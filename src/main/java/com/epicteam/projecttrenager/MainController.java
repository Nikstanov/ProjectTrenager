package com.epicteam.projecttrenager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final String STRINGEXP = "Ваш опыт: ";
    int exp;
    int[] arrayLevels;
    int level = 1;
    int difficult = 1;
    int praxis;
    int needexp = 0;
    String answer;
    String answer1;

    public MainController(int exp, int[] arrayLevels){
        this.exp = exp;
        this.arrayLevels = arrayLevels;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Синий фон", "Красный фон", "Желтый фон");
        anotherfon.setItems(list);
        mainMenu.setDisable(false);
        gameMenu.setDisable(true);
        mainMenu.setVisible(true);
        gameMenu.setVisible(false);
        expmenu.setText(STRINGEXP + exp);
    }

    @FXML
    private ComboBox<String> anotherfon;

    @FXML
    private AnchorPane backfon;

    @FXML
    private AnchorPane forwardfon;

    @FXML
    public void newFon(ActionEvent event){
        switch (anotherfon.getValue()) {
            case ("Синий фон"):
                anotherfon.setStyle("-fx-background-color: LightBlue;");
                forwardfon.setStyle("-fx-background-color: SteelBlue;");
                backfon.setStyle("-fx-background-color: LightBlue;");
                break;
            case ("Красный фон"):
                anotherfon.setStyle("-fx-background-color: Brown;");
                forwardfon.setStyle("-fx-background-color: Red;");
                backfon.setStyle("-fx-background-color: Brown;");
                break;
            case ("Желтый фон"):
                anotherfon.setStyle("-fx-background-color: Goldenrod;");
                forwardfon.setStyle("-fx-background-color: Yellow;");
                backfon.setStyle("-fx-background-color: Goldenrod;");
                break;
            default:
                anotherfon.setValue("Error");
        }
    }


    @FXML
    private Button hardbutton;

    @FXML
    private Label diffenough;

    @FXML
    private void click2(ActionEvent event) {
        if (!levelenough.isVisible()) {
            diffenough.setVisible(false);
            if (difficult == 1) {
                hardbutton.setText("Сложность: Жесть");
                difficult = 2;
                needexp = 200 * (level - 1) + 100;

                if (arrayLevels[level] != 2  && level != 0) {
                    diffenough.setLayoutX(202);
                    diffenough.setText("Пройди хотя бы 2 раза на легком");
                    diffenough.setVisible(true);
                }
                else{
                    if(needexp > exp && level != 0){
                        diffenough.setText("Недостаточно опыта:" +needexp);
                        diffenough.setVisible(true);
                        diffenough.setLayoutX(220);
                    }
                }
            } else {
                hardbutton.setText("Сложность: Легкий");
                difficult = 1;
            }
        }
    }

    @FXML
    private Button levelbutton;

    @FXML
    private Label levelenough;

    @FXML
    private void click(ActionEvent event) {
        if(level < 9) {
            level = level + 1;
        }
        else{
            level = 0;
        }

        levelenough.setVisible(false);

        diffenough.setVisible(false);
        hardbutton.setText("Сложность: Легкий");
        difficult = 1;

        levelbutton.setText("Уровень: " + level);
        needexp = 200 * (level - 1);
        if(level == 0){
            needexp = 0;
        }

        if(level != 0) {
            if (arrayLevels[level] < 1) {
                levelenough.setText("Предыдущий уровень");
                levelenough.setVisible(true);
                levelenough.setLayoutX(50);
            } else {
                if (exp < needexp) {
                    levelenough.setLayoutX(79);
                    levelenough.setText("Опыт: " + needexp);
                    levelenough.setVisible(true);
                }
            }
        }
    }

    @FXML
    private Label expmenu;

    @FXML
    private AnchorPane mainMenu;

    @FXML
    private AnchorPane gameMenu;

    @FXML
    private Label question;

    @FXML
    private TextField mainTextField;



    @FXML
    private void click1(ActionEvent event){
        if(!levelenough.isVisible() && !diffenough.isVisible()) {
            expmenu.setText(STRINGEXP + exp);
            if (levelenough.isVisible() && exp >= needexp && level != 0 && arrayLevels[level] >= 1) {    // Изменения при достаточном опыте для уровня
                levelenough.setVisible(false);
            }
            if (diffenough.isVisible() && exp >= needexp && level != 0 && arrayLevels[level] > 1) {  // Измения при достаточном опыте для уровня сложности
                diffenough.setVisible(false);
            }

            mainMenu.setVisible(false);
            mainMenu.setDisable(true);
            gameMenu.setDisable(false);
            gameMenu.setVisible(true);

            praxis = numberOfLevels(level);
            newQuestion();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Some troubles");
            alert.setHeaderText(null);
            alert.setContentText("Проверьте уровень и сложность");

            alert.showAndWait();
        }
    }

    public void newQuestion(){
        Generation gen = new Generation(level, difficult);
        question.setText(gen.question);
        answer1 = gen.answer1;
        mainTextField.setText(answer1);
    }

    private int numberOfLevels(int level){
        if(level != 0) {
            switch (level % 3) {
                case (1):
                    praxis = 20;
                    break;
                case (2):
                    praxis = 30;
                    break;
                case (0):
                    praxis = 40;
                    break;
                default:
                    praxis = 10;
                    break;
            }
        }
        else{
            praxis = -10;
        }
        return praxis;
    }

    @FXML
    private void clickTextField(ActionEvent event){answer = mainTextField.getText();}

    @FXML
    private void clickAnswer(ActionEvent event){
        answer = mainTextField.getText();

        if(Objects.equals(answer, answer1)) {
            exp = exp + 10;
        }
        else{
            if(exp > 10){
                exp = exp - 10;
                if(difficult == 2){
                    exp = exp - 10;
                }
            }
            else{
                exp = 0;
            }
        }
        expmenu.setText(STRINGEXP + exp);
    }
}