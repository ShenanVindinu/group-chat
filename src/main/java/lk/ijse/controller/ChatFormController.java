package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatFormController implements Initializable {

    @FXML
    private Button clsButton;

    @FXML
    private Button imageButton;

    @FXML
    private TextField massageBox;

    @FXML
    private Button minimizeButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button sendButton;

    @FXML
    private Text userNameText;

    @FXML
    private VBox vBox;

    private String message;

    private String usr;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private ArrayList<String> wordList;
    Label label;
    HBox hBox;


    {
        message = "";
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    void clsOnActon(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF("/usrLogOut//!-> "+userNameText.getText());
        dataOutputStream.flush();
    }

    @FXML
    void minimizeOnAction(ActionEvent event) {

    }

    @FXML
    void scrlOnMouseEntered(MouseEvent event) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    @FXML
    void scrlOnMouseExited(MouseEvent event) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    @FXML
    void sendImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("image file","*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String imageAbsalutePath = selectedFile.getAbsolutePath();
            dataOutputStream.writeUTF(userNameText.getText() + "!!!!split!!!!" + imageAbsalutePath);
            dataOutputStream.flush();
        }
    }

    @FXML
    void sendMassage(ActionEvent event) {

    }

}
