package com.daisy.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * poker
 * Task1: Please represent 54 playing Card Deck as a Java class. Imagine which methods could be placed inside.
 * Task2: Using task1 as your class. Imagine there are 1 card sender and 3 players. The card sender send card to player one by one in a round. Once the player’s sum points bigger than 50, the player win the game.  Multi-thread should be used in this task.
 * [Points define]
 * "A"=1,"2"=2,"3"=3,"4"=4,"5"=5,"6"=6,"7"=7,"8"=8,"9"=9,"10"=10,"J"=11,"Q"=12,"K"=13,"black Joke"=20,"red Joke"=20;
 * Example 1:
 * Round1 = Sender ["A","6","K"=13] -> Player1=1, player2=6, player3=13;
 * Round2 = Sender ["10","Q","black Joke"]-> Player1=11, player2=18, player3=33;
 * Round3 = Sender ["9","J","red Joke"]-> Player1=20, player2=29, player3=53-> player 3 win;
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


    public List<Integer> getAllPokers() {
        return allPokers;
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

    public static void main(String[] args) throws Exception {
        Task1 task1 = new Task1();
        System.out.println(task1.getAllPokers().toString());
        for (; ; ) {
            Thread.sleep(1);
            System.out.println(task1.getOnePoker());
        }

    }


}
