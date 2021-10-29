package com.epicteam.projecttrenager;

import java.security.SecureRandom;

public class generation {
    int level;
    int difficult;
    String question;
    String answer1;

    private static final SecureRandom ran = new SecureRandom();

    generation(int level, int difficult){
        this.difficult = difficult;
        this.level = level;
        generator(level,difficult);
    }

    private void generator(int difficult, int level){
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
                quadEquationsEasy();
                break;
            case 1:
                easyEquationsEasy();
                break;
            default:
                question = "В разработке";
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

        question = String.format("%s x^2 + (%s)x + %s",a,-(x1 + x2)*a, a*x1*x2);
        answer1 = x1 + "," + x2;
    }

    public void easyEquationsEasy(){
        int a =ran.nextInt(20) - 10;
        while(a == 0) {
            a = ran.nextInt(20) - 10;
        }
        int b = ran.nextInt(20) - 10;
        while(b == 0) {
            b = ran.nextInt(20) - 10;
        }
        int x = ran.nextInt(20) - 10;

        question = String.format("%s x + (%s) = %s",a,b, a*x + b);
        answer1 = String.format("%s",x);
    }
}
