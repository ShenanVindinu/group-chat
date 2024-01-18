package lk.ijse.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class ChatLoginController {

    @FXML
    private Button enterChatButton;

    @FXML
    private TextField userNameTextField;


    static Socket socket;
    DataOutputStream dataOutputStream;
    Stage stage;


    @FXML
    void enterChat(ActionEvent event) throws IOException {

            socket = new Socket("localhost", 3991);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("/usrLog//!-> "+ userNameTextField.getText());
            dataOutputStream.flush();
            Parent anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/chat_form.fxml")));


            stage = new Stage();
            stage.setScene(new Scene(anchorPane));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle(userNameTextField.getText());
            stage.setResizable(false);
            stage.show();

            userNameTextField.setText("");
    }

}
