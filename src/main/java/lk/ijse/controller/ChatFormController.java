package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ChatFormController {

    @FXML
    private ScrollPane ScrollPane;

    @FXML
    private Button imageButton;

    @FXML
    private TextField massageBox;

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
