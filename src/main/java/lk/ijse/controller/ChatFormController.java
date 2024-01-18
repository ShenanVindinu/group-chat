package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
        setName();
        massage();
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

    private void massage() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 3991);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (socket.isConnected()) {
                    hBox = new HBox(12);
                    message = dataInputStream.readUTF();
                    if (message.endsWith(".jpg") || message.endsWith(".jpeg") || message.endsWith(".png") || message.endsWith(".gif")) {
                        Platform.runLater(() -> {
                            String path = splitImg(message);
                            File file = new File(path);
                            Image image = new Image(file.toURI().toString());
                            ImageView img = new ImageView(image);
                            img.setFitWidth(150);
                            img.setFitHeight(150);
                            if (usr.equals(userNameText.getText())) {
                                HBox hBox1 = new HBox();
                                hBox1.setPadding(new Insets(5, 5, 5, 10));
                                hBox1.getChildren().add(img);
                                hBox1.setAlignment(Pos.CENTER_RIGHT);

                                vBox.getChildren().add(hBox1);
                            } else {
                                HBox hBox1 = new HBox();
                                hBox1.setAlignment(Pos.CENTER_LEFT);
                                Text text = new Text(usr);
                                hBox1.getChildren().add(text);
                                label = new Label(usr + " :\n\n");
                                label.setGraphic(img);

                                HBox hBox2 = new HBox();
                                hBox2.setAlignment(Pos.CENTER_LEFT);
                                hBox2.setPadding(new Insets(5, 5, 5, 10));
                                hBox2.getChildren().add(img);

                                Platform.runLater(() -> {
                                    vBox.getChildren().add(hBox1);
                                    vBox.getChildren().add(hBox2);
                                });
                            }
                        });
                    } else {
                        splitMsg(message);
                        String preparedMsg = makeMsg();
                        if (usr.equals(userNameText.getText())) {

                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.CENTER_RIGHT);
                            hBox.setPadding(new Insets(5, 5, 5, 10));

                            Text text = new Text(preparedMsg);
                            TextFlow textFlow = new TextFlow(text);
                            textFlow.setStyle("-fx-background-color: #abb8c3; -fx-font-weight: bold; -fx-background-radius: 20px");
                            textFlow.setPadding(new Insets(5, 10, 5, 10));
                            text.setFill(Color.color(0, 0, 0));

                            hBox.getChildren().add(textFlow);

                            Platform.runLater(() -> {
                                vBox.getChildren().add(hBox);
                            });

                        } else {
                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 5, 0, 10));

                            Text text = new Text(preparedMsg);
                            TextFlow textFlow = new TextFlow(text);
                            textFlow.setStyle("-fx-background-color: #0693e3; -fx-font-weight: bold; -fx-color: white; -fx-background-radius: 20px");
                            textFlow.setPadding(new Insets(5, 10, 5, 10));
                            text.setFill(Color.color(1, 1, 1));

                            hBox.getChildren().add(textFlow);

                            HBox hBoxName = new HBox();
                            hBoxName.setAlignment(Pos.CENTER_LEFT);
                            Text textName = new Text(usr);
                            TextFlow textFlowName = new TextFlow(textName);

                            hBoxName.getChildren().add(textFlowName);

                            Platform.runLater(() -> {
                                vBox.getChildren().add(hBoxName);
                                vBox.getChildren().add(hBox);
                            });
                        }
                    }
                    scrollPane.vvalueProperty().bind(vBox.heightProperty());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private String splitImg(String message) {
        String[] words = message.split("!!!!split!!!!");
        this.usr = words[0];
        return words[1];
    }

    private String makeMsg() {
        wordList.remove(0);
        return String.join(" ", wordList);
    }

    private void splitMsg(String message) {
        String[] words = message.split(" ");
        this.usr = words[0];
        wordList = new ArrayList<>(Arrays.asList(words));
    }

    private void setName() {
        userNameText.setText(ChatLoginController.currentUserName);
    }




}
