package com.incident.system.dto;

public class ResolutionRequest {
    private String rootCause;
    private String workaround;
    private String actionTaken;
    private boolean addToKB;

    public String getRootCause() { return rootCause; }
    public void setRootCause(String rootCause) { this.rootCause = rootCause; }
    public String getWorkaround() { return workaround; }
    public void setWorkaround(String workaround) { this.workaround = workaround; }
    public String getActionTaken() { return actionTaken; }
    public void setActionTaken(String actionTaken) { this.actionTaken = actionTaken; }
    public boolean isAddToKB() { return addToKB; }
    public void setAddToKB(boolean addToKB) { this.addToKB = addToKB; }
}
