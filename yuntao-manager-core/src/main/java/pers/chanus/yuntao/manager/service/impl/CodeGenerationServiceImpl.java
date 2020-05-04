/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.PageBean;
import pers.chanus.yuntao.manager.common.CodeGenerationUtils;
import pers.chanus.yuntao.manager.mapper.DataBaseColumnMapper;
import pers.chanus.yuntao.manager.mapper.DataBaseTableMapper;
import pers.chanus.yuntao.manager.mapper.ModuleMapper;
import pers.chanus.yuntao.manager.model.DataBaseColumn;
import pers.chanus.yuntao.manager.model.DataBaseTable;
import pers.chanus.yuntao.manager.model.Module;
import pers.chanus.yuntao.manager.service.CodeGenerationService;
import pers.chanus.yuntao.util.IOUtils;
import pers.chanus.yuntao.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 系统代码自动生成接口实现
 *
 * @author Chanus
 * @date 2018-10-30 20:24:46
 * @since 0.0.3
 */
@Service
public class CodeGenerationServiceImpl implements CodeGenerationService {
    @Autowired
    private DataBaseTableMapper dataBaseTableMapper;
    @Autowired
    private DataBaseColumnMapper dataBaseColumnMapper;
    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public PageBean listDataBaseTablePagination(CustomMap params) {
        int count = dataBaseTableMapper.count(params);
        if (count > 0) {
            int page = params.get("page") == null ? 1 : Integer.parseInt(String.valueOf(params.get("page")));
            int limit = params.get("limit") == null ? PageBean.PAGE_SIZE : Integer.parseInt(String.valueOf(params.get("limit")));
            params.putNext("start", (page - 1) * limit).putNext("limit", limit).putNext("pagination", true);
            return PageBean.pagination(count, dataBaseTableMapper.list(params));
        }

        return new PageBean();
    }

    @Override
    public List<DataBaseColumn> listDataBaseColumn(CustomMap params) {
        return dataBaseColumnMapper.list(params);
    }

    @Override
    public byte[] generateCode(String tableSchema, String[] tableNames, Map<String, Object> params) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        DataBaseTable table;
        List<DataBaseColumn> columns;
        for (String tableName : tableNames) {
            // 查询表信息
            table = dataBaseTableMapper.get(tableSchema, tableName);
            // 查询列信息
            columns = dataBaseColumnMapper.list(CustomMap.get().putNext("tableSchema", tableSchema).putNext("tableName", tableName));
            // 获取模块代码和模块名称
            String moduleId = (String) params.get("moduleId");
            String moduleCode = null, moduleName = null;
            if (StringUtils.isNotBlank(moduleId)) {
                Module module = moduleMapper.selectByPrimaryKey(Integer.parseInt(moduleId));
                moduleCode = module == null ? null : module.getModuleCode();
                moduleName = module == null ? null : module.getModuleName();
            }

            params.put("moduleCode", StringUtils.isBlank(moduleCode) ? table.getTableName().toUpperCase() : moduleCode);
            params.put("moduleName", StringUtils.isBlank(moduleName) ? table.getTableComment() : moduleName);
            // 生成代码
            CodeGenerationUtils.generateCode(table, columns, params, zip);
        }

        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

}
