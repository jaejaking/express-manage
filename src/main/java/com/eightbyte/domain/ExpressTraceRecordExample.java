package com.eightbyte.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpressTraceRecordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public ExpressTraceRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNull() {
            addCriterion("express_id is null");
            return (Criteria) this;
        }

        public Criteria andExpressIdIsNotNull() {
            addCriterion("express_id is not null");
            return (Criteria) this;
        }

        public Criteria andExpressIdEqualTo(Integer value) {
            addCriterion("express_id =", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotEqualTo(Integer value) {
            addCriterion("express_id <>", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThan(Integer value) {
            addCriterion("express_id >", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("express_id >=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThan(Integer value) {
            addCriterion("express_id <", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdLessThanOrEqualTo(Integer value) {
            addCriterion("express_id <=", value, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdIn(List<Integer> values) {
            addCriterion("express_id in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotIn(List<Integer> values) {
            addCriterion("express_id not in", values, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdBetween(Integer value1, Integer value2) {
            addCriterion("express_id between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("express_id not between", value1, value2, "expressId");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoIsNull() {
            addCriterion("express_order_no is null");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoIsNotNull() {
            addCriterion("express_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoEqualTo(String value) {
            addCriterion("express_order_no =", value, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoNotEqualTo(String value) {
            addCriterion("express_order_no <>", value, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoGreaterThan(String value) {
            addCriterion("express_order_no >", value, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("express_order_no >=", value, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoLessThan(String value) {
            addCriterion("express_order_no <", value, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoLessThanOrEqualTo(String value) {
            addCriterion("express_order_no <=", value, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoLike(String value) {
            addCriterion("express_order_no like", value, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoNotLike(String value) {
            addCriterion("express_order_no not like", value, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoIn(List<String> values) {
            addCriterion("express_order_no in", values, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoNotIn(List<String> values) {
            addCriterion("express_order_no not in", values, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoBetween(String value1, String value2) {
            addCriterion("express_order_no between", value1, value2, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpressOrderNoNotBetween(String value1, String value2) {
            addCriterion("express_order_no not between", value1, value2, "expressOrderNo");
            return (Criteria) this;
        }

        public Criteria andFromAddrIsNull() {
            addCriterion("from_addr is null");
            return (Criteria) this;
        }

        public Criteria andFromAddrIsNotNull() {
            addCriterion("from_addr is not null");
            return (Criteria) this;
        }

        public Criteria andFromAddrEqualTo(String value) {
            addCriterion("from_addr =", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotEqualTo(String value) {
            addCriterion("from_addr <>", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrGreaterThan(String value) {
            addCriterion("from_addr >", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrGreaterThanOrEqualTo(String value) {
            addCriterion("from_addr >=", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrLessThan(String value) {
            addCriterion("from_addr <", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrLessThanOrEqualTo(String value) {
            addCriterion("from_addr <=", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrLike(String value) {
            addCriterion("from_addr like", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotLike(String value) {
            addCriterion("from_addr not like", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrIn(List<String> values) {
            addCriterion("from_addr in", values, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotIn(List<String> values) {
            addCriterion("from_addr not in", values, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrBetween(String value1, String value2) {
            addCriterion("from_addr between", value1, value2, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotBetween(String value1, String value2) {
            addCriterion("from_addr not between", value1, value2, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrIsNull() {
            addCriterion("to_addr is null");
            return (Criteria) this;
        }

        public Criteria andToAddrIsNotNull() {
            addCriterion("to_addr is not null");
            return (Criteria) this;
        }

        public Criteria andToAddrEqualTo(String value) {
            addCriterion("to_addr =", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrNotEqualTo(String value) {
            addCriterion("to_addr <>", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrGreaterThan(String value) {
            addCriterion("to_addr >", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrGreaterThanOrEqualTo(String value) {
            addCriterion("to_addr >=", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrLessThan(String value) {
            addCriterion("to_addr <", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrLessThanOrEqualTo(String value) {
            addCriterion("to_addr <=", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrLike(String value) {
            addCriterion("to_addr like", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrNotLike(String value) {
            addCriterion("to_addr not like", value, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrIn(List<String> values) {
            addCriterion("to_addr in", values, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrNotIn(List<String> values) {
            addCriterion("to_addr not in", values, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrBetween(String value1, String value2) {
            addCriterion("to_addr between", value1, value2, "toAddr");
            return (Criteria) this;
        }

        public Criteria andToAddrNotBetween(String value1, String value2) {
            addCriterion("to_addr not between", value1, value2, "toAddr");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIsNull() {
            addCriterion("transfer_type is null");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIsNotNull() {
            addCriterion("transfer_type is not null");
            return (Criteria) this;
        }

        public Criteria andTransferTypeEqualTo(Integer value) {
            addCriterion("transfer_type =", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotEqualTo(Integer value) {
            addCriterion("transfer_type <>", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeGreaterThan(Integer value) {
            addCriterion("transfer_type >", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("transfer_type >=", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeLessThan(Integer value) {
            addCriterion("transfer_type <", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeLessThanOrEqualTo(Integer value) {
            addCriterion("transfer_type <=", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIn(List<Integer> values) {
            addCriterion("transfer_type in", values, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotIn(List<Integer> values) {
            addCriterion("transfer_type not in", values, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeBetween(Integer value1, Integer value2) {
            addCriterion("transfer_type between", value1, value2, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("transfer_type not between", value1, value2, "transferType");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrIsNull() {
            addCriterion("history_addr is null");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrIsNotNull() {
            addCriterion("history_addr is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrEqualTo(String value) {
            addCriterion("history_addr =", value, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrNotEqualTo(String value) {
            addCriterion("history_addr <>", value, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrGreaterThan(String value) {
            addCriterion("history_addr >", value, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrGreaterThanOrEqualTo(String value) {
            addCriterion("history_addr >=", value, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrLessThan(String value) {
            addCriterion("history_addr <", value, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrLessThanOrEqualTo(String value) {
            addCriterion("history_addr <=", value, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrLike(String value) {
            addCriterion("history_addr like", value, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrNotLike(String value) {
            addCriterion("history_addr not like", value, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrIn(List<String> values) {
            addCriterion("history_addr in", values, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrNotIn(List<String> values) {
            addCriterion("history_addr not in", values, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrBetween(String value1, String value2) {
            addCriterion("history_addr between", value1, value2, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andHistoryAddrNotBetween(String value1, String value2) {
            addCriterion("history_addr not between", value1, value2, "historyAddr");
            return (Criteria) this;
        }

        public Criteria andIsToIsNull() {
            addCriterion("is_to is null");
            return (Criteria) this;
        }

        public Criteria andIsToIsNotNull() {
            addCriterion("is_to is not null");
            return (Criteria) this;
        }

        public Criteria andIsToEqualTo(Integer value) {
            addCriterion("is_to =", value, "isTo");
            return (Criteria) this;
        }

        public Criteria andIsToNotEqualTo(Integer value) {
            addCriterion("is_to <>", value, "isTo");
            return (Criteria) this;
        }

        public Criteria andIsToGreaterThan(Integer value) {
            addCriterion("is_to >", value, "isTo");
            return (Criteria) this;
        }

        public Criteria andIsToGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_to >=", value, "isTo");
            return (Criteria) this;
        }

        public Criteria andIsToLessThan(Integer value) {
            addCriterion("is_to <", value, "isTo");
            return (Criteria) this;
        }

        public Criteria andIsToLessThanOrEqualTo(Integer value) {
            addCriterion("is_to <=", value, "isTo");
            return (Criteria) this;
        }

        public Criteria andIsToIn(List<Integer> values) {
            addCriterion("is_to in", values, "isTo");
            return (Criteria) this;
        }

        public Criteria andIsToNotIn(List<Integer> values) {
            addCriterion("is_to not in", values, "isTo");
            return (Criteria) this;
        }

        public Criteria andIsToBetween(Integer value1, Integer value2) {
            addCriterion("is_to between", value1, value2, "isTo");
            return (Criteria) this;
        }

        public Criteria andIsToNotBetween(Integer value1, Integer value2) {
            addCriterion("is_to not between", value1, value2, "isTo");
            return (Criteria) this;
        }

        public Criteria andExpressStatusIsNull() {
            addCriterion("express_status is null");
            return (Criteria) this;
        }

        public Criteria andExpressStatusIsNotNull() {
            addCriterion("express_status is not null");
            return (Criteria) this;
        }

        public Criteria andExpressStatusEqualTo(Integer value) {
            addCriterion("express_status =", value, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andExpressStatusNotEqualTo(Integer value) {
            addCriterion("express_status <>", value, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andExpressStatusGreaterThan(Integer value) {
            addCriterion("express_status >", value, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andExpressStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("express_status >=", value, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andExpressStatusLessThan(Integer value) {
            addCriterion("express_status <", value, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andExpressStatusLessThanOrEqualTo(Integer value) {
            addCriterion("express_status <=", value, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andExpressStatusIn(List<Integer> values) {
            addCriterion("express_status in", values, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andExpressStatusNotIn(List<Integer> values) {
            addCriterion("express_status not in", values, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andExpressStatusBetween(Integer value1, Integer value2) {
            addCriterion("express_status between", value1, value2, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andExpressStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("express_status not between", value1, value2, "expressStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_express_trace_record
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_express_trace_record
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}