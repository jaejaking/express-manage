package com.eightbyte.domain;

import lombok.*;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Getter
@Setter
@ToString
public class ExpressInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.type
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.weight
     *
     * @mbg.generated
     */
    private BigDecimal weight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.order_no
     *
     * @mbg.generated
     */
    private String orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.is_busy
     *
     * @mbg.generated
     */
    private Integer isBusy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.send_people_id
     *
     * @mbg.generated
     */
    private Integer sendPeopleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.receive_people_id
     *
     * @mbg.generated
     */
    private Integer receivePeopleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_express_info.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    public ExpressInfo(Integer id, Integer type, BigDecimal weight, String orderNo, Integer status, Integer isBusy, Integer sendPeopleId, Integer receivePeopleId, Date createTime, Date updateTime) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.orderNo = orderNo;
        this.status = status;
        this.isBusy = isBusy;
        this.sendPeopleId = sendPeopleId;
        this.receivePeopleId = receivePeopleId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_info
     *
     * @mbg.generated
     */
    public ExpressInfo() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.id
     *
     * @return the value of t_express_info.id
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.id
     *
     * @param id the value for t_express_info.id
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.type
     *
     * @return the value of t_express_info.type
     * @mbg.generated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.type
     *
     * @param type the value for t_express_info.type
     * @mbg.generated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.weight
     *
     * @return the value of t_express_info.weight
     * @mbg.generated
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.weight
     *
     * @param weight the value for t_express_info.weight
     * @mbg.generated
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.order_no
     *
     * @return the value of t_express_info.order_no
     * @mbg.generated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.order_no
     *
     * @param orderNo the value for t_express_info.order_no
     * @mbg.generated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.status
     *
     * @return the value of t_express_info.status
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.status
     *
     * @param status the value for t_express_info.status
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.is_busy
     *
     * @return the value of t_express_info.is_busy
     * @mbg.generated
     */
    public Integer getIsBusy() {
        return isBusy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.is_busy
     *
     * @param isBusy the value for t_express_info.is_busy
     * @mbg.generated
     */
    public void setIsBusy(Integer isBusy) {
        this.isBusy = isBusy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.send_people_id
     *
     * @return the value of t_express_info.send_people_id
     * @mbg.generated
     */
    public Integer getSendPeopleId() {
        return sendPeopleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.send_people_id
     *
     * @param sendPeopleId the value for t_express_info.send_people_id
     * @mbg.generated
     */
    public void setSendPeopleId(Integer sendPeopleId) {
        this.sendPeopleId = sendPeopleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.receive_people_id
     *
     * @return the value of t_express_info.receive_people_id
     * @mbg.generated
     */
    public Integer getReceivePeopleId() {
        return receivePeopleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.receive_people_id
     *
     * @param receivePeopleId the value for t_express_info.receive_people_id
     * @mbg.generated
     */
    public void setReceivePeopleId(Integer receivePeopleId) {
        this.receivePeopleId = receivePeopleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.create_time
     *
     * @return the value of t_express_info.create_time
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.create_time
     *
     * @param createTime the value for t_express_info.create_time
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_express_info.update_time
     *
     * @return the value of t_express_info.update_time
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_express_info.update_time
     *
     * @param updateTime the value for t_express_info.update_time
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}