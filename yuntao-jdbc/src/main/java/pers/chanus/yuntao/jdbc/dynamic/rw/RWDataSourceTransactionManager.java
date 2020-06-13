package pers.chanus.yuntao.jdbc.dynamic.rw;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import pers.chanus.yuntao.jdbc.dynamic.DataSourceEnum;
import pers.chanus.yuntao.jdbc.dynamic.DynamicDataSourceHolder;

/**
 * 事务管理
 *
 * @author Chanus
 * @date 2020-06-13 18:54:16
 * @since 0.2.1
 */
public class RWDataSourceTransactionManager extends DataSourceTransactionManager {
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
            DynamicDataSourceHolder.setDataSource(DataSourceEnum.SECOND.getValue());
        } else {
            DynamicDataSourceHolder.setDataSource(DataSourceEnum.DEFAULT.getValue());
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
        DynamicDataSourceHolder.clear();
    }
}
