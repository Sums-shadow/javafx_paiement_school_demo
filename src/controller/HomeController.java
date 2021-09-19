/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import database.database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class HomeController implements Initializable {

    @FXML
    private ImageView irecherche;
    @FXML
    private Label lclasse;
    @FXML
    private Label lniveau;
    @FXML
    private Label lsection;
    @FXML
    private Label lprenom;
    @FXML
    private Label lsexe;
    @FXML
    private Label lnom;
    @FXML
    private Label lmat;
    @FXML
    private Label lreste;
    @FXML
    private Label ldate;
    @FXML
    private Label lpayer;
    @FXML
    private Label ltotal;
    @FXML
    private JFXComboBox<String> cmbfilter;
    @FXML
    private JFXTextField trecherche;
    @FXML
    private JFXButton btnPaie;
    @FXML
    private Pane pinfo;
    @FXML
    private Pane pintro;
    @FXML
    private ImageView iintro;
    @FXML
    private Pane pnoresult;
    @FXML
    private ImageView inotfound;
    Connection con;
    database db = new database();
    String nom, mt, reste;
    int mat;
    @FXML
    private StackPane root;
    @FXML
    private AnchorPane p2;
    @FXML
    private ImageView ilogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = db.getcon();
        cmbfilter.getItems().addAll("mat_eleve", "nom", "prenom");
        cmbfilter.setLabelFloat(true);
        cmbfilter.setValue(cmbfilter.getItems().get(0));
        initImage();
        activeIntro();

    }

    @FXML
    private void onRecherche(MouseEvent event) {
        getData();
    }

    @FXML
    private void onPaie(ActionEvent event) {
       try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/vue/frais.fxml").openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            FraisController cont =fXMLLoader.getController();
            cont.initdata(mat,nom, mt, reste);
            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }

    void activeIntro() {
        pintro.setVisible(true);
        pinfo.setVisible(false);
        pnoresult.setVisible(false);
    }

    void activeResult() {
        pintro.setVisible(false);
        pinfo.setVisible(true);
        pnoresult.setVisible(false);
    }

    void activeNoResult() {
        pintro.setVisible(false);
        pinfo.setVisible(false);
        pnoresult.setVisible(true);
    }
     void activeButton() {
      btnPaie.setVisible(true);
    }
      void desactiveButton() {
       btnPaie.setVisible(false);
    }
     

    private void initImage() {
        iintro.setImage(new Image("/images/welcome.png"));
        inotfound.setImage(new Image("/images/notfound.png"));
        irecherche.setImage(new Image("/images/search.png"));
                ilogo.setImage(new Image("/images/logo.jpg"));


    }

    public void getData() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `v_eleve` where " + cmbfilter.getValue().toLowerCase().toString() + "=?");
            ps.setString(1, trecherche.getText());
            ResultSet rs = ps.executeQuery();

            int n = 1;

            if (rs.next()) {
                System.out.println("NOM " + rs.getString("nom"));
                lmat.setText("" + rs.getInt("mat_eleve"));
                lnom.setText(rs.getString("nom"));
                lprenom.setText(rs.getString("prenom"));
                lsexe.setText(rs.getString("sexe"));
                lclasse.setText("" + rs.getInt("classe"));
                lniveau.setText(rs.getString("titre_niveau"));
                if (rs.getString("titre_niveau").equals("Humanité")) {
                    getSection(rs.getInt("id_section"));
                } else {
                    lsection.setText("-");
                }
                ltotal.setText(rs.getString("frais_payer") + "$");
                getInfoPaie(rs.getInt("mat_eleve"), rs.getInt("frais_payer"));
                this.nom=rs.getString("nom")+" "+rs.getString("prenom");
                this.mt=""+rs.getInt("frais_payer");
                this.mat=rs.getInt("mat_eleve");
                
                activeResult();
                n++;
            } else {
                activeNoResult();
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }

    }

    void getSection(int n) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM `t_section` where id=?");
            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lsection.setText(rs.getString("titre"));

                activeResult();
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }

    private void getInfoPaie(int mat, int frais) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT SUM(v_paiement.montant_payer) as som FROM `v_paiement` WHERE mat_eleve=?");
            ps.setInt(1, mat);
            ResultSet rs = ps.executeQuery();
            System.err.println("MAT "+mat);
            if (rs.next()) {
                lpayer.setText(rs.getInt("som") + "$");
//                ldate.setText(rs.getString("date_payer"));
                lreste.setText("" + (frais - rs.getInt("som"))+"$");
                this.reste="" + (frais - rs.getInt("som"));
                if(rs.getInt("som")<frais){
                    activeButton();
                }else{
                    desactiveButton();
                }
            }else{
                lpayer.setText("0$$");
                ldate.setText("");
                lreste.setText(""+frais); 
                this.reste=""+frais;
                activeButton();
            }

        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }
    
       public void gotos(String path) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/vue/frais.fxml").openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }
       
       void showSnack(String msg){
            JFXSnackbar snackBar = new JFXSnackbar(p2);
      snackBar.show(msg, 4000);
       }
       public void setTextInput(String txt){
           trecherche.setText(txt);
       }
}
