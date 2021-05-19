/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;

import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.ProfileForm;
import com.mycompany.myapp.gui.Profileclient;
import com.mycompany.myapp.gui.Profilecoach;
import static com.mycompany.myapp.utils.Statics.BASE_URL;
import java.io.IOException;
import java.util.Map;



/**
 *
 * @author Ajengui
 */
public class UserService {
    public User user = new User();
    public static UserService instance=null;
    String json;
    private ConnectionRequest req;
    
    public static UserService getInstance(){
        if(instance==null){
            instance= new UserService();
        }
        return instance;
    }
    
     public UserService(){
         req=new ConnectionRequest();
     }
     
     
     public String getPasswordEmail(TextField email,Resources res){
        String url=BASE_URL+"/service/getPasswordEmail?email="+email.getText();
        req=new ConnectionRequest(url,false);
        System.out.println(url);
        req.setUrl(url);
        
        req.addResponseListener(e-> {
          JSONParser j = new JSONParser();
                 json = new String(req.getResponseData())+"";
                try {
                    
                        
                        Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        
                        
                    
                } catch (IOException ex) {        
                    ex.printStackTrace();
                }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       return json;
    }
     
    public void Register(TextField email, TextField password, TextField nom, TextField prenom, TextField login, TextField image, TextField sexe, Picker Date_naiss, TextField type, Resources res){
        String url=BASE_URL+"/service/register?email="+email.getText().toString()+
                                    "&password="+password.getText().toString()+
                                    "&nom="+nom.getText().toString()+
                                    "&prenom="+prenom.getText().toString()+
                                    "&login="+login.getText().toString()+
                                    "&image="+image.getText().toString()+
                                    "&sexe="+sexe.getText().toString()+
                                    "&Date_naiss="+Date_naiss.getText().toString()+
                                    "&type="+type.getText().toString();
        System.out.println(url);
        req.setUrl(url);
        
        req.addResponseListener(e-> {
         byte[]data=(byte[]) e.getMetaData();
         String rep=new String(data);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
     
    
    public User Login(TextField login, TextField password,Resources res) {
        String url = BASE_URL + "/service/login?login="+login.getText()+"&password="+password.getText();
        req.setUrl(url);
        req.addResponseListener(e->{
                JSONParser j = new JSONParser();
                 json = new String(req.getResponseData())+"";
                try {
                    if(json.equals("failed")){
                        Dialog.show("echec login","email ou password incorrect","OK",null);
                    }
                    else{
                        
                       Map<String,Object> userLog = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        if(userLog.size()>0){
                          if (userLog.get("type").toString().equals("Admin")) {
                              new ProfileForm(res).show();
                          } else if (userLog.get("type").toString().equals("Client")) {
                              new Profileclient(res).show();
                          }else{
                              new Profilecoach(res).show();
                          }
                            
                        }
                        
                    }
                } catch (IOException ex) {        
                    ex.printStackTrace();
                }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return null;
        }
        
    

    
   
}
