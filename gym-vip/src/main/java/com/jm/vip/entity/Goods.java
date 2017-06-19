package com.jm.vip.entity;

import java.math.BigDecimal;

/**
 * 商品信息
 */
public class Goods {

    /**
     * 唯一标识
     */
    private String goodsId;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 单位
     */
    private String goodsUnit;

    /**
     * 单价
     */
    private BigDecimal goodsPrice;

    /**
     * 状态
     */
    private String goodsStatus;

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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus == null ? null : goodsStatus.trim();
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
        return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName
                + ", goodsCode=" + goodsCode + ", goodsUnit=" + goodsUnit
                + ", goodsPrice=" + goodsPrice + ", goodsStatus=" + goodsStatus
                + ", creator=" + creator + ", creationId=" + creationId
                + ", creationTime=" + creationTime + ", modifier=" + modifier
                + ", modificationId=" + modificationId + ", modificationTime="
                + modificationTime + ", logicDelete=" + logicDelete + "]";
    }

}