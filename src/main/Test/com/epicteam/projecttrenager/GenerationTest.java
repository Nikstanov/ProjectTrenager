package com.epicteam.projecttrenager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerationTest {

    int level = 1;
    int difficult = 1;
    Generation generation = new Generation(level, difficult);

    @Test
    void generator() {

        assert(generation.layoutQuestion2 < 200 && 100 < generation.layoutQuestion2);
        assert(generation.layoutQuestion1 < 200 && 100 < generation.layoutQuestion1);
        assert(generation.layoutQuestion3 < 300 && 100 < generation.layoutQuestion3);
        assert(generation.question2Font < 30 && 10 < generation.question2Font);
        assert(generation.layoutQuestion2Y < 300 && 100 < generation.layoutQuestion2Y);
    }

    @Test
    void qubeEquations() {
    }

    @Test
    void quadEquationsEasy() {
    }

    @Test
    void hardEquationsEasy() {
    }

    @Test
    void easyEquationsEasy() {
    }

    @Test
    void hardEquationsEasy1() {
    }

    @Test
    void znak() {
    }

    @Test
    void fraction() {
    }
}