package com.epicteam.projecttrenager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class Main extends Application {
    static int exp = 10;
    static int[] arraylevels = {0,0,0,0,0,0,0,0,0,0};

    public static void main(String[] args) throws Exception{
        FileReader fr = new FileReader("preservation.txt");
        try (Scanner scan = new Scanner(fr)) {
            exp = Integer.parseInt(scan.nextLine());
            for (int i = 0; i < 10; i++) {
                arraylevels[i] = Integer.parseInt(scan.nextLine());
            }
            fr.close();
        }
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle("Математический тренажер");
        stage.setWidth(400);
        stage.setHeight(500);
        stage.setResizable(false);
        stage.show();
        super.stop();

        stage.setOnCloseRequest(windowEvent -> {
            try {
                FileWriter fw = new FileWriter("preservation.txt");
                fw.write(exp+"\n");
                for(int i = 0;i < 10;i++){
                    fw.write(arraylevels[i] + "\n");
                }
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}