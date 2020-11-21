package com.chanus.yuntao.mvc.manager.mapper;

import org.apache.ibatis.annotations.Select;
import com.chanus.yuntao.mvc.manager.model.LoginUserView;

/**
 * 登录用户视图
 *
 * @author Chanus
 * @date 2018-09-02 01:15:21
 * @since 0.0.1
 */
public interface LoginUserViewMapper {
    @Select("select login_no, login_name, role_code, master_no, master_role_code, valid_status, head_image " +
            "from view_login_user where login_no = #{loginNo,jdbcType=VARCHAR}")
    LoginUserView getUser(String loginNo);

    @Select("select login_no, login_name, password, role_code, master_no, master_role_code, valid_status, head_image " +
            "from view_login_user where binary login_no = #{loginNo,jdbcType=VARCHAR}")
    LoginUserView login(String loginNo);

    @Select("select user_name from view_user where user_name = #{userName,jdbcType=VARCHAR} limit 1")
    String getUserName(String userName);
}