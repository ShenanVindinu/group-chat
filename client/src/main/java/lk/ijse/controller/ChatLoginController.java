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
    double x, y = 0;
    public static String currentUserName;



    @FXML
    void enterChat() throws IOException {

            socket = new Socket("localhost", 3991);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("/usrLog//!-> "+ userNameTextField.getText());
            currentUserName = userNameTextField.getText();
            dataOutputStream.flush();

            Parent anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/chat_form.fxml")));

            //AnchorPane Movements
            anchorPane.setOnMousePressed(event -> { x = event.getSceneX();y = event.getSceneY(); });
            anchorPane.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            });

            stage = new Stage();
            stage.setScene(new Scene(anchorPane));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle(userNameTextField.getText());
            stage.setResizable(false);
            stage.show();

            userNameTextField.setText("");
    }

    @FXML
    void OpenChatOnAction(ActionEvent event) throws IOException {
        enterChat();
    }

    public static void clsStg() throws IOException {

        if (socket != null){
            socket.close();
        }

    }

}
