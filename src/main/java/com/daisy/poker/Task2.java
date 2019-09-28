package com.daisy.poker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Task2 {
    String[] strArr = {"张三", "李四", "王二麻"};


}


class Player extends Thread {
    private Task1 task1;//扑克
    private Player next;//下一家
    public List<Integer> pokersInHand = new ArrayList<>(); //发到手的牌

    public Player(String name, Task1 task1) {
        this.setName(name);
        this.task1 = task1;
    }

    public void setNext(Player next) {
        this.next = next;
    }

    public void setPokersInHand(int cards) {
        pokersInHand.add(cards);
    }

    public boolean biggerThan50() {
        int allPoints = 0;
        for (Integer poker : pokersInHand) {
            allPoints = allPoints + Task1.getPokerPoint(poker);
        }
        return allPoints > 50;
    }

    public void cllectAll() {
        int poker = task1.getOnePoker();
        pokersInHand.add(poker);
        System.out.println(getName() + "get" + Task1.getPokerName(poker));
        if (biggerThan50()) {
            System.out.println(getName() + "win");
            System.exit(0);
        }




    public void run() {
        int sendNum = task1.getNext();
        while (sendNum < task1.getPokersNum()) {
            sendNum = getPoker(sendNum);
        }
        task1.showAllCards();
        System.exit(0);
    }


    /**
     * 摸牌
     *
     * @param sendNum 发了多少张牌
     * @return
     */
    private synchronized int getPoker(int sendNum) {
        System.out.println(getName() + "摸到牌" + sendNum);
        setPokersInHand(task1.sendPokers(sendNum));
        this.showCards();
        this.cllectAll();
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
        sendNum = task1.getNext();
        if (sendNum % task1.PLAY_NUM == 0) {
            System.out.println("--------------------完成" + sendNum / task1.PLAY_NUM + "轮----------------");
        }
        return sendNum;

    }
}