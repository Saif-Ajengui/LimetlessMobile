/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author Ajengui
 */
public class RegisterForm extends Form {

    public RegisterForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome, ", "WelcomeWhite"),
                new Label("Wellness", "WelcomeBlue")
        );
        
        getTitleArea().setUIID("Container");
        
        Image profilePic = theme.getImage("register.PNG");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());
        
        TextField nom = new TextField("", "Nom", 20, TextField.ANY) ;
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY) ;
        TextField email = new TextField("", "Email", 100, TextField.EMAILADDR) ;
        TextField image = new TextField("", "Image", 100, TextField.ANY) ;
       // TextField im=new TextField("", "Image", 100, TextField.ANY);
       // Button image = new Button();
        TextField login = new TextField("", "Login", 20, TextField.ANY) ;
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD) ;
        TextField sexe = new TextField("", "Sexe", 1, TextField.ANY) ;
        Picker dateNaiss =new Picker();  
        dateNaiss.setType(Display.PICKER_TYPE_DATE);
        TextField type = new TextField("", "Type", 20, TextField.ANY) ;
        
        nom.getAllStyles().setMargin(LEFT, 0);
        prenom.getAllStyles().setMargin(LEFT, 0);
        email.getAllStyles().setMargin(LEFT, 0);
        image.getAllStyles().setMargin(LEFT, 0);
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        sexe.getAllStyles().setMargin(LEFT, 0);
        dateNaiss.getAllStyles().setMargin(LEFT, 0);
        type.getAllStyles().setMargin(LEFT, 0);
        Label nomIcon = new Label("", "TextField");
        Label prenomIcon = new Label("", "TextField");
        Label emailIcon = new Label("", "TextField");
        Label imageIcon = new Label("", "TextField");
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        Label sexeIcon = new Label("", "TextField");
        Label deteNaissIcon = new Label("", "TextField");
        Label typeIcon = new Label("", "TextField");
       
        nomIcon.getAllStyles().setMargin(RIGHT, 0);
        prenomIcon.getAllStyles().setMargin(RIGHT, 0);
        emailIcon.getAllStyles().setMargin(RIGHT, 0);
        imageIcon.getAllStyles().setMargin(RIGHT, 0);
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        sexeIcon.getAllStyles().setMargin(RIGHT, 0);
        deteNaissIcon.getAllStyles().setMargin(RIGHT, 0);
        typeIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(nomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(prenomIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(emailIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(imageIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        FontImage.setMaterialIcon(sexeIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(deteNaissIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(typeIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        
        
        Button loginButton = new Button("REGISTER");
        loginButton.setUIID("LoginButton");
       /* image.addActionListener(new ActionListener() {
            ActionListener callback = e->{
   if (e != null && e.getSource() != null) {
       String filePath = (String)e.getSource();
       int pos = filePath.lastIndexOf("/");
      im.setText(filePath.substring(pos+1));
      System.out.println(filePath.substring(pos+1));
     
   }
};
            @Override
            public void actionPerformed(ActionEvent evt) {
              
                        Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
            }

            
   
        });*/
        loginButton.addActionListener(e -> {
           
            UserService.getInstance().Register(email, password, nom, prenom, login, image, sexe, dateNaiss, type, theme);

            new LoginForm(theme).show();
           
        });
        
        
        Button Retourlogin = new Button("LOGIN");
        Retourlogin.setUIID("CreateNewAccountButton");
        Retourlogin.addActionListener(e -> new LoginForm(theme).show());
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
                BorderLayout.center(nom).
                        add(BorderLayout.WEST, nomIcon),
                BorderLayout.center(prenom).
                        add(BorderLayout.WEST, prenomIcon),
                BorderLayout.center(email).
                        add(BorderLayout.WEST, emailIcon),
                BorderLayout.center(image).
                        add(BorderLayout.WEST, imageIcon),
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                BorderLayout.center(dateNaiss).
                        add(BorderLayout.WEST, deteNaissIcon),
                BorderLayout.center(sexe).
                        add(BorderLayout.WEST, sexeIcon),
                BorderLayout.center(type).
                        add(BorderLayout.WEST, typeIcon),
                loginButton,
                Retourlogin
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
    }
}

