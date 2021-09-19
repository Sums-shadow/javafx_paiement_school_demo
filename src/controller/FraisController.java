/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import database.database;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FraisController implements Initializable {

    @FXML
    private Label ltotal;
    @FXML
    private Label lreste;
    @FXML
    private Label ldate;
    @FXML
    private TextField tmontant;
    @FXML
    private JFXButton btnValider;
    @FXML
    private ImageView irecu;
    @FXML
    private Label lnom;
    @FXML
    private Label lmontant;
    String nom, mt, reste;
    @FXML
    private Label lerror;
    @FXML
    private Pane p2;
    Connection con;
    database db = new database();

    int mat;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
    @FXML
    private ImageView ireturn;
    @FXML
    private StackPane root;
    @FXML
    private Label ldate2;
    @FXML
    private ImageView ilogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = db.getcon();
 btnValider.setDisable(true);
        // TODO
        irecu.setImage(new Image("/images/recu.png"));
        ilogo.setImage(new Image("/images/logo.jpg"));

        ireturn.setImage(new Image("/images/close.png"));

        tmontant.textProperty().addListener((o, old, newv) -> {
            if (!newv.equals("")) {
                if (Integer.parseInt(newv) > Integer.parseInt(this.reste)) {
                    lerror.setText("Saisissez un montant valide");
                    btnValider.setDisable(true);
                } else {
                    lerror.setText("");
                    btnValider.setDisable(false);
                    lmontant.setText(newv + "$");
                }
            } else {
                lmontant.setText("0$");
            }

        });
    }

    @FXML
    private void onMontant(InputMethodEvent event) {
        System.out.println("Montant " + tmontant.getText());
    }

    @FXML
    private void onValider(ActionEvent event) {
        printr();
    }

    public void initdata(int mat, String nom, String mt, String reste) {
        this.nom = nom;
        this.mt = mt;
        this.reste = reste;
        this.mat = mat;
        lnom.setText(nom);
        ltotal.setText(mt + "$");
        lreste.setText(reste + "$");
        ldate.setText(formatter1.format(calendar.getTime()) );
        ldate2.setText(formatter1.format(calendar.getTime()));

    }

    void printr() {
        WritableImage image = p2.snapshot(new SnapshotParameters(), null);

        String nameFile = "";
        nameFile = "recu/tfc_" + nom.split(" ")[0] + formatter2.format(calendar.getTime()).replace(":", "").replace("-", "") + formatter1.format(calendar.getTime()).replace(":", "").replace("-", "") + "_recto.png";

        // TODO: probably use a file chooser here
        File file = new File(nameFile);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Impression terminer");
            updateData();
        } catch (IOException e) {
            // TODO: handle exception here
            System.err.println("errerur lors de l impression " + e);
        }
    }

    private void updateData() {
        String sql = "INSERT INTO `t_paiement`( `mat`, `montant`) VALUES (?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.mat);
            ps.setInt(2, Integer.parseInt(tmontant.getText()));

            ps.execute();

            System.err.println("Success save to database");
            returnMe(1);

//            Stage ss = (Stage) lblmontant.getScene().getWindow();
//            ss.close();
//             returnMe();
        } catch (Exception e) {
            System.err.println("erreur " + e.getMessage());
        }
    }

    @FXML
    private void onReturn(MouseEvent event) {
        returnMe(0);
    }

    void returnMe(int n) {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/vue/home.fxml").openStream());
            AnchorPane anchorPane = fXMLLoader.getRoot();
            HomeController cont = fXMLLoader.getController();
            if (n == 1) {
                cont.showSnack("Paiement effectué avec succés");
                cont.setTextInput("" + this.mat);
                cont.getData();
            }

            root.getChildren().clear();
            root.getChildren().add(anchorPane);
        } catch (IOException ex) {
            System.err.println("error occured " + ex);
        }
    }

}
