package com.guang.provider.mapstruct;

import com.common.core.web.bean.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guang.provider.ao.file.FileQueryAo;
import com.guang.provider.feign.FileService;
import com.guang.provider.vo.entity.ActionEntitySet;
import com.guang.provider.vo.entity.Meta;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
@Component
public class SpecialTypeConvert {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Named(value = "meta")
    public Meta strToMeta(String strMeta) {

//        Objects.requireNonNull(strMeta, "progress is null");

        if(strMeta == null) {
            return null;
        }

        try {
            return objectMapper.readValue(strMeta, Meta.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Meta().setTitle("转换错误").setShow(false);

    }


    /**
     *
     * 将 文件 id 转换为 文件路径
     *
     * @param fileId 文件 id
     * @return
     */
    @Named("avatar")
    public String getFileUrl(String fileId) {

        FileService fileService = BeanUtil.getBean(FileService.class);
        return fileService.getFileUrl(new FileQueryAo().setFileId(fileId)).getResult();
    }


    @Named("actionEntitySet")
    public List<ActionEntitySet> actionEntitySet(String actionEntitySet) {

        try {
            return objectMapper.readValue(actionEntitySet, new TypeReference<List<ActionEntitySet>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    @Named("labels")
    public List<String> labels(String actionEntitySet) {

        try {
            List<ActionEntitySet> actions = objectMapper.readValue(actionEntitySet, new TypeReference<List<ActionEntitySet>>() {});

            if(!actions.isEmpty()) {
                return actions.stream().map(ActionEntitySet::getDescribe).collect(Collectors.toList());
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    final static Map<String, String> ACTION_LABELS = new HashMap<>();

    static {
        ACTION_LABELS.put("新增", "add");
        ACTION_LABELS.put("查询", "get");
        ACTION_LABELS.put("修改", "update");
        ACTION_LABELS.put("列表", "query");
        ACTION_LABELS.put("删除", "delete");
        ACTION_LABELS.put("导入", "import");
        ACTION_LABELS.put("导出", "export");
    }

    @Named("actions")
    public String actions(List<String> labels) {

        List<ActionEntitySet> actionEntitySets = new ArrayList<>();

        for (String label : labels) {
            actionEntitySets.add(new ActionEntitySet(ACTION_LABELS.get(label), label, false));
        }

        try {
            return objectMapper.writeValueAsString(actionEntitySets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;

    }

}
