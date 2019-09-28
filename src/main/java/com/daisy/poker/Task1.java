package com.daisy.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * poker
 * Task1: Please represent 54 playing Card Deck as a Java class. Imagine which methods could be placed inside.
 */
public class Task1 {
    private static final int[] POINTS_ARRAY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            20, 20};
    private static final String[] NAME_ARRAY = {"♠A", "♠2", "♠3", "♠4", "♠5", "♠6", "♠7", "♠8", "♠9", "♠10", "♠J", "♠Q", "♠K",
            "♥A", "♥2", "♥3", "♥4", "♥5", "♥6", "♥7", "♥8", "♥9", "♥10", "♥J", "♥Q", "♥K",
            "♦A", "♦2", "♦3", "♦4", "♦5", "♦6", "♦7", "♦8", "♦9", "♦10", "♦J", "♦Q", "♦K",
            "♣A", "♣2", "♣3", "♣4", "♣5", "♣6", "♣7", "♣8", "♣9", "♣10", "♣J", "♣Q", "♣K",
            "black Joke", "red Joke"};


    private static final int TOTAL_POKER_NUM = 54;
    private List<Integer> allPokers = new ArrayList<>();
    private int index = 0;

    Task1() {
        init();
    }

    public static String getPokerName(int poker) {
        if (poker >= TOTAL_POKER_NUM || poker < 0) {
            throw new RuntimeException("error poker");
        }
        return NAME_ARRAY[poker];
    }

    public static int getPokerPoint(int poker) {
        if (poker >= TOTAL_POKER_NUM || poker < 0) {
            throw new RuntimeException("error poker");
        }
        return POINTS_ARRAY[poker];
    }

    public synchronized int getOnePoker() {//同步发牌
        if (index >= TOTAL_POKER_NUM) {
            throw new RuntimeException("no pokers");
        }
        return allPokers.get(index++);
    }

    private synchronized void init() {
        for (int i = 0; i < TOTAL_POKER_NUM; i++) {
            allPokers.add(i);
        }
        Random rand = new Random();
        Collections.shuffle(allPokers, rand);

    }
}
