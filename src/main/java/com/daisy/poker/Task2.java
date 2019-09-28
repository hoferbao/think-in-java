package com.daisy.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * poker
 * Task2: Using task1 as your class. Imagine there are 1 card sender and 3 players. The card sender send card to player one by one in a round. Once the player’s sum points bigger than 50, the player win the game.  Multi-thread should be used in this task.
 * [Points define]
 * "A"=1,"2"=2,"3"=3,"4"=4,"5"=5,"6"=6,"7"=7,"8"=8,"9"=9,"10"=10,"J"=11,"Q"=12,"K"=13,"black Joke"=20,"red Joke"=20;
 * Example 1:
 * Round1 = Sender ["A","6","K"=13] -> Player1=1, player2=6, player3=13;
 * Round2 = Sender ["10","Q","black Joke"]-> Player1=11, player2=18, player3=33;
 * Round3 = Sender ["9","J","red Joke"]-> Player1=20, player2=29, player3=53-> player 3 win;
 */
public class Task2 {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Player a = new Player("Player1", task1);
        Player b = new Player("Player2", task1);
        Player c = new Player("Player3", task1);

        a.setNext(b);
        b.setNext(c);
        c.setNext(a);
        a.start();
    }


}


class Player extends Thread {
    private Task1 task1;//扑克
    private Player next;//下一家
    private List<Integer> pokersInHand = new ArrayList<>(); //发到手的牌

    Player(String name, Task1 task1) {
        this.setName(name);
        this.task1 = task1;
    }

    public void setNext(Player next) {
        this.next = next;
    }


    private boolean biggerThan50() {
        return getTotalPoint() > 50;
    }

    private String getAllPoker() {
        StringBuilder allPoker = new StringBuilder();
        for (Integer poker : pokersInHand) {
            allPoker = allPoker.append(Task1.getPokerName(poker)).append(" ");
        }
        return allPoker.toString();
    }

    private int getTotalPoint() {
        int allPoints = 0;
        for (Integer poker : pokersInHand) {
            allPoints = allPoints + Task1.getPokerPoint(poker);
        }
        return allPoints;
    }


    public void run() {
        while (true) {
            getPoker();
        }

    }


    private synchronized void getPoker() {
        int poker = task1.getOnePoker();
        pokersInHand.add(poker);
        System.out.println(getName() + " get " + Task1.getPokerName(poker));
        if (biggerThan50()) {
            System.out.println(getName() + " win! " + " pokersInHand = [" + getAllPoker() + "] points = " + getTotalPoint());
            System.exit(0);
        }
        if (this.next.getState() == State.NEW) {//第一轮 下一个线程如果还没开始则启动它
            this.next.start();
        } else {
            synchronized (this.next) {//唤醒下一个进程
                this.next.notify();
            }
        }
        try { //阻塞当前进程
            this.wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}