package pers.chanus.yuntao.manager.mapper;

import pers.chanus.yuntao.manager.model.LoginUserView;

public interface LoginUserViewMapper {
    LoginUserView getUser(String loginNo);

    LoginUserView login(String loginNo);

    String getUserName(String userName);
}