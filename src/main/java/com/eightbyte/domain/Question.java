package com.eightbyte.domain;

import java.util.Date;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.question_name
     *
     * @mbg.generated
     */
    private String questionName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column question.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated
     */
    public Question(Integer id, String questionName, Date createTime, Date updateTime) {
        this.id = id;
        this.questionName = questionName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question
     *
     * @mbg.generated
     */
    public Question() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.id
     *
     * @return the value of question.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.id
     *
     * @param id the value for question.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.question_name
     *
     * @return the value of question.question_name
     *
     * @mbg.generated
     */
    public String getQuestionName() {
        return questionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.question_name
     *
     * @param questionName the value for question.question_name
     *
     * @mbg.generated
     */
    public void setQuestionName(String questionName) {
        this.questionName = questionName == null ? null : questionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.create_time
     *
     * @return the value of question.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.create_time
     *
     * @param createTime the value for question.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column question.update_time
     *
     * @return the value of question.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column question.update_time
     *
     * @param updateTime the value for question.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}