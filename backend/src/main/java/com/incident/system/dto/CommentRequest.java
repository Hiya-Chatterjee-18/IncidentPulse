package com.incident.system.dto;

public class CommentRequest {
    private String text;
    private boolean isInternal;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public boolean isInternal() { return isInternal; }
    public void setInternal(boolean internal) { isInternal = internal; }
}
