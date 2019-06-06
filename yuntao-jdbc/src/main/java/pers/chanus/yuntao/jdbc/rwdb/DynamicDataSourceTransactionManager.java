/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.jdbc.rwdb;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * 事务管理
 *
 * @author Chanus
 * @date 2019-01-03 23:05:16
 * @since 0.0.5
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {
    private static final long serialVersionUID = -4742343318643326824L;

    /**
     * 只读事务到读库，读写事务到写库
     *
     * @param transaction
     * @param definition
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        // 设置数据源
        boolean readOnly = definition.isReadOnly();
        if (readOnly) {
            DynamicDataSourceHolder.putDataSource(DynamicDataSourceGlobal.READ);
        } else {
            DynamicDataSourceHolder.putDataSource(DynamicDataSourceGlobal.WRITE);
        }
        super.doBegin(transaction, definition);
    }

    /**
     * 清理本地线程的数据源
     *
     * @param transaction
     */
    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        DynamicDataSourceHolder.clearDataSource();
    }
}
