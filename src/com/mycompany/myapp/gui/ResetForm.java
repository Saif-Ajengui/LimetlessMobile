/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.UserService;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ajengui
 */
public class ResetForm extends Form  {
   TextField email;
    public ResetForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome, ", "WelcomeWhite"),
                new Label("Wellness", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
        
        Image profilePic = theme.getImage("oublier.png");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());
        
        email = new TextField("", "Votre Email", 100, TextField.EMAILADDR) ;
        email.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        
        Button envoyer = new Button("Envoyer");
        envoyer.setUIID("LoginButton");
        envoyer.addActionListener(e -> {
            sendMail(theme);
            new LoginForm(theme).show();
            refreshTheme();
        });
        
        Button RetourLogin = new Button("LOGIN");
        RetourLogin.setUIID("CreateNewAccountButton");
        RetourLogin.addActionListener(e -> new LoginForm(theme).show());
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,
                BorderLayout.center(email).
                        add(BorderLayout.WEST, loginIcon),
               
                envoyer,
                RetourLogin
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    } 
    
    
    
    
        public void sendMail(Resources res){
        try{
         Properties pros = new Properties();
         pros.put("mail.transport.protocol", "smtp");
         pros.put("mail.smtp.host", "smtp.gmail.com");
         pros.put("mail.smtp.auth", "true");
        
         
         pros.put("mail.smtp.port", "587");
         String user = "limitless.pidev@gmail.com"; 
         String pass = "123456Pi@";
         Session session = Session.getInstance(pros, null);
         Message msg = new MimeMessage(session); 
           msg.setFrom(new InternetAddress(user));  
           InternetAddress[] address = {new InternetAddress(email.getText().toString())};
           msg.setRecipients(Message.RecipientType.TO,address ); 
           msg.setSubject("Mot de Passe Oublié");
           
           String mp=UserService.getInstance().getPasswordEmail(email, res);
           msg.setText("Bonjour chers WellNess,"+"\n"+"\n"+"Votre Mot De Passe est : "+mp+"\n"+"\n"+"Bien Cordialement,\n" +"Equipe WellNess ");
          
           SMTPTransport st=(SMTPTransport) session.getTransport("smtps");
           st.connect("smtp.gmail.com",465,user,pass);
           st.sendMessage(msg, address);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
}
