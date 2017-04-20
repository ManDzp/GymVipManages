package com.jm.vip.entity;

import java.util.Date;

public class AttachmentInfo {
	
    private String blobguid;//文件唯一标识

    private String guid;//附件唯一标识

    private String attachmenttype;//附件类型

    private Short doctype;//应用代码

    private String title;//附件名称

    private Integer filelength;//附件大小

    private String fileext;//附件后缀

    private Date createtime;//创建时间

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

    public String getAttachmenttype() {
        return attachmenttype;
    }

    public void setAttachmenttype(String attachmenttype) {
        this.attachmenttype = attachmenttype == null ? null : attachmenttype.trim();
    }

    public Short getDoctype() {
        return doctype;
    }

    public void setDoctype(Short doctype) {
        this.doctype = doctype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getFilelength() {
        return filelength;
    }

    public void setFilelength(Integer filelength) {
        this.filelength = filelength;
    }

    public String getFileext() {
        return fileext;
    }

    public void setFileext(String fileext) {
        this.fileext = fileext == null ? null : fileext.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}