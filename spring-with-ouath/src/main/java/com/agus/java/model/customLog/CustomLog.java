package com.agus.java.model.customLog;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = CustomLog.ENTITY_NAME)
@Table(name = CustomLog.TABLE_NAME)
public class CustomLog implements Serializable {

    private static final long serialVersionUID = -1L;
    public static final String TABLE_NAME = "m_log";
    public static final String ENTITY_NAME = "com.agus.java.model.log.Log";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(name = "action_name")
    private String actionName;

    @Column(name = "client_id")
    private String clienId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "api_get_url")
    private String apiGetUri;

    @Column(name = "command_as_json")
    private String commandJson;

    @Column(name = "request_date")
    private Date requestDate;

    @Column(name = "response_as_json")
    private String responseJson;


    @Column(name = "response_date")
    private Date responseDate;

    @Column(name = "result_remark")
    private Integer resultRemark;


    public String getActionName() {
        return actionName;
    }

    public String getClienId() {
        return clienId;
    }

    public String getUserId() {
        return userId;
    }

    public String getApiGetUri() {
        return apiGetUri;
    }

    public String getCommandJson() {
        return commandJson;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public Integer getResultRemark() {
        return resultRemark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public void setClienId(String clienId) {
        this.clienId = clienId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setApiGetUri(String apiGetUri) {
        this.apiGetUri = apiGetUri;
    }

    public void setCommandJson(String commandJson) {
        this.commandJson = commandJson;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public void setResultRemark(Integer resultRemark) {
        this.resultRemark = resultRemark;
    }

}
