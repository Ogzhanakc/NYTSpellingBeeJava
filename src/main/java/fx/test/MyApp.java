package fx.test;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            System.out.println("Hata: "+throwable.getMessage());
        });
        GeneratePuzzle generatePuzzle = new GeneratePuzzle(new File("C:\\Users\\mrtyl\\IdeaProjects\\NYTSpellingBeeJava\\src\\main\\java\\sozluk_v2.txt"));
        PlayPuzzle playPuzzle = new PlayPuzzle(generatePuzzle);
        List<String> harfGetir = Arrays.stream(generatePuzzle.getHarfler().split("")).toList();
        String goldenWord = harfGetir.get(0);
        ArrayList<String> harf = new ArrayList<>();
        for (int i = 1; i < harfGetir.size(); i++)
            harf.add(harfGetir.get(i));


        Label title = new Label(generatePuzzle.getHarfler()); //scene1 borderpane e yazılacak
        VBox scene1VBox = new VBox();// scene1 right ına yazılacak içinde score ve listview tutacak
        ListView correctWords = new ListView();// vbox ın 2. elemanı
        scene1VBox.getChildren().addAll(title, correctWords);

        BorderPane sceneBorderPane = new BorderPane();//kullanıcının seçeceği
        Scene scene = new Scene(sceneBorderPane, 300, 250);
        BorderPane scene1BorderPane = new BorderPane();
        scene1BorderPane.setCenter(anchorPane(harf, correctWords, goldenWord, playPuzzle));
        scene1BorderPane.setRight(scene1VBox);
        scene1BorderPane.setTop(title);
        Scene scene1 = new Scene(scene1BorderPane, 500, 500);


        //---------------------------------------------------------------------------------
        //ANA EKRANA ÇIKAN İLK SAYFA FONTLARLA OYNANACAK VAKİT KALIRSA
        Label questionLabel = new Label("Choose Your Game");
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList("AUTOMATIC", "MYSELF"));//Devamına ChoiceBox event handler
        Label infoLabel = new Label("PLEASE kelimeyi girin");
        TextField myselfTextField = new TextField();
        myselfTextField.setMaxWidth(100);
        VBox sceneVBox = new VBox(questionLabel, choiceBox, infoLabel, myselfTextField);
        sceneVBox.setSpacing(10);//vboxtaki elemanlar arasındaki boşluk
        sceneVBox.setMaxSize(150, 50);//sınır belirtmezsem ortaya almıyor tamamını kaplıyor
        sceneBorderPane.setCenter(sceneVBox);
        sceneBorderPane.setAlignment(sceneVBox, Pos.CENTER);
        Button nextButton = new Button("Next ->");
        nextButton.setOnAction(e -> {
            //OTOMATİK Mİ SEÇİLMİŞ ELLE Mİ SEÇİLMİŞ İKİSİNİN Kontrolünü ve Diğer sahneye geçişini kontrol eder
            stage.setScene(scene1);
        });
        nextButton.setPrefSize(70, 70);
        sceneBorderPane.setRight(nextButton);
        sceneBorderPane.setAlignment(nextButton, Pos.BOTTOM_RIGHT);
        //----------------------------------------------------------------------------


        stage.setScene(scene);
        stage.setTitle("Group 56 Project");
        stage.show();
    }


    private AnchorPane anchorPane(ArrayList<String> arrayList, ListView listView, String goldenWord, PlayPuzzle playPuzzle) {
        AnchorPane anchorPane = new AnchorPane();
        ArrayList<String> textFieldArray = new ArrayList<>();

        //TextField-------------------------
        TextField textField = new TextField();
        textField.maxWidth(220);
        textField.maxHeight(40);
        anchorPane.setTopAnchor(textField, 110.0);
        anchorPane.setLeftAnchor(textField, 215.0);
        // textField.setOnKeyPressed(keyEvent -> );

        //TextField-------------------------

        //GoldButton--------------------------------------------
        Button goldenButton = new Button(goldenWord);
        goldenButton.setShape(hexagonMaker());
        goldenButton.setStyle("-fx-background-color: #FFFF66; -fx-border-color: #000000");
        goldenButton.setPrefSize(70, 70);
        goldenButton.setOnAction(e -> {
            textFieldArray.add(goldenButton.getText());
            textField.setText(String.join("", textFieldArray));
        });

        anchorPane.setTopAnchor(goldenButton, 250.0);
        anchorPane.setLeftAnchor(goldenButton, 250.0);
        //GoldButton------------------------------------------


        //NormalButton1-----------------------------------------üst+
        Button normalButton1 = new Button(arrayList.get(0));
        normalButton1.setShape(hexagonMaker());
        normalButton1.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000");
        normalButton1.setPrefSize(70, 70);
        anchorPane.setTopAnchor(normalButton1, 179.0);
        anchorPane.setLeftAnchor(normalButton1, 250.0);
        normalButton1.setOnAction(e -> {
            textFieldArray.add(normalButton1.getText());
            textField.setText(String.join("", textFieldArray));
        });
        //NormalButton1-----------------------------------------

        //NormalButton2-----------------------------------------sol üst+
        Button normalButton2 = new Button(arrayList.get(1));
        normalButton2.setShape(hexagonMaker());
        normalButton2.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000");
        normalButton2.setPrefSize(70, 70);
        anchorPane.setTopAnchor(normalButton2, 215.0);
        anchorPane.setLeftAnchor(normalButton2, (250.0 - (35 * Math.sqrt(3))));
        normalButton2.setOnAction(e -> {
            textFieldArray.add(normalButton2.getText());
            textField.setText(String.join("", textFieldArray));
        });
        //NormalButton2-----------------------------------------

        //NormalButton3-----------------------------------------sol alt+
        Button normalButton3 = new Button(arrayList.get(2));
        normalButton3.setShape(hexagonMaker());
        normalButton3.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000");
        normalButton3.setPrefSize(70, 70);
        anchorPane.setTopAnchor(normalButton3, 285.0);
        anchorPane.setLeftAnchor(normalButton3, (250.0 - (35 * Math.sqrt(3))));
        normalButton3.setOnAction(e -> {
            textFieldArray.add(normalButton3.getText());
            textField.setText(String.join("", textFieldArray));
        });
        //NormalButton3-----------------------------------------

        //NormalButton4-----------------------------------------alt
        Button normalButton4 = new Button(arrayList.get(3));
        normalButton4.setShape(hexagonMaker());
        normalButton4.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000");
        normalButton4.setPrefSize(70, 70);
        anchorPane.setTopAnchor(normalButton4, 321.0);
        anchorPane.setLeftAnchor(normalButton4, 250.0);
        normalButton4.setOnAction(e -> {
            textFieldArray.add(normalButton4.getText());
            textField.setText(String.join("", textFieldArray));
        });
        //NormalButton4-----------------------------------------

        //NormalButton5-----------------------------------------sağ alt
        Button normalButton5 = new Button(arrayList.get(4));
        normalButton5.setShape(hexagonMaker());
        normalButton5.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000");
        normalButton5.setPrefSize(70, 70);
        anchorPane.setTopAnchor(normalButton5, 285.0);
        anchorPane.setLeftAnchor(normalButton5, (250.0 + (35 * Math.sqrt(3))));
        normalButton5.setOnAction(e -> {
            textFieldArray.add(normalButton5.getText());
            textField.setText(String.join("", textFieldArray));
        });
        //NormalButton5-----------------------------------------

        //NormalButton6-----------------------------------------sağ üst
        Button normalButton6 = new Button(arrayList.get(5));
        normalButton6.setShape(hexagonMaker());
        normalButton6.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000");
        normalButton6.setPrefSize(70, 70);
        anchorPane.setTopAnchor(normalButton6, 215.0);
        anchorPane.setLeftAnchor(normalButton6, (250.0 + (35 * Math.sqrt(3))));
        normalButton6.setOnAction(e -> {
            textFieldArray.add(normalButton6.getText());
            textField.setText(String.join("", textFieldArray));
        });
        //NormalButton6-----------------------------------------


        //EnterButton----------------------------------
        Button enterButton = new Button("Enter");
        enterButton.setPrefSize(70, 40);
        enterButton.setOnAction(e -> {
            if (!(playPuzzle.isPivotUsed(textField.getText())) | !(playPuzzle.isExist(textField.getText())) |
                    !(playPuzzle.contains(textField.getText())) | !(playPuzzle.isFound(textField.getText())) | (listView.getItems().contains(textField.getText()))) {
                textField.clear();
                textFieldArray.clear();
                System.out.println("YOK");
            } else {
                listView.getItems().addAll(textField.getText());
                textField.clear();
                textFieldArray.clear();
            }
        });

        //EnterButton----------------------------------

        //DeleteButton----------------------------------
        Button deleteButton = new Button("Delete");
        deleteButton.setPrefSize(70, 40);
        deleteButton.setOnAction(e -> {
            textField.clear();
            textFieldArray.clear();
        });
        //DeleteButton----------------------------------

        //MixButton----------------------------------
        Button mixButton = new Button("Mix");
        mixButton.setPrefSize(70, 40);
        mixButton.setOnAction(e -> {
            Collections.shuffle(arrayList);
            normalButton1.setText(arrayList.get(0));
            normalButton2.setText(arrayList.get(1));
            normalButton3.setText(arrayList.get(2));
            normalButton4.setText(arrayList.get(3));
            normalButton5.setText(arrayList.get(4));
            normalButton6.setText(arrayList.get(5));
        });
        //MixButton----------------------------------

        HBox controlButtons = new HBox(enterButton, deleteButton, mixButton);
        controlButtons.setSpacing(5);
        anchorPane.setTopAnchor(controlButtons, 415.0);
        anchorPane.setLeftAnchor(controlButtons, 175.0);


        anchorPane.getChildren().addAll(goldenButton, normalButton1, normalButton2, normalButton3, normalButton4, normalButton5, normalButton6, controlButtons, textField);
        return anchorPane;
    }


    public Polygon hexagonMaker() {
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
