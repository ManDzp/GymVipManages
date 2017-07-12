package com.jm.vip.entity;

import java.util.Date;

public class BaseEntity {

    private String creator;

    private String creationId;

    private Date creationTime;

    private String modifier;

    private String modificationId;

    private Date modificationTime;

    private String logicDelete;

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

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
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

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getLogicDelete() {
        return logicDelete;
    }

    public void setLogicDelete(String logicDelete) {
        this.logicDelete = logicDelete == null ? null : logicDelete.trim();
    }
}
