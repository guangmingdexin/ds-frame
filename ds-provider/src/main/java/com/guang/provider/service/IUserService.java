package com.guang.provider.service;

import com.guang.provider.bo.UserBo;

/**
 * @author guangyong.deng
 * @date 2022-02-15 17:19
 */
public interface IUserService {


    UserBo getUser(String uId);
}
