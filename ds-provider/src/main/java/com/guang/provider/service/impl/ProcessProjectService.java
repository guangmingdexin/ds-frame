package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.common.core.web.domain.Constant;
import com.common.core.web.util.Util;
import com.guang.persistence.domain.SysProcessProject;
import com.guang.persistence.domain.SysProject;
import com.guang.persistence.service.process.SysProcessProjectService;
import com.guang.persistence.service.program.SysProjectService;
import com.guang.provider.ao.process.ProcessProjectDelAo;
import com.guang.provider.ao.process.ProcessProjectQueryAo;
import com.guang.provider.ao.process.ProcessProjectUpdateAo;
import com.guang.provider.ao.process.ProjectVerifyAo;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IProcessProjectService;
import com.guang.provider.vo.ProcessProjectVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author guangmingdexin
 * @date 2022/4/24
 */
@Service
@Slf4j
public class ProcessProjectService implements IProcessProjectService {

    @Autowired
    SysProcessProjectService sysProcessProjectService;

    @Autowired
    SysProjectService sysProjectService;


    final CommonConvert convert = CommonConvert.INSTANCE;

    @Override
    public List<ProcessProjectVo> getProcessProjectBatchBy(ProcessProjectQueryAo queryAo) {

        LambdaQueryWrapper<SysProcessProject> queryWrapper =  Wrappers.lambdaQuery();

        queryWrapper.eq(SysProcessProject::getDeleted, 0)
                        .eq(SysProcessProject::getProgramId, queryAo.getProgramId());

        List<SysProcessProject> list = sysProcessProjectService.list(queryWrapper);

        if(list.isEmpty()) {

            // 创建两个固定步骤（开始结束，并且返回需要有序）
            SysProcessProject FINAL = new SysProcessProject(
                    Util.generatorUUID(),
                    Constant.INITIAL_PROCESS_START_NAME,
                    "最终流程",
                    queryAo.getProgramId(),
                    queryAo.getProgramId(),
                    false,
                    true,
                    Constant.INITIAL_PROCESS_LEADER_ID,
                    null,
                    null
            );


            SysProcessProject START = new SysProcessProject(
                    Util.generatorUUID(),
                    Constant.INITIAL_PROCESS_DONE_NAME,
                    "最终流程",
                    queryAo.getProgramId(),
                    queryAo.getProgramId(),
                    false,
                    true,
                    Constant.INITIAL_PROCESS_LEADER_ID,
                    null,
                    null
            );

            START.setNextProcessId(FINAL.getProcessId());
            FINAL.setPrevProcessId(START.getProcessId());

            sysProcessProjectService.saveBatch(Arrays.asList(FINAL, START));

            list.add(START);
            list.add(FINAL);
        }

        // 相当于重新排序
        list = build(list);

        return list.stream().map(convert::sysProcessProjectConvertVo).collect(Collectors.toList());

    }

    @Override
    public ProcessProjectVo getProjectCurProcessBy(ProcessProjectQueryAo queryAo) {
        // 1.根据不同参数设置不同的返回结果
        if(!StringUtils.isEmpty(queryAo.getProjectId())) {

            // 2.需要获取玩家的当前进展
            SysProject project = sysProjectService.getById(queryAo.getProjectId());

            // 3.根据 project 获取当前进度
            SysProcessProject processProject = sysProcessProjectService.getById(project.getProcessProjectId());

            List<ProcessProjectVo> source = getProcessProjectBatchBy(queryAo);

            if(processProject == null || processProject.getProcessName().equals(Constant.INITIAL_PROCESS_START_NAME)) {
                // 为该项目设置初始状态流程
                log.info("initial step: {}", processProject);
                ProcessProjectVo vo = source.get(0);


                // 首先判断是否为初始步骤，如果是，再判断下一个阶段是否为 结束步骤
                // 正常来说，应该还有一层判断，但是按照正常流程，开始阶段，vo 一定为 开始步骤
                // 如果是，说明该课题只有两个步骤，如果不是，则跳过开始步骤，直接进入下一个阶段
                if(!Constant.INITIAL_PROCESS_DONE_NAME.equals(getNextProcess(vo.getProcessId()).getProcessName())) {
                    vo = convert.sysProcessProjectConvertVo(getNextProcess(vo.getProcessId()));
                }
                project.setProcessProjectId(vo.getProcessId());
                sysProjectService.updateById(project);
                return vo;
            }
            log.info("get step: {}", processProject);
            int i = 0;
            // 确定当前课题正处于哪个流程，因为返回的数据是有序的，直接遍历即可获取
            for (ProcessProjectVo s : source) {

                if(!s.getProcessId().equals(processProject.getProcessId())) {
                    i ++;
                }else {
                    break;
                }
            }
            // 类型转换
            return convert.sysProcessProjectConvertVo(processProject).setCurrent(i);
        }

        throw new NullPointerException("获取失败");
    }

    @Override
    public boolean verifyProject(ProjectVerifyAo verifyAo) {

        // 1.用户申报课题（教师，学生）
        // 2.开始走流程，获取所有流程，并取得下一步流程的负责人，发送一条通知给下一位负责人
        //   并由下一位负责人来更新审核课题的流程状态
        // 3.最后判断是否为最后一条流程，如果是，则更新课题的状态，并再此发送信息通过申报人

        // 首先，权限判定，是否该角色可以进行此流程下的课题审核
        LambdaUpdateWrapper<SysProject> statusProjectUpdate = Wrappers.lambdaUpdate();

        statusProjectUpdate.eq(SysProject::getProjectId, verifyAo.getProjectId())
                .eq(SysProject::getDeleted, 0);

        if(!verifyAo.getApprove()) {
            // 说明流程被上一级打回，此时有两种方案
            // 1.直接重新开始走流程
            // 2.回到上一级重新开始
            // 记录日志里面，重新设置 课题流程为 上一状态
            // 只要被驳回一次，说明申报失败，但是不用从头开始（回到上一级审核）
            statusProjectUpdate.set(SysProject::getStatus, "error");
            SysProcessProject prev = getPrevProcess(verifyAo.getProcessId());
            statusProjectUpdate.set(SysProject::getProcessProjectId, prev.getProcessId());
        }else {
            // 更新课题的流程状态为下一个状态
            SysProcessProject next = getNextProcess(verifyAo.getProcessId());
            if(Constant.INITIAL_PROCESS_DONE_NAME.equals(next.getProcessName())) {
                // 说明已经走完流程了
               statusProjectUpdate.set(SysProject::getStatus, "success");
            }else {
                statusProjectUpdate.set(SysProject::getProcessProjectId, next.getProcessId());
            }
        }

        // 发送通知
        return sysProjectService.update(statusProjectUpdate);

    }

    @Override
    public SysProcessProject getNextProcess(String processId) {

        SysProcessProject cur = sysProcessProjectService.getById(processId);

        if(cur.getNextProcessId() == null) {
            return cur;
        }

        return sysProcessProjectService.getById(cur.getNextProcessId());
    }

    @Override
    public SysProcessProject getPrevProcess(String processId) {

        SysProcessProject cur = sysProcessProjectService.getById(processId);

        if(cur.getPrevProcessId() == null) {
            return cur;
        }

        return sysProcessProjectService.getById(cur.getPrevProcessId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysProcessProject addProcessProject(ProcessProjectUpdateAo updateAo) {

        SysProcessProject sysProcessProject = convert.processProjectAoConvertDo(updateAo);

        // 同时改变连接状态
        SysProcessProject prev = sysProcessProjectService.getById(updateAo.getPrevProcessId());
        SysProcessProject next = sysProcessProjectService.getById(updateAo.getNextProcessId());


        boolean r = sysProcessProjectService.save(sysProcessProject);

        prev.setNextProcessId(sysProcessProject.getProcessId());
        next.setPrevProcessId(sysProcessProject.getProcessId());

        return r && sysProcessProjectService.updateBatchById(Arrays.asList(prev, next)) ? sysProcessProject : null;
    }

    @Override
    public boolean editProcessProject(ProcessProjectUpdateAo updateAo) {

        LambdaUpdateWrapper<SysProcessProject> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper.eq(SysProcessProject::getDeleted, 0)
                .eq(SysProcessProject::getProcessId, updateAo.getProcessId());

        return sysProcessProjectService.updateById(convert.processProjectAoConvertDo(updateAo));
    }

    @Override
    public boolean delProcessProject(ProcessProjectDelAo delAo) {


        // TODO: 删除流程模块会对待审核的课题造成影响，需要后面及时处理
        //

        // 同时更改前一个节点和后一个节点
        String processId = delAo.getProcessId();

        SysProcessProject next = getNextProcess(processId);
        SysProcessProject prev = getPrevProcess(processId);
        SysProcessProject cur = sysProcessProjectService.getById(processId);

        next.setPrevProcessId(prev.getProcessId());
        prev.setNextProcessId(next.getProcessId());

        cur.setDeleted(true);

        return sysProcessProjectService.updateBatchById(Arrays.asList(next, prev, cur));
    }


    public List<SysProcessProject> build(List<SysProcessProject> source) {

        Objects.requireNonNull(source, "source is null");

        // 调整顺序，按照从开始到结束

        SysProcessProject root = source
                .stream()
                .filter(sysProcessProject -> sysProcessProject.getPrevProcessId() == null)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("请先插入开始步骤"));

        Map<String, SysProcessProject> indexMap = new HashMap<>(source.size());

        source.forEach(e -> indexMap.put(e.getProcessId(), e));

        List<SysProcessProject> tree = new ArrayList<>(source.size());

        for (int i = 0; i < source.size(); i++) {
            tree.add(root);
            String nextProcessId = root.getNextProcessId();
            root = indexMap.get(nextProcessId);
        }

        return tree;
    }
}
