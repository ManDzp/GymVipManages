package com.jm.vip.entity;

/**
 * 奖品信息
 */
public class Prize {

    /**
     * 唯一标识
     */
    private String prizeId;

    /**
     * 商品名称
     */
    private String prizeName;

    /**
     * 商品编码
     */
    private String prizeCode;

    /**
     * 单位
     */
    private String prizeUnit;

    /**
     * 状态
     */
    private String prizeStatus;

    /**
     * 备注
     */
    private String prizeRemark;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人唯一标识
     */
    private String creationId;

    /**
     * 创建时间
     */
    private String creationTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改人唯一标识
     */
    private String modificationId;

    /**
     * 修改时间
     */
    private String modificationTime;

    /**
     * 逻辑删除 1 表示删除，0 表示未删除
     */
    private String logicDelete;

    public String getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public String getPrizeCode() {
        return prizeCode;
    }

    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode == null ? null : prizeCode.trim();
    }

    public String getPrizeUnit() {
        return prizeUnit;
    }

    public void setPrizeUnit(String prizeUnit) {
        this.prizeUnit = prizeUnit == null ? null : prizeUnit.trim();
    }

    public String getPrizeStatus() {
        return prizeStatus;
    }

    public void setPrizeStatus(String prizeStatus) {
        this.prizeStatus = prizeStatus == null ? null : prizeStatus.trim();
    }

    public String getPrizeRemark() {
        return prizeRemark;
    }

    public void setPrizeRemark(String prizeRemark) {
        this.prizeRemark = prizeRemark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreationId() {
        return creationId;
    }

    public void setCreationId(String creationId) {
        this.creationId = creationId == null ? null : creationId.trim();
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getModificationId() {
        return modificationId;
    }

    public void setModificationId(String modificationId) {
        this.modificationId = modificationId == null ? null : modificationId
                .trim();
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getLogicDelete() {
        return logicDelete;
    }

    public void setLogicDelete(String logicDelete) {
        this.logicDelete = logicDelete == null ? null : logicDelete.trim();
    }

    @Override
    public String toString() {
        return "Prize [prizeId=" + prizeId + ", prizeName=" + prizeName
                + ", prizeCode=" + prizeCode + ", prizeUnit=" + prizeUnit
                + ", prizeStatus=" + prizeStatus + ", creator=" + creator
                + ", creationId=" + creationId + ", creationTime="
                + creationTime + ", modifier=" + modifier + ", modificationId="
                + modificationId + ", modificationTime=" + modificationTime
                + ", logicDelete=" + logicDelete + "]";
    }

}