// Sven Mayer - ITMO2202

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    private ArrayList<Car> carList;
    private ListView<Car> listView;
    private Label errorLabel;
    private int selectedIndex = -1;
    private final String regex = "\\d+";
    private final ObservableList<Car> items = FXCollections.observableArrayList();
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
//--//----------//----------//----------//----------//----------//
//---------- TABLE OF CONTENTS | STRUCTURE ----------//
//----- stlye stuff: shadows|fonts|paddings|colors|backgrounds|borders|car arraylist

//----- window content
//------|---[wrapperBox]
//----------|-----[wrapperHeaderLabel]
//----------|-----[bodyBox]
//----------------|----[menuBox]
//---------------------|----[Buttons]
//---------------------|----[buttonArray]
//----------------|----[menuBox] add elements
//----------------|----[inputGridPane]
//---------------------|----[errorLabel]
//---------------------|----[TextFields]
//---------------------|----|----[ArrayList]
//----------------|----[inputGridPane] add elements
//----------------|----[listView]
//----------|-----[bodyBox] add elements
//------|---[wrapperBox] add elements
//----------|-----[Buttons eventHandling]
//----- [dummy data]
//----- scene and primarystage

//methods
//--//----------//----------//----------//----------//----------//

    //----- style stuff -----//
        //  dropShadow default
        DropShadow dropShadow = createDropShadow(1, 1, 3, Color.web("#303030"));
        //  dropShadowEnter
        DropShadow dropShadowEnter = createDropShadow(3, 3, 12, Color.web("#303030"));
        //  dropShadowClick
        DropShadow dropShadowClick = createDropShadow(0, 0, 3, Color.GRAY);
        //  fonts
        Font helv13 = new Font("Helvetica", 13);
        Font helv15 = new Font("Helvetica", 15);
        Font helv26 = new Font("Helvetica", 26);
        Font gara15 = new Font("Garamond", 15);
        //  paddings
        Insets paddingList = new Insets(6, 5, 6, 5);
        Insets paddingButton = new Insets(6, 11, 6, 11);
        Insets paddingBox = new Insets(7, 0, 8, 13);
        Insets paddingBox2 = new Insets(7, 13, 8, 13);
        Insets paddingHeader = new Insets(17, 0, 18, 34);
        //  colors
        Color errorColor = Color.rgb(187, 38, 73);
        Color light = Color.rgb(255, 170, 30);      //#FFAA1E
        Color dark =  Color.rgb(48, 48, 48);
        //  backgrounds
        Background elementBg = new Background(new BackgroundFill(dark, CornerRadii.EMPTY, Insets.EMPTY));
        //  borders
        Border errorBorder = new Border(new BorderStroke(Color.web("#303030"), BorderStrokeStyle.SOLID, null, new BorderWidths(1, 0, 1, 0)));
        //  car arrayList for all cars
        carList = new ArrayList<>();

    //----- window content -----//
    // [wrapperBox]
        VBox wrapperBox = new VBox();
            // [wrapperBox wrapperHeaderLabel]
            Label wrapperHeaderLabel = new Label(("Car  Management").toUpperCase());
            wrapperHeaderLabel.setPrefWidth(Double.MAX_VALUE);
            wrapperHeaderLabel.setPadding(paddingHeader);
            wrapperHeaderLabel.setBackground(elementBg);
            wrapperHeaderLabel.setFont(helv26);
            wrapperHeaderLabel.setAlignment(Pos.CENTER_LEFT);
            wrapperHeaderLabel.setTextFill(light);
            wrapperHeaderLabel.setFocusTraversable(true);
            wrapperHeaderLabel.requestFocus();
            // [wrapperBox bodyBox]
            HBox bodyBox = new HBox();
            VBox.setVgrow(bodyBox, Priority.ALWAYS);
                // [wrapperBox]->[bodyBox menuBox]
                VBox menuBox = new VBox();
                menuBox.setPadding(paddingBox);
                menuBox.setSpacing(13);
                menuBox.setPrefHeight(35);
                menuBox.setPrefWidth(103);
                menuBox.setAlignment(Pos.CENTER);
                    //  [wrapperBox]->[bodyBox]->[menuBox Buttons]
                    Button addButton = new Button("add car".toUpperCase());
                    Button loadButton = new Button("load car".toUpperCase());
                    Button saveButton = new Button("save car".toUpperCase());
                    Button deleteButton = new Button("del car".toUpperCase());
                    Button showButton = new Button("show cars".toUpperCase());
                        //  [wrapperBox]->[bodyBox]->[menuBox buttonArray]
                        Button[] buttonArray = { addButton, deleteButton, loadButton, saveButton, showButton };
                        for (Button button : buttonArray) {
                            button.setFont(helv13);
                            button.setPadding(paddingButton);
                            button.setPrefWidth(menuBox.getPrefWidth());
                            button.setMinWidth(menuBox.getPrefWidth());
                            button.setStyle("-fx-background-color: #303030;");
                            button.setTextFill(Color.WHITE);
                            button.setEffect(dropShadow);
                            button.setOnMouseEntered(mouseEvent -> {
                                button.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #303030; ");
                                button.setEffect(dropShadowEnter);
                            });
                            button.setOnMouseExited(mouseEvent -> {
                                button.setStyle("-fx-background-color: #303030; -fx-text-fill: #FFFFFF; ");
                                button.setFont(helv13);
                                button.setEffect(dropShadow);
                                button.setTranslateY(0);
                            });
                            button.setOnMouseClicked(mouseEvent -> {
                                button.setStyle("-fx-background-color: #FFAA1E; -fx-text-fill: #000000;");
                                button.setEffect(dropShadowClick);
                                button.setTranslateY(1);
                            });
                        }
            // [wrapperBox]->[bodyBox]->[menuBox] add elements
                menuBox.getChildren().addAll(buttonArray);
                // [wrapperBox]->[bodyBox inputGridPane]
                GridPane inputGridPane = new GridPane();
                inputGridPane.setPadding(paddingBox2);
                inputGridPane.setVgap(8);
                inputGridPane.setAlignment(Pos.CENTER);
                    // [wrapperBox]->[bodyBox]->[inputGridPane errorLabel]
                    errorLabel = new Label();
                    errorLabel.setText("");
                    errorLabel.setFont(helv15);
                    errorLabel.setBorder(errorBorder);
                    errorLabel.setMaxWidth(Double.MAX_VALUE);
                    errorLabel.setMinWidth(250);
                    errorLabel.setPrefHeight(menuBox.getPrefHeight());
                    errorLabel.setAlignment(Pos.CENTER);
                    // [wrapperBox]->[bodyBox]->[inputGridPane TextFields]
                    TextField idTextField = new TextField();
                        idTextField.setPromptText("id".toUpperCase());
                    TextField hpTextField = new TextField();
                        hpTextField.setPromptText("hp".toUpperCase());
                    TextField brandTextField = new TextField();
                        brandTextField.setPromptText("brand".toUpperCase());
                    TextField modelTextField = new TextField();
                        modelTextField.setPromptText("model".toUpperCase());
                    AtomicBoolean isNumber = new AtomicBoolean(false);
                    TextField[] textFields = {idTextField, hpTextField};
                    for (TextField textField : textFields) {
                        textField.textProperty().addListener((observable, oldValue, newValue) ->
                        {
                            boolean idFieldValid = idTextField.getText().matches(regex);
                            boolean hpFieldValid = hpTextField.getText().matches(regex);
                            if(!newValue.isEmpty()) {
                                if(!idFieldValid && !hpFieldValid){
                                    showError("noNumber", errorColor);
                                    hpTextField.setStyle("-fx-text-fill: red;");
                                    idTextField.setStyle("-fx-text-fill: red;");
                                    isNumber.set(false);
                                } else if (idFieldValid && !hpFieldValid) {
                                    showError("noNumber", errorColor);
                                    hpTextField.setStyle("-fx-text-fill: red;");
                                    idTextField.setStyle("-fx-text-fill: green;");
                                    isNumber.set(false);
                                } else if (!idFieldValid) {
                                    showError("noNumber", errorColor);
                                    hpTextField.setStyle("-fx-text-fill: green;");
                                    idTextField.setStyle("-fx-text-fill: red;");
                                    isNumber.set(false);
                                } else {
                                    showError("correctInput", errorColor);
                                    hpTextField.setStyle("-fx-text-fill: green;");
                                    idTextField.setStyle("-fx-text-fill: green;");
                                    isNumber.set(true);
                                }
                            }else{
                                showError("isEmpty", errorColor);
                            }
                        });
                    }
                        // [wrapperBox]->[bodyBox]->[inputGridPane]->[TextFields ArrayList]
                        ArrayList<TextField> elementList = new ArrayList<>(Arrays.asList(idTextField, hpTextField, brandTextField, modelTextField));
                        for (TextField textField : elementList) {
                            if (elementList.indexOf(textField) <= 1) {
                                textField.setTextFormatter(new TextFormatter<>(change -> {
                                    if (change.getControlNewText().length() <= 4) {
                                        return change;
                                    }
                                    return null;
                                }));
                                textField.setMaxWidth(60);
                            } else {
                                textField.setTextFormatter(new TextFormatter<>(change -> {
                                    if (change.getControlNewText().length() <= 15) {
                                        return change;
                                    }
                                    return null;
                                }));
                            }
                            textField.setPrefWidth(230);
                            textField.setFont(gara15);
                            Tooltip toolTip = new Tooltip("enter the " + textField.getPromptText());
                            Tooltip.install(textField, toolTip);
                            toolTip.setShowDelay(Duration.millis(34));
                            toolTip.setStyle("-fx-background-color: #FFAA1E; -fx-text-fill: #303030; -fx-font-size: 15px; -fx-padding: 5px 13px");
                        }
                // [wrapperBox]->[bodyBox]->[inputGridPane] add elements
                inputGridPane.add(errorLabel, 0, 0);
                inputGridPane.add(idTextField, 0, 1);
                inputGridPane.add(brandTextField, 0, 2);
                inputGridPane.add(modelTextField, 0, 3);
                inputGridPane.add(hpTextField, 0, 4);
                // [wrapperBox]->[bodyBox listView]
                listView = new ListView<>();
                listView.setMinWidth(450);
            // [wrapperBox]->[bodyBox] add elements
            bodyBox.getChildren().addAll(menuBox, inputGridPane, listView);
        //----- [wrapperBox] add elements
        wrapperBox.getChildren().addAll(wrapperHeaderLabel, bodyBox);
    //  [wrapperBox]->[bodyBox]->[menuBox]->[Buttons eventHandling]
        addButton.setOnAction(event -> {
            //boolean areEmpty = false;
            if (areEmpty(idTextField, hpTextField, modelTextField, brandTextField)) {
                showError("isEmpty", errorColor);
            } else if(!isNumber.get()) {
                showError("noNumber", errorColor);
            } else if(idExists(textToInt(idTextField))){
                showError("idExist", errorColor);
            }else{
                Integer id = textToInt(idTextField);
                String brand = brandTextField.getText();
                String model = modelTextField.getText();
                int hp = textToInt(idTextField);
                showError("correctInput", errorColor);
                Car car = new Car(id, brand, model, hp);
                carList.add(car);
                listView.setItems(items);
                listView.getItems().add(car);
                listView.setPadding(paddingList);
                clearFields(idTextField, brandTextField, modelTextField, hpTextField);
                errorLabel.setTextFill(Color.GREEN);
                errorLabel.setText("car added".toUpperCase());
                listView.refresh();
            }
        });
        loadButton.setOnAction(event -> {
            Car selectedCar = listView.getSelectionModel().getSelectedItem();
            showError("correctInput", errorColor);
            if (selectedCar != null) {
                selectedCar.getCar();
                selectedIndex = carList.indexOf(selectedCar);
                intToText(idTextField, selectedCar.getId());
                int hp = selectedCar.getHp();
                intToText(hpTextField, hp);
                brandTextField.setText(selectedCar.getBrand());
                modelTextField.setText(selectedCar.getModel());
                errorLabel.setTextFill(errorColor);
                errorLabel.setText("save car to apply changes".toUpperCase());
            }else{
                showError("noCar", errorColor);
            }
        });
        saveButton.setOnAction(event -> {
            if(areEmpty(idTextField, hpTextField, modelTextField, brandTextField)) {
                showError("isEmpty", errorColor);
            }else if(selectedIndex == -1){
                showError("noCar", errorColor);
            }else if(!isNumber.get()) {
                showError("noNumber", errorColor);
            }else{
                Integer id = (textToInt(idTextField));
                String brand = brandTextField.getText();
                String model = modelTextField.getText();
                int hp = textToInt(hpTextField);
                Car carAtIndex = listView.getItems().get(selectedIndex);
                if (idExists(id) && (!Objects.equals(carAtIndex.getId(), id))) {
                    showError("idExist", errorColor);
                } else {
                    carAtIndex.setId(id);
                    carAtIndex.setHp(hp);
                    carAtIndex.setBrand(brand);
                    carAtIndex.setModel(model);
                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("loaded car got saved".toUpperCase());
                    listView.refresh();
                }
            }
        });
        showButton.setOnAction(event-> {
            listView.setItems(items);
            listView.setPadding(paddingList);
            errorLabel.setText("");
            clearFields(idTextField, brandTextField, modelTextField, hpTextField);
        });
        deleteButton.setOnAction(event -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if(listView.getSelectionModel().getSelectedItem() == null) {
                showError("noCar", errorColor);
            }else {
                if (selectedIndex >= 0) {
                    carList.remove(selectedIndex);
                    listView.getItems().remove(selectedIndex);
                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("car removed".toUpperCase());
                }
            }
        });

//----- [dummy data] for testing ------------//
    for(int i = 9989; i <= 9999; i++){
        String marke = "-Testmarke-" + i;
        String modell = "-Testmodell" + i;
        Car car = new Car(i, marke, modell, i);
        carList.add(car);
        items.add(car);
    }
//-------------------------------------------//

        //  Scene for showing the windows content
        Scene scene = new Scene(wrapperBox, 852, 400); //width, height
        //  Main window of the app with the scene as content
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        //  Setting the title of the Stage
        primaryStage.setTitle("CarApp_");
        //  Displaying the contents of the stage
        primaryStage.show();
    }
//----- methods -----//
//----- [showError]
    private void showError(String errorType, Color color) {
        errorLabel.setTextFill(color);
        switch (errorType) {
            case "idExist" -> errorLabel.setText("ID already exists".toUpperCase());
            case "isEmpty" -> errorLabel.setText("at least one field is empty".toUpperCase());
            case "noCar" -> errorLabel.setText("no car selected in the list".toUpperCase());
            case "noNumber" -> errorLabel.setText("ID and HP must be numbers".toUpperCase());
            case "correctInput" -> errorLabel.setText("");
        }
    }
//----- [idExists]
    private boolean idExists(Integer textFieldID){
        boolean idExists = false;
        for (Car car: carList) {
            Integer carID = car.getId();
            if (textFieldID.equals(carID)) {
                idExists = true;
                break;
            }
        }
        return idExists;
    }
//----- [dropShadow] method
    public static DropShadow createDropShadow(double offsetX, double offsetY, double radius, Color color) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(color);
        dropShadow.setOffsetX(offsetX);
        dropShadow.setOffsetY(offsetY);
        dropShadow.setRadius(radius);
        return dropShadow;
    }
//----- [areEmpty] method
    public boolean areEmpty(TextField field1, TextField field2, TextField field3, TextField field4) {
        return field1.getText().isEmpty() || field2.getText().isEmpty() || field3.getText().isEmpty() || field4.getText().isEmpty();
    }
//----- [textToInt] method
    public int textToInt(TextField textField) {
            return Integer.parseInt(textField.getText().trim());
    }
//----- [intToText] method
    public void intToText(TextField textField, int id) {
        textField.setText(Integer.toString(id));
    }
//----- [clearFields] method
    public void clearFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }
}