package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.chanus.yuntao.manager.model.ScheduleJob;
import pers.chanus.yuntao.springmvc.mapper.SuperMapper;

import java.util.List;

/**
 * 定时任务定义表
 *
 * @author Chanus
 * @date 2020-04-13 23:46:30
 * @since 0.1.7
 */
public interface ScheduleJobMapper extends SuperMapper<ScheduleJob> {
    @Select("select valid_status from sys_schedule_job where id = #{arg0,jdbcType=INTEGER}")
    String getValidStatus(Integer id);

    @Update("update sys_schedule_job set valid_status = #{arg1,jdbcType=CHAR} where id = #{arg0,jdbcType=INTEGER}")
    int updateValidStatus(Integer id, String validStatus);

    ScheduleJob getScheduleJob(Integer id);

    List<ScheduleJob> listScheduleJob();
}