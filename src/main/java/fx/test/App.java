package fx.test;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane BP = new BorderPane();
        Scene scene1 = new Scene(BP, 500, 500);

        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList("AUTOMATIC", "MYSELF"));


        choiceBox.setOnAction(e -> {});
        ListView listView = new ListView<>();
        //BP.setCenter(anchorPane(listView));
        Label titleLabel = new Label("OUR SPEELİNG BEEE");
        Label label = new Label("SCORE");

        BP.setTop(titleLabel);
        BP.setAlignment(titleLabel, Pos.TOP_CENTER);
        VBox vBox = new VBox(label, listView);
        BP.setRight(vBox);
        BorderPane firstBorderPane = new BorderPane();

        Button nextButton = new Button("NEXT");

        nextButton.setOnAction(e -> {

            stage.setScene(scene1);
        });
        firstBorderPane.setRight(nextButton);
        firstBorderPane.setAlignment(nextButton, Pos.BOTTOM_CENTER);
        firstBorderPane.setCenter(choiceBox);
        firstBorderPane.setAlignment(choiceBox, Pos.CENTER);


        Scene scene = new Scene(firstBorderPane, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Hello World");
        stage.show();
    }


    public AnchorPane anchorPane(ListView listView,ArrayList<String> dizi) {
        AnchorPane anchorPane = new AnchorPane();
        TextField textField = new TextField();

        Button gButton = goldenButton();

        Button nButton1 = normalButton().get(0);
        nButton1.setOnAction(e -> {
            //textField.textProperty().bind(nButton1.textProperty());

        });

        Button nButton2 = normalButton().get(1);
        Button nButton3 = normalButton().get(2);
        Button nButton4 = normalButton().get(3);
        Button nButton5 = normalButton().get(4);
        Button nButton6 = normalButton().get(5);
        HBox controlButtons = controlButtons(listView, textField,dizi,normalButton());


        anchorPane.setTopAnchor(textField, 25.0);
        anchorPane.setLeftAnchor(textField, 175.0);

        anchorPane.setTopAnchor(gButton, 125.0);
        anchorPane.setLeftAnchor(gButton, 225.0);


        anchorPane.setTopAnchor(nButton1, 75.0);
        anchorPane.setLeftAnchor(nButton1, 225.0);
//--------------------------------------------------------------------------------------------------
        anchorPane.setTopAnchor(nButton2, 100.0);
        anchorPane.setLeftAnchor(nButton2, (225.0 - (2 * (12.5 * Math.sqrt(3)))));

        anchorPane.setTopAnchor(nButton3, 150.0);
        anchorPane.setLeftAnchor(nButton3, (225.0 - (2 * (12.5 * Math.sqrt(3)))));

        anchorPane.setTopAnchor(nButton4, 175.0);
        anchorPane.setLeftAnchor(nButton4, 225.0);

        anchorPane.setTopAnchor(nButton5, 100.0);
        anchorPane.setLeftAnchor(nButton5, (225.0 + (2 * (12.5 * Math.sqrt(3)))));

        anchorPane.setTopAnchor(nButton6, 150.0);
        anchorPane.setLeftAnchor(nButton6, (225.0 + (2 * (12.5 * Math.sqrt(3)))));

        anchorPane.setTopAnchor(controlButtons, 250.0);
        anchorPane.setLeftAnchor(controlButtons, 145.5);


        anchorPane.getChildren().addAll(gButton, nButton1, nButton2, nButton3, nButton4, nButton5, nButton6, controlButtons, textField);

        return anchorPane;

    }


    public Button goldenButton(/*ORTADAKİ HARF */) {
        Button button = new Button();
        button.setStyle("-fx-background-color: #FFFF66; -fx-border-color: #000000");
        button.setPrefSize(50, 50);
        button.setShape(hexagon());
        return button;

    }

    public ArrayList<Button> normalButton() {
        ArrayList<Button> buttons = new ArrayList<>();


        for (int i = 0; i < 6; i++) {

            buttons.add(new Button());
            buttons.get(i).setShape(hexagon());
            buttons.get(i).setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000");
            buttons.get(i).setPrefSize(50, 50);

        }
        return buttons;
    }

    public HBox controlButtons(ListView listView, TextField textField,ArrayList<String> dizi,ArrayList<Button> nButton) {

        Button enterButton = new Button("Enter");

        enterButton.setOnAction(e -> {
            listView.getItems().addAll(textField.getText());
        });
        enterButton.setPrefSize(70, 30);
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            textField.clear();
        });
        deleteButton.setPrefSize(70, 30);
        Button mixButton = new Button("Mix");
        mixButton.setOnAction(e -> {});
        mixButton.setPrefSize(70, 30);
        HBox hBox = new HBox(enterButton, deleteButton, mixButton);
        hBox.setSpacing(5);

        return hBox;

    }

    public Polygon hexagon() {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                20.0, 5.0,
                40.0, 5.0,
                45.0, 15.0,
                40.0, 25.0,
                20.0, 25.0,
                15.0, 15.0,
        });
        return polygon;
    }






}
