package com.epicteam.projecttrenager;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final String STRINGEXP = "Ваш опыт: ";
    int exp;
    int[] arrayLevels;
    int level = 1;
    int difficult = 1;
    int praxis;
    int praxisLeft;
    int lives;
    int needexp = 0;
    String answer;
    String answer1;
    String answer2;
    String answer3;
    boolean answerIsRight;
    boolean returnBack;

    Thread timer = new Thread(new Timer());
    int width = 200;

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
        returnBack = false;
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
    private Label question1;

    @FXML
    private TextField mainTextField;

    @FXML
    private Label numberOfPraxis;

    @FXML
    private Label livesLabel;

    @FXML
    private Button buttonReturn;

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
            praxisLeft = praxis;

            lives = 3;
            livesLabel.setVisible(true);

            buttonReturn.setVisible(false);
            if(level == 0){
                livesLabel.setVisible(false);
                buttonReturn.setVisible(true);
            }

            newQuestion();

            width = 200;
            if(!timer.isAlive()){
                Thread timer1 = new Thread(new Timer());
                timer1.start();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Some troubles");
            alert.setHeaderText(null);
            alert.setContentText("Проверьте уровень и сложность");

            alert.showAndWait();
        }
    }

    @FXML
    private Button answerButton;

    @FXML
    private Label question2;

    @FXML
    private Label question3;

    private synchronized void newQuestion(){
        livesLabel.setText("Жизней - " + lives);
        Generation gen = new Generation(level, difficult);
        question1.setText(gen.question1);
        question1.setLayoutX(gen.layoutQuestion1);
        question2.setText(gen.question2);
        question2.setLayoutX(gen.layoutQuestion2);
        question2.setFont(new Font(gen.question2Font));
        question2.setLayoutY(gen.layoutQuestion2Y);
        question3.setLayoutX(gen.layoutQuestion3);
        question3.setText(gen.question3);
        answer1 = gen.answer1;
        answer2 = gen.answer2;
        answer3 = gen.answer3;
        mainTextField.setText(answer1);
        numberOfPraxis.setText(String.format("осталось %s примеров из %s", praxisLeft,praxis));
        if(praxisLeft == 0) {
            if(level != 0) {
                exp = exp + 200;
            }
            if (arrayLevels[level] < 2){
                arrayLevels[level]++;
            }
        }
        if(lives == 0){
            mainMenu.setVisible(true);
            mainMenu.setDisable(false);
            gameMenu.setDisable(true);
            gameMenu.setVisible(false);
            if(level != 0){
                exp = exp - 400;
            }
        }
        expmenu.setText(STRINGEXP + exp);
        praxisLeft--;
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

        answerIsRight = false;
        checkAnswer();
        if(answerIsRight) {
            exp = exp + 5;
            if(difficult == 2){
                exp = exp + 5;
            }
        }
        else{
            lives--;
            if(exp > 10){
                exp = exp - 5;
                if(difficult == 2){
                    exp = exp - 5;
                }
            }
            else{
                exp = 0;
            }
        }
        expmenu.setText(STRINGEXP + exp);
        newQuestion();
        width = 200;
    }

    private void checkAnswer(){
        boolean flag = false;
        if(answer1 != null){
            if(answer1.equals(answer)){
                flag = true;
            }
        }
        if(answer2 != null){
            if(answer2.equals(answer)){
                flag = true;
            }
        }
        if(answer3 != null){
            if(answer3.equals(answer)){
                flag = true;
            }
        }
        answerIsRight = flag;
    }

    @FXML
    private void clickReturn(ActionEvent event){
        if(!returnBack){
            buttonReturn.setText("Уже устал\nНажми еще раз");
        }
        else{
            mainMenu.setVisible(true);
            mainMenu.setDisable(false);
            gameMenu.setDisable(true);
            gameMenu.setVisible(false);
        }
    }

    @FXML
    private Rectangle timerRectangle;

    class Timer implements Runnable{
        @Override
        public void run() {
            timerRectangle.setFill(Color.DODGERBLUE);
            while (width >= 1 && gameMenu.isVisible()) {
                timerRectangle.setWidth(width);
                width = width - 1;
                switch (width){
                    case 150:
                        timerRectangle.setFill(Color.rgb(99,22,225));
                        break;
                    case 100:
                        timerRectangle.setFill(Color.rgb(148,60,201));
                        break;
                    case 50:
                        timerRectangle.setFill(Color.rgb(155,33,57));
                        break;
                    default:
                        break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            if(gameMenu.isVisible()){
                lives = lives - 1;
                exp = exp - 5;
                Platform.runLater(MainController.this::newQuestion);
                timerRectangle.setWidth(200);
                width = 200;
                Thread timer1 = new Thread(new Timer());
                timer1.start();
            }
        }
    }
}
