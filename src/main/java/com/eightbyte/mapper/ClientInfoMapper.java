package com.eightbyte.mapper;

import com.eightbyte.domain.ClientInfo;
import com.eightbyte.domain.ClientInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClientInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    long countByExample(ClientInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    int deleteByExample(ClientInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    int insert(ClientInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    int insertSelective(ClientInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    List<ClientInfo> selectByExample(ClientInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    ClientInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ClientInfo record, @Param("example") ClientInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ClientInfo record, @Param("example") ClientInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ClientInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_client_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ClientInfo record);
}