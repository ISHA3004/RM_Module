/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demojersey.RMmodule;

import java.util.List;

/**
 *
 * @author ISHA MISTRY
 */
public class RMservice {
    
    public RMLoginDTO isValidUsernamePassword(RMLoginDTO rmlogindto)
    {
        String password = rmlogindto.getPassword();
        
        if(password.length() == 8)
        {
            boolean digit = false,alphabet=false,specialChar=false;
            char[] pass = password.toCharArray();
            for(int i=0;i<pass.length;i++)
            {
                if(Character.isDigit(pass[i]))
                    digit = true;
                else if(Character.isLetter(pass[i]))
                    alphabet = true;
                else
                    specialChar = true;
            }
            if(digit && alphabet && specialChar)
            {
                RMDAO rmdao = new RMDAO();
                rmlogindto = rmdao.userLogin(rmlogindto);
                System.out.println("Service mei "+rmlogindto.toString());
             
                return rmlogindto;
            }
        }
        return null;
    }
    
    public boolean isValidToken(String token,int userid)
    {
        RMDAO rmdao = new RMDAO();
        System.out.println("func token "+token);
        System.out.println("db token "+rmdao.getToken(userid));
        return token.equals(rmdao.getToken(userid));
    }
    
    public List<RMUserStatusDTO> getUserStatus(int rmid)
    {
        RMDAO rmdao = new RMDAO();
        return rmdao.getUserStatus(rmid);
    }
}
