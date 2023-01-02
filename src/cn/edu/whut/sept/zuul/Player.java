package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author:wangyuze
 * @create: 2023-01-03 02:05
 * @Description: 玩家类
 */
public class Player {
    private String name;
    private HashMap<String,Item> items;
    private int strength=1000;
    private Room currentRoom;
    public Player(String name){
        this.name=name;
        items=new HashMap<>();
    }
    public Player(String name,int strength){
        this(name);
        this.strength=strength;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getAllItems(){

    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
