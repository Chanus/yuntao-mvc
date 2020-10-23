/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.common;

import pers.chanus.yuntao.commons.pojo.LoginUser;
import pers.chanus.yuntao.manager.model.Module;
import pers.chanus.yuntao.manager.model.ModulePower;
import com.chanus.yuntao.utils.core.CollectionUtils;
import com.chanus.yuntao.utils.core.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统模块权限项工具类
 *
 * @author Chanus
 * @date 2018-09-06 22:01:51
 * @since 0.0.1
 */
public class ModulePowerUtils {
    /**
     * 获取用户的模块权限
     *
     * @param session
     * @param moduleCode 模块代码
     * @return 用户的模块权限列表
     * @since 0.0.1
     */
    @SuppressWarnings("unchecked")
    public static List<ModulePower> getModulePowers(HttpSession session, String moduleCode) {
        List<Module> menus = (List<Module>) ((LoginUser) session.getAttribute("loginUser")).getMenus();
        if (CollectionUtils.isEmpty(menus))
            return null;

        // 遍历一级模块菜单
        for (Module module1 : menus) {
            if (module1 == null || CollectionUtils.isEmpty(module1.getChildren()))
                continue;
            // 遍历二级模块菜单
            for (Module module2 : module1.getChildren()) {
                // 返回该模块的权限组
                if (moduleCode.equals(module2.getModuleCode()))
                    return module2.getModulePowers();
            }
        }

        return null;
    }

    /**
     * 获取用户的模块权限
     *
     * @param session
     * @param moduleCode 模块代码
     * @return 用户的模块权限Map集合
     * @since 0.0.1
     */
    public static Map<String, Boolean> getPowers(HttpSession session, String moduleCode) {
        List<ModulePower> modulePowers = getModulePowers(session, moduleCode);
        Map<String, Boolean> powerMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(modulePowers)) {
            for (ModulePower power : modulePowers) {
                if (power != null)
                    powerMap.put(power.getPowerItem(), true);
            }
        }

        return powerMap;
    }

    /**
     * 判断用户是否具有指定的模块权限
     *
     * @param modulePowers  模块权限项
     * @param powerItem 权限项
     * @return {@code true} 有权限；{@code false} 没有权限
     * @since 0.0.1
     */
    public static boolean checkModulePower(List<ModulePower> modulePowers, String powerItem) {
        if (CollectionUtils.isEmpty(modulePowers))
            return false;

        // 遍历模块的权限组
        for (ModulePower power : modulePowers) {
            if (power != null && StringUtils.equals(powerItem, power.getPowerItem()))
                return true;
        }

        return false;
    }
}
