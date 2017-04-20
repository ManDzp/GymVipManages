package com.jm.vip.entity;

public class BlobInfo {
	
    private String blobguid;//文件唯一标识

    private String guid;//附件唯一标识

    private String content;//内容

    public String getBlobguid() {
        return blobguid;
    }

    public void setBlobguid(String blobguid) {
        this.blobguid = blobguid == null ? null : blobguid.trim();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}