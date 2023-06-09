// Import necessary JavaFX libraries

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
// Import additional Java libraries
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The carApp program provides a GUI that accepts the following car data from the user input: ID, Make, Model, HP.
 * It temporarily stores the data and offers the possibility to edit, delete and display the stored cars as a list.
 *
 * @author <a href="https://github.com/Mengil">Mengil's magic</a>
 * @version 1.0.0 - beta
 * @since 2023-06-08
 */
public class Main extends Application {
    private ArrayList<Car> carList;
    private ListView<Car> carListView;
    private Label errorLabel;
    private int selectedCarIndex = -1;
    private final String textFieldRegex = "\\d+";
    private final ObservableList<Car> carItemsObservableList = FXCollections.observableArrayList();

    /**
     * Program entry point
     * Initializes the JavaFX environment and calls the "start" method to display the GUI.
     * Starts the JavaFX application and initializes the JavaFX runtime.
     *
     * @param args String Array as parameter to pass arguments to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * GUI entry point
     * The "start" method of the Application class is overridden to allow custom implementation in a safe way.
     *
     * @param primaryStage window to display the GUI.
     */
    @Override
    public void start(Stage primaryStage) {
        DropShadow buttonDropShadow = createButtonDropShadow(1, 1, 3, Color.web("#303030"));
        DropShadow buttonDropShadowEnter = createButtonDropShadow(3, 3, 12, Color.web("#303030"));
        DropShadow buttonDropShadowClick = createButtonDropShadow(0, 0, 3, Color.GRAY);
        Font helv13 = new Font("Helvetica", 13);
        Font helv15 = new Font("Helvetica", 15);
        Font helv26 = new Font("Helvetica", 26);
        Font gara15 = new Font("Garamond", 15);
        Insets carListPadding = new Insets(6, 5, 6, 5);
        Insets buttonPadding = new Insets(6, 11, 6, 11);
        Insets buttonMenuVBoxPadding = new Insets(7, 0, 8, 13);
        Insets textInputGridPanePadding = new Insets(7, 13, 8, 13);
        Insets headerPadding = new Insets(17, 0, 18, 34);
        Color errorColor = Color.rgb(187, 38, 73);
        Color lightColor = Color.rgb(255, 170, 30);      //#FFAA1E
        Color darkColor = Color.rgb(48, 48, 48);
        Background headerBackground = new Background(new BackgroundFill(darkColor, CornerRadii.EMPTY, Insets.EMPTY));
        Border errorBorder = new Border(new BorderStroke(Color.web("#303030"), BorderStrokeStyle.SOLID, null, new BorderWidths(1, 0, 1, 0)));

        carList = new ArrayList<>();
        VBox parentVBox = new VBox();
        Label labelHeaderParentVBox = new Label(("Car  Management").toUpperCase());
        labelHeaderParentVBox.setPrefWidth(Double.MAX_VALUE);
        labelHeaderParentVBox.setPadding(headerPadding);
        labelHeaderParentVBox.setBackground(headerBackground);
        labelHeaderParentVBox.setFont(helv26);
        labelHeaderParentVBox.setAlignment(Pos.CENTER_LEFT);
        labelHeaderParentVBox.setTextFill(lightColor);
        labelHeaderParentVBox.setFocusTraversable(true);
        labelHeaderParentVBox.requestFocus();

        HBox childHBox = new HBox();
        VBox.setVgrow(childHBox, Priority.ALWAYS);
        VBox buttonMenuVBox = new VBox();
        buttonMenuVBox.setPadding(buttonMenuVBoxPadding);
        buttonMenuVBox.setSpacing(13);
        buttonMenuVBox.setPrefHeight(35);
        buttonMenuVBox.setPrefWidth(103);
        buttonMenuVBox.setAlignment(Pos.CENTER);
        Button viewButton = new Button("view cars".toUpperCase());
        Button addButton = new Button("add car".toUpperCase());
        Button deleteButton = new Button("del car".toUpperCase());
        Button loadButton = new Button("load car".toUpperCase());
        Button saveButton = new Button("save car".toUpperCase());

        Button[] buttonArray = {viewButton, addButton, deleteButton, loadButton, saveButton};
        for (Button button : buttonArray) {
            button.setFont(helv13);
            button.setPadding(buttonPadding);
            button.setPrefWidth(buttonMenuVBox.getPrefWidth());
            button.setMinWidth(buttonMenuVBox.getPrefWidth());
            button.setStyle("-fx-background-color: #303030;");
            button.setTextFill(Color.WHITE);
            button.setEffect(buttonDropShadow);

            button.setOnMouseEntered(mouseEvent -> {
                button.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #303030; ");
                button.setEffect(buttonDropShadowEnter);
            });
            button.setOnMouseExited(mouseEvent -> {
                button.setStyle("-fx-background-color: #303030; -fx-text-fill: #FFFFFF; ");
                button.setFont(helv13);
                button.setEffect(buttonDropShadow);
                button.setTranslateY(0);
            });
            button.setOnMouseClicked(mouseEvent -> {
                button.setStyle("-fx-background-color: #FFAA1E; -fx-text-fill: #000000;");
                button.setEffect(buttonDropShadowClick);
                button.setTranslateY(1);
            });
        }
        buttonMenuVBox.getChildren().addAll(buttonArray);
        GridPane textInputGridPane = new GridPane();
        textInputGridPane.setPadding(textInputGridPanePadding);
        textInputGridPane.setVgap(8);
        textInputGridPane.setAlignment(Pos.CENTER);
        errorLabel = new Label();
        errorLabel.setText("");
        errorLabel.setFont(helv15);
        errorLabel.setBorder(errorBorder);
        errorLabel.setMaxWidth(Double.MAX_VALUE);
        errorLabel.setMinWidth(250);
        errorLabel.setPrefHeight(buttonMenuVBox.getPrefHeight());
        errorLabel.setAlignment(Pos.CENTER);
        TextField idTextField = new TextField();
        idTextField.setPromptText("id".toUpperCase());
        TextField hpTextField = new TextField();
        hpTextField.setPromptText("hp".toUpperCase());
        TextField brandTextField = new TextField();
        brandTextField.setPromptText("brand".toUpperCase());
        TextField modelTextField = new TextField();
        modelTextField.setPromptText("model".toUpperCase());
        AtomicBoolean isInputANumber = new AtomicBoolean(false);
        TextField[] textFieldArray = {idTextField, hpTextField};

        for (TextField textField : textFieldArray) {
            textField.textProperty().addListener((observable, oldValue, newValue) ->
            {
                boolean idFieldMatchRegex = idTextField.getText().matches(textFieldRegex);
                boolean hpFieldMatchRegex = hpTextField.getText().matches(textFieldRegex);
                if (!newValue.isEmpty()) {
                    if (!idFieldMatchRegex && !hpFieldMatchRegex) {
                        showErrorOutput("isNoNumber", errorColor);
                        hpTextField.setStyle("-fx-text-fill: red;");
                        idTextField.setStyle("-fx-text-fill: red;");
                        isInputANumber.set(false);
                    } else if (idFieldMatchRegex && !hpFieldMatchRegex) {
                        showErrorOutput("isNoNumber", errorColor);
                        hpTextField.setStyle("-fx-text-fill: red;");
                        idTextField.setStyle("-fx-text-fill: green;");
                        isInputANumber.set(false);
                    } else if (!idFieldMatchRegex) {
                        showErrorOutput("isNoNumber", errorColor);
                        hpTextField.setStyle("-fx-text-fill: green;");
                        idTextField.setStyle("-fx-text-fill: red;");
                        isInputANumber.set(false);
                    } else {
                        showErrorOutput("correctInput", errorColor);
                        hpTextField.setStyle("-fx-text-fill: green;");
                        idTextField.setStyle("-fx-text-fill: green;");
                        isInputANumber.set(true);
                    }
                } else {
                    showErrorOutput("emptyField", errorColor);
                }
            });
        }
        ArrayList<TextField> textFieldArrayList = new ArrayList<>(Arrays.asList(idTextField, hpTextField, brandTextField, modelTextField));
        for (TextField textField : textFieldArrayList) {
            if (textFieldArrayList.indexOf(textField) <= 1) {
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
        textInputGridPane.add(errorLabel, 0, 0);
        textInputGridPane.add(idTextField, 0, 1);
        textInputGridPane.add(brandTextField, 0, 2);
        textInputGridPane.add(modelTextField, 0, 3);
        textInputGridPane.add(hpTextField, 0, 4);
        carListView = new ListView<>();
        carListView.setMinWidth(450);
        childHBox.getChildren().addAll(buttonMenuVBox, textInputGridPane, carListView);
        parentVBox.getChildren().addAll(labelHeaderParentVBox, childHBox);
        addButton.setOnAction(event -> {
            if (textFieldsAreEmpty(idTextField, hpTextField, modelTextField, brandTextField)) {
                showErrorOutput("emptyField", errorColor);
            } else if (!isInputANumber.get()) {
                showErrorOutput("isNoNumber", errorColor);
            } else if (idExistCheck(parseTextToInt(idTextField))) {
                showErrorOutput("idExists", errorColor);
            } else {
                Integer id = parseTextToInt(idTextField);
                String brand = brandTextField.getText();
                String model = modelTextField.getText();
                int hp = parseTextToInt(idTextField);
                showErrorOutput("correctInput", errorColor);
                Car car = new Car(id, brand, model, hp);
                carList.add(car);
                carListView.setItems(carItemsObservableList);
                carListView.getItems().add(car);
                carListView.setPadding(carListPadding);
                clearFields(idTextField, brandTextField, modelTextField, hpTextField);
                errorLabel.setTextFill(Color.GREEN);
                errorLabel.setText("car added".toUpperCase());
                carListView.refresh();
            }
        });
        loadButton.setOnAction(event -> {
            Car selectedCar = carListView.getSelectionModel().getSelectedItem();
            showErrorOutput("correctInput", errorColor);
            if (selectedCar != null) {
                selectedCar.getCar();
                selectedCarIndex = carList.indexOf(selectedCar);
                parseIntToText(idTextField, selectedCar.getId());
                int hp = selectedCar.getHp();
                parseIntToText(hpTextField, hp);
                brandTextField.setText(selectedCar.getBrand());
                modelTextField.setText(selectedCar.getModel());
                errorLabel.setTextFill(errorColor);
                errorLabel.setText("save car to apply changes".toUpperCase());
            } else {
                showErrorOutput("noCarSelected", errorColor);
            }
        });
        saveButton.setOnAction(event -> {
            if (textFieldsAreEmpty(idTextField, hpTextField, modelTextField, brandTextField)) {
                showErrorOutput("emptyField", errorColor);
            } else if (selectedCarIndex == -1) {
                showErrorOutput("noCarSelected", errorColor);
            } else if (!isInputANumber.get()) {
                showErrorOutput("isNoNumber", errorColor);
            } else {
                Integer id = (parseTextToInt(idTextField));
                String brand = brandTextField.getText();
                String model = modelTextField.getText();
                int hp = parseTextToInt(hpTextField);
                Car carAtIndex = carListView.getItems().get(selectedCarIndex);
                if (idExistCheck(id) && (!Objects.equals(carAtIndex.getId(), id))) {
                    showErrorOutput("idExists", errorColor);
                } else {
                    carAtIndex.setId(id);
                    carAtIndex.setHp(hp);
                    carAtIndex.setBrand(brand);
                    carAtIndex.setModel(model);
                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("loaded car got saved".toUpperCase());
                    carListView.refresh();
                }
            }
        });
        viewButton.setOnAction(event -> {
            carListView.setItems(carItemsObservableList);
            carListView.setPadding(carListPadding);
            errorLabel.setText("");
            clearFields(idTextField, brandTextField, modelTextField, hpTextField);
        });
        deleteButton.setOnAction(event -> {
            int selectedCarIndex = carListView.getSelectionModel().getSelectedIndex();
            if (carListView.getSelectionModel().getSelectedItem() == null) {
                showErrorOutput("noCarSelected", errorColor);
            } else {
                if (selectedCarIndex >= 0) {
                    carList.remove(selectedCarIndex);
                    carListView.getItems().remove(selectedCarIndex);
                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("car removed".toUpperCase());
                }
            }
        });

        textFieldInputDummyData(1, 10, "-Testmarke-", "-Testmodell" );

        showMainWindow(parentVBox, 852, 400, primaryStage, "CarApp_");
    }

    //----- methods -----//
    private void showErrorOutput(String errorType, Color color) {
        errorLabel.setTextFill(color);
        switch (errorType) {
            case "idExists" -> errorLabel.setText("ID already exists".toUpperCase());
            case "emptyField" -> errorLabel.setText("at least one field is empty".toUpperCase());
            case "noCarSelected" -> errorLabel.setText("no car selected in the list".toUpperCase());
            case "isNoNumber" -> errorLabel.setText("ID and HP must be numbers".toUpperCase());
            case "correctInput" -> errorLabel.setText("");
        }
    }

    private boolean idExistCheck(Integer textFieldID) {
        boolean idExists = false;
        for (Car car : carList) {
            Integer carID = car.getId();
            if (textFieldID.equals(carID)) {
                idExists = true;
                break;
            }
        }
        return idExists;
    }

    public static DropShadow createButtonDropShadow(double offsetX, double offsetY, double radius, Color color) {
        DropShadow buttonDropShadow = new DropShadow();
        buttonDropShadow.setColor(color);
        buttonDropShadow.setOffsetX(offsetX);
        buttonDropShadow.setOffsetY(offsetY);
        buttonDropShadow.setRadius(radius);
        return buttonDropShadow;
    }

    public boolean textFieldsAreEmpty(TextField field1, TextField field2, TextField field3, TextField field4) {
        return field1.getText().isEmpty() || field2.getText().isEmpty() || field3.getText().isEmpty() || field4.getText().isEmpty();
    }

    public int parseTextToInt(TextField textField) {
        return Integer.parseInt(textField.getText().trim());
    }

    public void parseIntToText(TextField textField, int id) {
        textField.setText(Integer.toString(id));
    }

    public void clearFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

    public void showMainWindow(VBox vbox, double width, double height, Stage primaryStage, String title) {
        Scene scene = new Scene(vbox, width, height);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(title);
        primaryStage.show();
    }

    public void textFieldInputDummyData(Integer testCarsStartID, Integer testCarsEndID, String brand, String model){
        for (int i = testCarsStartID; i <= testCarsEndID; i++) {
            String newBrand = brand + i;
            String newModel = model + i;
            Car car = new Car(i, newBrand, newModel, i);
            carList.add(car);
            carItemsObservableList.add(car);
        }
    }

}
