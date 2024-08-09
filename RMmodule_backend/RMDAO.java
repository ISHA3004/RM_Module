/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demojersey.RMmodule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ISHA MISTRY
 */
public class RMDAO {
    
    Connection conn = null;
    public RMDAO(){
        String url = "jdbc:mysql://localhost:3306/externalapi";
        String username = "root";
        String password = "";
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
            if(conn != null)
                System.out.println("Successfull Connection");
            else
                System.out.println("Connection failed");
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
         catch (ClassNotFoundException e) {
            System.out.println("Class not found "+e);
        }
    }
    
    public String getToken(int userid)
    {
        String sql = "select token from rmlogin where rmid = ?";
        try{
            int pos=1;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(pos++,userid);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                return rs.getString(1);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    public RMLoginDTO userLogin(RMLoginDTO rmlogindto)
    {
        String sql = "select rmid,rmpassword,token from rmlogin where rmusername=?";
        try{
            int ind=1;
            String dbrmPassword=null;
            int dbrmUserid=0;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(ind++,rmlogindto.getUsername());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                ind=1;
                dbrmUserid = rs.getInt(ind++);
                dbrmPassword = rs.getString(ind++);
                
                if((rmlogindto.getPassword()).equals(dbrmPassword))
                {
                    rmlogindto.setRmid(dbrmUserid);
                    rmlogindto.setToken(rs.getString(ind++));
                }
                return rmlogindto;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    public List<RMUserStatusDTO> getUserStatus(int rmid)
    {
        List<RMUserStatusDTO> userStatusList = new ArrayList<>();
        String sql = "select userid,username,applStatus,adminStage from rmuserStatus where rmid = ?";
        try{
            int pos=1;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(pos++,rmid);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next())
            {
                pos=1;
                RMUserStatusDTO rmUserStatus = new RMUserStatusDTO();
                rmUserStatus.setUserid(rs.getInt(pos++));
                rmUserStatus.setUsername(rs.getString(pos++));
                rmUserStatus.setApplStatus(rs.getString(pos++));
                rmUserStatus.setAdminStage(rs.getString(pos++));
                
                userStatusList.add(rmUserStatus);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return userStatusList;
    }
}
