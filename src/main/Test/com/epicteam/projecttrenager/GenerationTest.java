package com.epicteam.projecttrenager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerationTest {

    int level = 1;
    int difficult = 1;
    Generation generation = new Generation(level, difficult);

    @Test
    void generator() {
        int expLayoutQuestion3 = 150;
        int expQuestion2Font = 12;
        int expLayoutQuestion2Y = 266;

        assert(generation.layoutQuestion2 < 200 && 100 < generation.layoutQuestion2);
        assert(generation.layoutQuestion1 < 200 && 100 < generation.layoutQuestion1);
        assertEquals(expLayoutQuestion3, generation.layoutQuestion3);
        assertEquals(expQuestion2Font, generation.question2Font);
        assertEquals(expLayoutQuestion2Y, generation.layoutQuestion2Y);
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