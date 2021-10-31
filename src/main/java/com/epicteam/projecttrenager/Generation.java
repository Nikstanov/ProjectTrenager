package com.epicteam.projecttrenager;

import java.security.SecureRandom;

public class Generation {
    int level;
    int difficult;
    int layoutQuestion2 = 140;
    int layoutQuestion1 = 115;
    int layoutQuestion3 = 150;
    int question2Font = 12;
    int layoutQuestion2Y = 266;
    String question1;
    String question2;
    String question3;
    String answer1;
    String answer2 = null;
    String answer3 = null;

    private static final SecureRandom ran = new SecureRandom();

    Generation(int level, int difficult){
        this.difficult = difficult;
        this.level = level;
        generator(level,difficult);
    }

    private void generator(int level, int difficult){
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
            case 1:
                easyEquationsEasy();
                break;
            case 2:
                fraction();
                break;
            case 8:
                quadEquationsEasy();
                break;
            default:
                question1 = "В разработке";
                answer1 = "0";
                break;
        }
    }

    private void quadEquationsEasy(){
        int a = ran.nextInt(20) - 10;
        while(a == 0) {
            a = ran.nextInt(20) - 10;
        }
        int x1 = ran.nextInt(20) - 10;
        int x2 = ran.nextInt(20) - 10;

        if(Math.abs(a) == 1){
            question1 = "   x";
        }
        else{
            question1 = a + "x";
        }
        if(-(x1+x2)*a < 0){
            question1 = question1 + " - " + (x1+x2)*a + "x";
        }
        else{
            if(-(x1+x2)*a != 0) {
                question1 = question1 + " + " + -(x1 + x2) * a + "x";
            }
        }
        if(a*x1*x2 < 0){
            question1 = question1 + " - " + -a*x1*x2 + " = 0";
        }
        else{
            if(a*x1*x2 != 0 ) {
                question1 = question1 + " + " + a * x1 * x2 + " = 0";
            }
        }

        if(a < 0){
            layoutQuestion2 = 147;
            if(a == -10){
                layoutQuestion2 = layoutQuestion2 + 11;
            }
        }
        question2 = "2";
        answer1 = x1 + "," + x2;
        answer2 = x2 + "," + x1;
    }

    private void easyEquationsEasy(){
        int a = ran.nextInt(20) - 10;
        while(a == 0) {
            a = ran.nextInt(20) - 10;
        }
        int b = ran.nextInt(20) - 10;
        while(b == 0) {
            b = ran.nextInt(20) - 10;
        }
        int x = ran.nextInt(20) - 10;

        question1 = a +"x";
        if(b < 0){
            question1 = question1 + " - " + -b;
        }
        else{
            question1 = question1 + " + " + b;
        }
        question1 = question1 + " = " + (a*x + b);
        answer1 = String.format("%s",x);
        layoutQuestion1 = 145;
    }

    private void fraction(){
        int b = ran.nextInt(10);
        while (b <= 1){
            b = ran.nextInt(10);
        }
        int c = ran.nextInt(4);
        while (c == 0){
            c = ran.nextInt(4);
        }
        int d = ran.nextInt(b*c);
        while (d == 0){
            d = ran.nextInt(10);
        }
        int c1 = ran.nextInt(4);
        while (c1 == 0 || c1 == c){
            c1 = ran.nextInt(4);
        }
        int a = ran.nextInt(c*b);
        while (a == 0 || a > b){
            a = ran.nextInt(10);
        }
        layoutQuestion1 = 150;
        layoutQuestion2 = 155;
        layoutQuestion3 = 150;
        question2Font = 20;
        layoutQuestion2Y = layoutQuestion2Y - 7;
        question1 = " " + b*c + "       " + b*c1;
        question2 = "—  +  —  = ?";
        question3 = " " + a + "       " + d;
        int x1 = a*c1 + d*c;
        int x2 =b*c*c1;
        int x = x1 / x2;
        answer1 = "";
        if (x != 0) {
            answer1 = x + " ";
            x1 = x1 - x2;
        }
        for(int i = 2; i<x2; i++){
            if(x1 % i == 0 && x2 % i == 0){
                x1 = x1 / i;
                x2 = x2 / i;
            }
        }
        answer1 = answer1  + x1 +"/" + x2;
    }
}