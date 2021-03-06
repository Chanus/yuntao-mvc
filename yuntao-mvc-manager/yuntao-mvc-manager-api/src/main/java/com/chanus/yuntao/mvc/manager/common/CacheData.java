/*
 * Copyright (c) 2020 Chanus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chanus.yuntao.mvc.manager.common;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.chanus.yuntao.mvc.manager.model.DictItem;
import com.chanus.yuntao.utils.core.encrypt.RSAUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 系统缓存数据
 *
 * @author Chanus
 * @date 2018-08-31 23:02:47
 * @since 0.0.1
 */
public class CacheData {
    /**
     * 存储系统参数配置信息
     *
     * @since 0.1.1
     */
    public static final Map<String, String> SYSTEM_PARAMS_MAP = new HashMap<>();

    /**
     * 存储系统字典数据，Map的key为字典集代码，value为有序的字典项列表
     *
     * @since 0.1.1
     */
    public static final Map<String, List<DictItem>> SYSTEM_DICT_MAP = new HashMap<>();

    /**
     * 存储系统字典项数据，Map的key为“字典集代码-字典项代码”，value为字典项名称
     *
     * @since 0.1.7
     */
    public static final Map<String, String> SYSTEM_DICT_ITEM_MAP = new HashMap<>();

    /**
     * 存储所有的请求URL及Controller类名，Map<Controller, List<URL>>
     *
     * @since 0.0.1
     */
    public static final Map<String, Set<String>> CLASS_URL_MAP = new HashMap<>();

    /**
     * 存储RSA密钥对
     *
     * @since 0.0.1
     */
    public static final Queue<Map<String, String>> RSA_KEYS_QUEUE = new ConcurrentLinkedQueue<>();

    /**
     * 存储从{@code RSA_KEYS_QUEUE}队列中取出的RSA密钥对
     *
     * @since 0.0.1
     */
    public static final Map<String, String> RSA_KEYS_MAP = new HashMap<>();

    /**
     * 初始化CLASS_URL_MAP
     *
     * @since 0.0.1
     */
    public static void initClassUrlMap(HttpServletRequest request) {
        WebApplicationContext webApplicationContext = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);//获取上下文对象
        Map<String, RequestMappingHandlerMapping> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(webApplicationContext, RequestMappingHandlerMapping.class, true, false);
        for (RequestMappingHandlerMapping rmhm : map.values()) {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
            for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
                HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry.getValue();
                RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry.getKey();

                // 获取Controller类
                String controllerName = mappingInfoValue.getBeanType().toString().replace("class", "").replace(" ", "");

                // 存储Controller类
                CacheData.CLASS_URL_MAP.computeIfAbsent(controllerName, k -> new TreeSet<>());

                // 存储请求URL
                PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                CacheData.CLASS_URL_MAP.get(controllerName).add(patternsCondition.toString().replace("[", "").replace("]", ""));
            }
        }
    }

    /**
     * 初始化RSA_KEYS_QUEUE
     *
     * @since 0.0.1
     */
    public static void initRsaKeysQueue() {
        for (int i = 0; i < 100; i++) {
            RSA_KEYS_QUEUE.offer(RSAUtils.generateKeyPair());
        }
    }

    /**
     * 从队列{@code RSA_KEYS_QUEUE}中取出一个RSA密钥对，如果队列{@code RSA_KEYS_QUEUE}为空，则重新初始化一次队列
     *
     * @return RSA密钥对
     * @since 0.0.1
     */
    public static Map<String, String> getRsaKey() {
        if (CacheData.RSA_KEYS_QUEUE.size() == 0)
            initRsaKeysQueue();
        return CacheData.RSA_KEYS_QUEUE.poll();
    }
}
