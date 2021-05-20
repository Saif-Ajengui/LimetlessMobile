/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.UserSession;
import com.mycompany.myapp.gui.ProfileForm;
import com.mycompany.myapp.gui.Profileclient;
import com.mycompany.myapp.gui.Profilecoach;
import static com.mycompany.myapp.utils.Statics.BASE_URL;
import com.oschrenk.utils.StringUtils;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Ajengui
 */
public class UserService {
    public static int idConnect;
    User sesion=UserSession.getCurrentSession();
    public User user = new User();
    public static UserService instance=null;
    String json;
    private ConnectionRequest req;
    public ArrayList<User> users;
     public boolean resultOK;
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
                              String idF=userLog.get("id").toString();
                              String output = idF.substring(0, idF.indexOf('.'));
                              idConnect=Integer.parseInt(output);
                          } else if (userLog.get("type").toString().equals("Client")) {
                              new Profileclient(res).show();
                              String idF=userLog.get("id").toString();
                              String output = idF.substring(0, idF.indexOf('.'));
                              idConnect=Integer.parseInt(output);
                          }else{
                              new Profilecoach(res).show();
                              String idF=userLog.get("id").toString();
                              String output = idF.substring(0, idF.indexOf('.'));
                              idConnect=Integer.parseInt(output);
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
        
    
public boolean EditUser(int id,TextField email, TextField password, TextField nom, TextField prenom, TextField login, TextField image, TextField sexe, Picker Date_naiss, TextField type, Resources res){
       
    String url = BASE_URL +"/service/edit/"+id+"?email="+email.getText().toString()+
                                    "&password="+password.getText().toString()+
                                    "&nom="+nom.getText().toString()+
                                    "&prenom="+prenom.getText().toString()+
                                    "&login="+login.getText().toString()+
                                    "&image="+image.getText().toString()+
                                    "&sexe="+sexe.getText().toString()+
                                    "&Date_naiss="+Date_naiss.getText().toString()+
                                    "&type="+type.getText().toString();
                 
                
          System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(  e-> {
            byte[]data=(byte[]) e.getMetaData();
         String rep=new String(data);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
                 
    }
    

    public User LoginGoogle(Resources res) {
        String url = BASE_URL + "/connect/google";
        req.setUrl(url);
        req.addResponseListener(e->{
                
                              new ProfileForm(res).show();

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return null;
        }


    public ArrayList<User> profile(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

                User t=new User();
                
              t.setNom(tasksListJson.get("nom").toString());
              t.setPrenom(tasksListJson.get("prenom").toString());
              t.setEmail(tasksListJson.get("email").toString());
              t.setImage(tasksListJson.get("image").toString());
              t.setLogin(tasksListJson.get("login").toString());
              t.setPassword(tasksListJson.get("password").toString());
              t.setSexe(tasksListJson.get("sexe").toString());
              //t.setDate_naiss(tasksListJson.get("Date_naiss").toString());
              t.setType(tasksListJson.get("type").toString());
                users.add(t);

        } catch (IOException ex) {
            System.out.println(ex);
        }
        return users;
    }

    
         public ArrayList<User> getProfile(int t){
        String url =BASE_URL+"/service/profile?id="+t;
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 json = new String(req.getResponseData())+"";
                
                users = profile(json);
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;

         }
   
         
}
