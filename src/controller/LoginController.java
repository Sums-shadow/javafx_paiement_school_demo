/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import database.database;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginController implements Initializable {
    @FXML
    private AnchorPane p2;
    @FXML
    private ImageView ilogo;
    @FXML
    private JFXTextField t_user;
    @FXML
    private JFXPasswordField t_pass;
    @FXML
    private JFXButton btnLogin;
Connection con;
    database db = new database();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         con = db.getcon();
        ilogo.setImage(new Image("/images/logo.jpg"));
    }    

    @FXML
    private void onLogin(ActionEvent event) {
         connecterMoi();
    }
    
    
      private void connecterMoi() {

        String username = t_user.getText();
        String password = t_pass.getText();
        String sql = "SELECT * FROM `t_auth` WHERE `email`=? AND `password`=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                showSnack("Connection reussie");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/vue/home.fxml"));
                AnchorPane catcont = loader.load();

                Stage s = new Stage();
                s.setScene(new Scene(catcont));

                s.show();
                Stage ss = (Stage) btnLogin.getScene().getWindow();
                ss.close();
            } else {
                showSnack("Echec de connection");
            }
        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }

    public void showSnack(String msg) {
        JFXSnackbar snackBar = new JFXSnackbar(p2);
      snackBar.show(msg, 4000);
    }
    
}
