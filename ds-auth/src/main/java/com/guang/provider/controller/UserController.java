package com.guang.provider.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.user.*;
import com.guang.provider.router.UserRouterService;
import com.guang.provider.service.impl.UserService;
import com.guang.provider.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author guangyong.deng
 * @date 2022-02-15 17:19
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@SuppressWarnings("unchecked")
public class UserController implements UserRouterService {

    @Autowired
    UserService userService;


    @Override
    public ResponseVO<UserVo> getUserInfo(UserQueryAo query) {
        UserVo userVo = userService.getUserInfo(query);

        if(userVo == null) {
            return ResponseVO.fail("查询错误");
        }
        return ResponseVO.success(userVo);
    }

    @Override
    public ResponseVO<List<MenuVo>> getUserNav(UserQueryAo query) {

        List<MenuVo> userNav = userService.getUserNav(query);
        return ResponseVO.success(userNav);
    }

    @Override
    public ResponseVO<List<MenuVo>> getUserBasic(UserQueryAo query) {
        return ResponseVO.success(userService.getUserBasic(query));
    }

    @Override
    public ResponseVO<MenuVo> getUserHome(UserQueryAo query) {
        return ResponseVO.success(userService.getUserHome(query));
    }

    @Override
    public ResponseVO<List<RoleVo>> getUserRoleBy(UserQueryAo query) {
        ResponseVO<UserVo> info = getUserInfo(query);
        return ResponseVO.success(info.getResult().getRoles());
    }

    @Override
    public ResponseVO<IPage<RolePermissionVo>> getBatchRolePermissionBy(UserQueryAo query) {
        return ResponseVO.success(userService.getBatchRolePermissionBy(query));
    }

    @Override
    public ResponseVO<Set<PermissionVo>> getBatchPermission() {
        return ResponseVO.success(userService.getBatchPermission());
    }

    @Override
    public ResponseVO<UserVo> addUser() {
        return null;
    }

    @Override
    public ResponseVO<Boolean> addPermissionForRole(UserPermissionUpdateAo updateAo) {
        System.out.println("添加角色权限");
        return ResponseVO.success(userService.addPermissionForRole(updateAo));
    }

    @Override
    public ResponseVO<String> addRole(RoleUpdateAo roleUpdateAo) {
        return ResponseVO.success(userService.addRole(roleUpdateAo));
    }

    @Override
    public ResponseVO<Boolean> updatePermission(PermissionUpdateAo updateAo) {
        return ResponseVO.success(userService.updatePermission(updateAo));
    }

    @Override
    public ResponseVO<Boolean> updateRole(RolePermissionVo updateAo) {
        return ResponseVO.success(userService.updateRole(updateAo));
    }

    @Override
    public ResponseVO<Boolean> addPermission(PermissionUpdateAo updateAo) {
        return ResponseVO.success(userService.addPermission(updateAo));
    }

    @Override
    public ResponseVO<Page<PermissionVo>> getPermissionPage(UserQueryAo queryAo) {
        return ResponseVO.success(userService.getBatchPermissionPage(queryAo));
    }

    @Override
    public ResponseVO<Set<PermissionVo>> getBatchPermissionBy(PermissionQueryAo queryAo) {
        return ResponseVO.success(userService.getBatchPermissionBy(queryAo));
    }


}
