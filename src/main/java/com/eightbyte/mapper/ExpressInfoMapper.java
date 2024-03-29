package com.eightbyte.mapper;

import com.eightbyte.domain.ExpressInfo;
import com.eightbyte.domain.ExpressInfoExample;

import java.util.List;

import com.eightbyte.vo.ExpressInfoVo;
import com.eightbyte.vo.ParamBean;
import org.apache.ibatis.annotations.Param;

public interface ExpressInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    long countByExample(ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    int deleteByExample(ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    int insert(ExpressInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    int insertSelective(ExpressInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    List<ExpressInfo> selectByExample(ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    ExpressInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ExpressInfo record, @Param("example") ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ExpressInfo record, @Param("example") ExpressInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ExpressInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ExpressInfo record);

    List<ExpressInfoVo> searchExpressInfoVosByStatus(int status);

    ExpressInfoVo searchExpressInfoVoById(int id);

    List<ExpressInfoVo> selectExpressInfoVosByParamBean(ParamBean param);

    List<ExpressInfoVo> selectNoBusyExpressAnd4PickUp();

    List<ExpressInfoVo> selectAllMyExpressTask(Integer userId);
}