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
    private int capacity=1000;
    private Room currentRoom;
    public Player(String name){
        this.name=name;
        items=new HashMap<>();
    }
    public Player(String name,int capacity){
        this(name);
        this.capacity=capacity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getAllItems(){
        return new ArrayList<>(items.values());
    }

    public void addItem(Item item){
        this.capacity-=item.getWeight();
        items.put(item.getDescription(),item);
    }

    public void removeItem(String description){
        this.capacity+=items.get(description).getWeight();
        items.remove(description);
    }

    public Item getItem(String item){
        return items.get(item);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
