package com.guang.provider.router;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.user.*;
import com.guang.provider.vo.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * @author guangyong.deng
 * @date 2022-02-15 9:34
 */
public interface UserRouterService {

    /**
     *
     * 返回 UserVo
     *
     * @param query 查询对象
     * @return UserVo
     */
    @PostMapping("/user/info")
    ResponseVO<UserVo> getUserInfo(@Validated @RequestBody UserQueryAo query);


    /**
     *
     * 返回菜单列表
     *
     * @param query 查询对象
     * @return List MenuVo
     */
    @PostMapping("/user/nav")
    ResponseVO<List<MenuVo>> getUserNav(@Validated @RequestBody UserQueryAo query);

    /**
     *
     * 返回基础列表
     *
     * @param query 查询对象
     * @return List MenuVo
     */
    @PostMapping("/user/basic")
    ResponseVO<List<MenuVo>> getUserBasic(@Validated @RequestBody UserQueryAo query);



    /**
     *
     * 获取用户的首页界面
     *
     * @param query 查询对象
     * @return MenuVo
     */
    @PostMapping("/user/home")
    ResponseVO<MenuVo> getUserHome(@Validated @RequestBody UserQueryAo query);


    /**
     *
     * 获取用户角色列表
     *
     * @param query 查询对象
     * @return
     */
    @PostMapping("/user/role")
    ResponseVO<List<RoleVo>> getUserRoleBy(@Validated @RequestBody UserQueryAo query);


    @PostMapping("/user/role-permission")
    ResponseVO<IPage<RolePermissionVo>> getBatchRolePermissionBy(@Validated @RequestBody UserQueryAo query);


    @GetMapping("/user/permission/list")
    ResponseVO<Set<PermissionVo>> getBatchPermission();


    ResponseVO<UserVo> addUser();


    @PostMapping(value = "/user/role-permission/save")
    ResponseVO<Boolean> addPermissionForRole(@RequestBody UserPermissionUpdateAo updateAo);

    @PostMapping(value = "/user/role/save")
    ResponseVO<String> addRole(@RequestBody RoleUpdateAo roleUpdateAo);

    @PostMapping(value = "/user/permission/edit")
    ResponseVO<Boolean> updatePermission(@RequestBody PermissionUpdateAo updateAo);

    @PostMapping(value = "/user/role/edit")
    ResponseVO<Boolean> updateRole(@RequestBody RolePermissionVo updateAo);

    @PostMapping(value = "/user/permission/save")
    ResponseVO<Boolean> addPermission(@RequestBody PermissionUpdateAo updateAo);

    @PostMapping(value = "/user/permission/list-page")
    ResponseVO<Page<PermissionVo>> getPermissionPage(@RequestBody UserQueryAo queryAo);

    @PostMapping(value = "/user/permission/list-role")
    ResponseVO<Set<PermissionVo>> getBatchPermissionBy(@RequestBody PermissionQueryAo queryAo);
}
