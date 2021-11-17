package com.epicteam.projecttrenager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
    }

    @Test
    void start() {
        Main main = new Main();
        int expectedExp = 15;
        assertEquals(expectedExp,main.exp);
    }
}