package com.chanus.yuntao.mvc.manager.mapper;

import com.chanus.yuntao.mvc.manager.model.LoginUserView;
import com.chanus.yuntao.utils.core.map.CustomMap;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 登录用户视图
 *
 * @author Chanus
 * @date 2018-09-02 01:15:21
 * @since 0.0.1
 */
public interface LoginUserViewMapper {
    @Select("select login_no, login_name, password, role_code, master_no, master_role_code, valid_status, head_image " +
            "from view_login_user where binary login_no = #{loginNo,jdbcType=VARCHAR}")
    LoginUserView selectLoginUser(String loginNo);

    @Select("select 1 from view_user where binary user_no = #{userNo,jdbcType=VARCHAR} limit 1")
    Integer isExistUser(String userNo);

    @Select("select * from view_user where binary user_no = #{userNo,jdbcType=VARCHAR} limit 1")
    CustomMap selectUserByUserNo(String userNo);

    @Select("select * from view_user where ${column} = #{value}")
    CustomMap selectUser(@Param("column") String column, @Param("value") String value);
}