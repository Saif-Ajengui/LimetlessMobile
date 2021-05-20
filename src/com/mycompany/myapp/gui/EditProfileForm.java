/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.UserSession;
import com.mycompany.myapp.services.UserService;

import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author Ajengui
 */
public class EditProfileForm extends SideMenuBaseForm {
     User user=UserSession.getCurrentSession();
     
    public EditProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e ->new ProfileForm(res).show());
        
        Container remainingTasks = BoxLayout.encloseY(
                        new Label("12", "CenterTitle"),
                        new Label("remaining tasks", "CenterSubTitle")
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("32", "CenterTitle"),
                        new Label("completed tasks", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("Jennifer Wilson", "Title"),
                                    new Label("UI/UX Designer", "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                        
        add(new Label("Profile", "TodayTitle"));
         FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        
        

        ArrayList<User> array2=UserService.getInstance().getProfile(UserService.idConnect);
        ArrayList<String> Anom = new ArrayList<>();
        ArrayList<String> Aprenom = new ArrayList<>();
        ArrayList<String> Aemail = new ArrayList<>();
        ArrayList<String> Aimage = new ArrayList<>();
        ArrayList<String> Alogin = new ArrayList<>();
        ArrayList<String> Apassword = new ArrayList<>();
        ArrayList<String> ADateNaiss = new ArrayList<>();
        ArrayList<String> Asexe = new ArrayList<>();
        ArrayList<String> Atype = new ArrayList<>();
       
        for(User t:array2){
           Anom.add(t.getNom());
           Aprenom.add(t.getPrenom());
           Aemail.add(t.getEmail());
           Aimage.add(t.getImage());
           Alogin.add(t.getLogin());
           Apassword.add(t.getPassword());
           ADateNaiss.add(t.getDate_naiss());
           Asexe.add(t.getSexe());
           Atype.add(t.getType()); 
        }
        Label Lnom = new Label("Votre Nom :");
        
        TextField nom = new TextField();
        nom.setUIID("TextFieldBlack");
        nom.setText(Anom.get(0).toString());
        Label Lprenom = new Label("Votre Prénom :");
        
        TextField prenom = new TextField();
        prenom.setUIID("TextFieldBlack");
        prenom.setText(Aprenom.get(0).toString());
        Label Lemail = new Label("Votre Email :");
        TextField email = new TextField();
        email.setUIID("TextFieldBlack");
        email.setText(Aemail.get(0).toString());
        Label Limage = new Label("Votre Image :");
        TextField image = new TextField();
        image.setUIID("TextFieldBlack");
        image.setText(Aimage.get(0).toString());
        Label Llogin = new Label("Votre Login :");
        TextField login = new TextField();
        login.setUIID("TextFieldBlack");
        login.setText(Alogin.get(0).toString());
        Label Lpassword = new Label("Votre Password :");
        TextField password = new TextField();
        password.setUIID("TextFieldBlack");
        password.setText(Apassword.get(0).toString());
        //TextField path = new TextField("");
        Label Ldate= new Label("Votre Date De Naissance :");
        Picker DateNaiss =new Picker();  
        
        //TextField DateNaiss = new TextField(UserSession.getCurrentSession().getDate_naiss());
        DateNaiss.setUIID("TextFieldBlack");
        //DateNaiss.setText(ADateNaiss.get(0).toString());
        Label Lsexe= new Label("Votre Sexe :");
        TextField sexe = new TextField();
        sexe.setUIID("TextFieldBlack");
        sexe.setText(Asexe.get(0).toString());
        Label Ltype = new Label("Votre Type :");
        TextField type = new TextField();
        type.setUIID("TextFieldBlack");
        type.setText(Atype.get(0).toString());
        Button modifier = new Button("Modifier");
       modifier.setUIID("LoginButton");
        addAll(Lemail,email, Lpassword,password, Lnom,nom, Lprenom, prenom, Llogin, login,Limage, image,Lsexe, sexe, Ldate, DateNaiss,Ltype, type,modifier);
        
        
        modifier.addActionListener(e -> {
           
            UserService.getInstance().EditUser(UserService.idConnect,email, password, nom, prenom, login, image, sexe, DateNaiss, type, res);

            new EditProfileForm(res).show();
           
        });
        /*modifier.addActionListener(ev -> {
           User upd =new User(UserService.idConnect,nom.getText(),prenom.getText(),email.getText(),login.getText(),password.getText(),image.getText(),sexe.getText(),DateNaiss.getText(),type.getText());
            if (UserService.getInstance().EditUser(upd)) {
                Dialog.show("Success", "Book Edited", new Command("OK"));
                 new EditProfileForm(upd,res).show();
            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });*/
        
        
        
        
         //picture.addActionListener(e -> {
           // i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            //if (i != null) {
            //    Image im;
            //    try {
            //        im = Image.createImage(i);
            //        im = im.scaled(res.getImage("photo-profile.jpg").getWidth(),
            //                res.getImage("photo-profile.jpg").getHeight());
                    
             //       System.out.println(i);
             //       path.setText(i);

             //   } catch (IOException ex) {
             //       System.out.println("Could not load image!");
              //  }
            //}
        //});
         
        // modifier.addActionListener(e -> {
           
    //        UserService.getInstance().EditUser(email, password, nom, prenom, login, image, sexe, DateNaiss, type, res);

            
           
        //});
        
        
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
}
