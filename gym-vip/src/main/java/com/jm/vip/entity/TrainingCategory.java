package com.jm.vip.entity;

/**
 * 训练分类
 * 
 * @author jingjq
 */
public class TrainingCategory extends BaseEntity {

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 训练分类名称
     */
    private String name;

    /**
     * 训练分类编码
     */
    private String code;

    /**
     * 状态 0：启用，1：停用
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}