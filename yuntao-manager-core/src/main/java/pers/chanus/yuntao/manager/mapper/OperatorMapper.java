package pers.chanus.yuntao.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import pers.chanus.yuntao.manager.model.Operator;
import pers.chanus.yuntao.server.mapper.SuperMapper;

import java.util.List;
import java.util.Map;

public interface OperatorMapper extends SuperMapper<Operator> {
    int updateByPrimaryKeySelective(Operator operator);

    Operator getById(@Param("id") Integer id, @Param("aesEmailKey") String aesEmailKey, @Param("aesPhoneKey") String aesPhoneKey);

    Operator getByOperatorNo(@Param("operatorNo") String operatorNo, @Param("aesEmailKey") String aesEmailKey, @Param("aesPhoneKey") String aesPhoneKey);

    @Select("select operator_password from sys_operator where operator_no = #{operatorNo,jdbcType=VARCHAR}")
    String getPassword(String operatorNo);

    @Update("update sys_operator set operator_password = #{arg1,jdbcType=CHAR} where operator_no = #{arg0,jdbcType=VARCHAR}")
    void updatePassword(String operatorNo, String operatorPassword);

    int countSub(Map<String, Object> params);

    List<Operator> listSub(Map<String, Object> params);

    @Select("select head_image from sys_operator where operator_no = #{operatorNo,jdbcType=VARCHAR}")
    String getHeadImage(String operatorNo);

    @Update("update sys_operator set head_image = #{arg1,jdbcType=VARCHAR} where operator_no = #{arg0,jdbcType=VARCHAR}")
    void updateHeadImage(String operatorNo, String headImage);
}