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
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    int level = 1;
    int difficult = 1;
    int praxis;
    int needexp = 0;
    String answer;
    double[] arrayAnswers;

    private static final Random ran = new Random();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Синий фон", "Красный фон", "Желтый фон");
        anotherfon.setItems(list);
        mainMenu.setDisable(false);
        gameMenu.setDisable(true);
        mainMenu.setVisible(true);
        gameMenu.setVisible(false);
        expmenu.setText("ваш опыт: " + Main.exp);
    }

    @FXML
    private ComboBox<String> anotherfon;

    @FXML
    private AnchorPane backfon;

    @FXML
    private AnchorPane forwardfon;

    @FXML
    public void newfon(ActionEvent event){
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

                if (Main.arraylevels[level] != 2  && level != 0) {
                    diffenough.setLayoutX(202);
                    diffenough.setText("Пройди хотя бы 2 раза на легком");
                    diffenough.setVisible(true);
                }
                else{
                    if(needexp > Main.exp && level != 0){
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
            if (Main.arraylevels[level] < 1) {
                levelenough.setText("Предыдущий уровень");
                levelenough.setVisible(true);
                levelenough.setLayoutX(50);
            } else {
                if (Main.exp < needexp) {
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
            expmenu.setText("Ваш опыт: " + Main.exp);
            if (levelenough.isVisible() && Main.exp >= needexp && level != 0 && Main.arraylevels[level] >= 1) {    // Изменения при достаточном опыте для уровня
                levelenough.setVisible(false);
            }
            if (diffenough.isVisible() && Main.exp >= needexp && level != 0 && Main.arraylevels[level] > 1) {  // Измения при достаточном опыте для уровня сложности
                diffenough.setVisible(false);
            }

            mainMenu.setVisible(false);
            mainMenu.setDisable(true);
            gameMenu.setDisable(false);
            gameMenu.setVisible(true);

            praxis = numberOfLevels(level);

            arrayAnswers = generation(difficult,level);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Some troubles");
            alert.setHeaderText(null);
            alert.setContentText("Проверьте уровень и сложность");

            alert.showAndWait();
        }
    }

    public  int numberOfLevels(int level){
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

    public double[] generation(int difficult, int level){
        int numberOfExample = 1;
        if(difficult == 1) {
            if (0 < level) {
                numberOfExample = ran.nextInt(3) + 1;
            }
            if (3 < level) {
                numberOfExample = ran.nextInt(3) + 8;
            }
            if (6 < level) {
                numberOfExample = ran.nextInt(3) + 14;
            }
        }
        else {
            if (0 < level) {
                numberOfExample = ran.nextInt(3) + 5;
            }
            if (3 < level) {
                numberOfExample = ran.nextInt(3)+ 11;
            }
            if (6 < level) {
                numberOfExample = ran.nextInt(3) + 17;
            }
        }
        switch (numberOfExample){
            case 8:
                arrayAnswers = quadEquationsEasy();
                break;
            case 1:
                arrayAnswers = easyEquationsEasy();
                break;
            default:
                question.setText("В разработке");
                arrayAnswers = new double[]{0};
                break;
        }
        return arrayAnswers;
    }

    public double[] quadEquationsEasy(){
        int a = ran.nextInt(20) - 10;
        while(a == 0) {
            a = ran.nextInt(20) - 10;
        }
        int x1 = ran.nextInt(20) - 10;
        int x2 = ran.nextInt(20) - 10;

        String example =  String.format("%s x^2 + (%s)x + %s",a,-(x1 + x2)*a, a*x1*x2);
        question.setText(example);

        return new double[]{x1,x2};
    }

    public double[] easyEquationsEasy(){
        int a =ran.nextInt(20) - 10;
        while(a == 0) {
            a = ran.nextInt(20) - 10;
        }
        int b = ran.nextInt(20) - 10;
        while(b == 0) {
            b = ran.nextInt(20) - 10;
        }
        int x = ran.nextInt(20) - 10;

        String example =  String.format("%s x + (%s) = %s",a,b, a*x + b);
        question.setText(example);

        return new double[]{x};
    }

    @FXML
    private void clickTextField(ActionEvent event){answer = mainTextField.getText();}

    @FXML
    private void clickAnswer(ActionEvent event){
        answer = mainTextField.getText();
        String answer1;
        String answer2;

        switch (arrayAnswers.length){
            case 1:
                answer1 = "" + arrayAnswers[0];
                answer2 = "";
                break;
            case 2:
                answer1 = arrayAnswers[1] + "," + arrayAnswers[0];
                answer2 = arrayAnswers[0] + "," + arrayAnswers[1];
                break;
            default:
                answer1 = arrayAnswers[0] + "";
                answer2 = "";
                break;
        }


        if(Objects.equals(answer, answer1) || Objects.equals(answer, answer2)) {
            Main.exp = Main.exp + 10;
        }
        else{
            if(Main.exp > 10){
                Main.exp = Main.exp - 10;
                if(difficult == 2){
                    Main.exp = Main.exp - 10;
                }
            }
            else{
                Main.exp = 0;
            }
        }
        expmenu.setText("Ваш опыт: " + Main.exp);
    }
}