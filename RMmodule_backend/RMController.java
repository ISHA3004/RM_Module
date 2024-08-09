/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demojersey.RMmodule;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author ISHA MISTRY
 */

@Path("rm")
public class RMController {
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response rmLogin(JSONObject logindetails)
    {
        RMLoginDTO rmlogindto = new RMLoginDTO();
        rmlogindto.setUsername((String)logindetails.get("username"));
        rmlogindto.setPassword((String)logindetails.get("password"));
        
        System.out.println(rmlogindto.getUsername()+" "+rmlogindto.getPassword());
        RMservice rmservice = new RMservice();
        rmlogindto = rmservice.isValidUsernamePassword(rmlogindto);
        JSONObject res = new JSONObject();
        if(rmlogindto != null)
        {
            res.put("rmid",rmlogindto.getRmid());
            res.put("token",rmlogindto.getToken());
            res.put("status","success");
            return Response.status(200).entity(res.toString()).build();
        }
        else
        {
            res.put("status","failed");
            return Response.status(400).entity(res.toString()).build();
        }
    }
    
    @POST
    @Path("getUserStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserStatus(@HeaderParam ("token") String token,JSONObject details)
    {
        RMservice rmservice = new RMservice();
        if(!rmservice.isValidToken(token,((BigDecimal)details.get("rmid")).intValue()))
        {
            return Response.status(400).entity("Session time out").build();
        }
        
        int rmid = ((BigDecimal)details.get("rmid")).intValue();
        List<RMUserStatusDTO> rmUserStatusList = rmservice.getUserStatus(rmid);
        
        if(!rmUserStatusList.isEmpty())
        {
            JSONArray resList = new JSONArray();
            
            for(RMUserStatusDTO rmUserStatusDTO : rmUserStatusList)
            {
                JSONObject rmuserStatusObj = new JSONObject();
                rmuserStatusObj.put("userid",rmUserStatusDTO.getUserid());
                rmuserStatusObj.put("username",rmUserStatusDTO.getUsername());
                rmuserStatusObj.put("applStatus",rmUserStatusDTO.getApplStatus());
                rmuserStatusObj.put("adminStage",rmUserStatusDTO.getAdminStage());
                
                resList.add(rmuserStatusObj);
            }
            
            JSONObject res = new JSONObject();
            res.put("userStatus",resList);
            res.put("status","success");
            
            return Response.status(200).entity(res.toString()).build();
        }
        else
        {
            JSONObject res = new JSONObject();
            res.put("status","success");
            
            return Response.status(200).entity(res.toString()).build();
        }
    }
}
