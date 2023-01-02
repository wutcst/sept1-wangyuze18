package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wangyuze
 * @create: 2023-01-03 02:05
 * @Description: 玩家类
 */
public class Player {
    String name;
    List<Item> items;
    int strength=1000;
    Room currentRoom;
    public Player(String name){
        this.name=name;
        items=new ArrayList<>();
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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
