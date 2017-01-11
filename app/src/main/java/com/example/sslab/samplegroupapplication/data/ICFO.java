package com.example.sslab.samplegroupapplication.data;

import org.json.JSONObject;

/**
 * Created by SSLAB on 2016-12-23.
 */

public abstract class ICFO {
    protected String id;
    protected String rescode;
    protected String resmsg;
    protected String approvalYn;
    protected String	ikenId	;           //	영업사원 ID

    protected JSONObject jsonObject = new JSONObject();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    abstract public JSONObject getJSONParams();

    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

    public String getResmsg() {
        return resmsg;
    }

    public void setResmsg(String resmsg) {
        this.resmsg = resmsg;
    }

    public String getApprovalYn() {
        return approvalYn;
    }

    public void setApprovalYn(String approvalYn) {
        this.approvalYn = approvalYn;
    }

    public String getIkenId() {
        return ikenId;
    }

    public void setIkenId(String ikenId) {
        this.ikenId = ikenId;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
