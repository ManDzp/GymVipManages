package com.jm.vip.entity;

/**
 * 私教信息
 */
public class PersonalTraining {

    /**
     * 唯一标识
     */
    private String personalTrainingId;

    /**
     * 商品名称
     */
    private String personalTrainingName;

    /**
     * 商品编码
     */
    private String personalTrainingCode;

    /**
     * 状态
     */
    private String personalTrainingStatus;

    /**
     * 备注
     */
    private String personalTrainingRemark;

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

    public String getPersonalTrainingId() {
        return personalTrainingId;
    }

    public void setPersonalTrainingId(String personalTrainingId) {
        this.personalTrainingId = personalTrainingId;
    }

    public String getPersonalTrainingName() {
        return personalTrainingName;
    }

    public void setPersonalTrainingName(String personalTrainingName) {
        this.personalTrainingName = personalTrainingName == null ? null
                : personalTrainingName.trim();
    }

    public String getPersonalTrainingCode() {
        return personalTrainingCode;
    }

    public void setPersonalTrainingCode(String personalTrainingCode) {
        this.personalTrainingCode = personalTrainingCode == null ? null
                : personalTrainingCode.trim();
    }

    public String getPersonalTrainingStatus() {
        return personalTrainingStatus;
    }

    public void setPersonalTrainingStatus(String personalTrainingStatus) {
        this.personalTrainingStatus = personalTrainingStatus == null ? null
                : personalTrainingStatus.trim();
    }

    public String getPersonalTrainingRemark() {
        return personalTrainingRemark;
    }

    public void setPersonalTrainingRemark(String personalTrainingRemark) {
        this.personalTrainingRemark = personalTrainingRemark == null ? null
                : personalTrainingRemark.trim();
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
        return "PersonalTraining [personalTrainingId=" + personalTrainingId
                + ", personalTrainingName=" + personalTrainingName
                + ", personalTrainingCode=" + personalTrainingCode
                + ", personalTrainingStatus=" + personalTrainingStatus
                + ", personalTrainingRemark=" + personalTrainingRemark
                + ", creator=" + creator + ", creationId=" + creationId
                + ", creationTime=" + creationTime + ", modifier=" + modifier
                + ", modificationId=" + modificationId + ", modificationTime="
                + modificationTime + ", logicDelete=" + logicDelete + "]";
    }

}