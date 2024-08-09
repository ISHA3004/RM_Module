/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demojersey.RMmodule;

/**
 *
 * @author ISHA MISTRY
 */
public class RMUserStatusDTO {
    private int rmid;
    private int userid;
    private String username;
    private String applStatus;
    private String adminStage;

    public int getRmid() {
        return rmid;
    }

    public void setRmid(int rmid) {
        this.rmid = rmid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplStatus() {
        return applStatus;
    }

    public void setApplStatus(String applStatus) {
        this.applStatus = applStatus;
    }

    public String getAdminStage() {
        return adminStage;
    }

    public void setAdminStage(String adminStage) {
        this.adminStage = adminStage;
    }
    
    
}
