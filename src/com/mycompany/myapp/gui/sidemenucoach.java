/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ghassen
 */
public abstract class sidemenucoach extends Form  {
     public sidemenucoach(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public sidemenucoach(String title) {
        super(title);
    }

    public sidemenucoach() {
    }

    public sidemenucoach(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  Jennifer Wilson", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD,  e -> new StatsForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Evennement", FontImage.MATERIAL_TRENDING_UP,  e -> new StatsForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Formation", FontImage.MATERIAL_ACCESS_TIME,  e -> new StatsForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Routine", FontImage.MATERIAL_ACCESS_TIME,  e -> new StatsForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Rendez-vous", FontImage.MATERIAL_ACCESS_TIME,  e -> new StatsForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
    
}
