package cn.edu.whut.sept.zuul;

import java.util.*;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String,Item> items;

    /**
     * 创建房间并初始化其位置描述和出口
     * @param description 房间位置的描述
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        items = new HashMap<>();
    }

    /**
     * 设置出口的方向和对应的相邻房间
     * @param direction 出口方向
     * @param neighbor 出口方向所对应的相邻房间
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * 得到房间的位置描述
     * @return 房间的简短描述字符串
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * 得到房间的位置描述及其开放的出口方向
     * @return 房间的详细描述字符串
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    public void addItem(String description,int weight){
        items.put(description,new Item(description,weight));
    }

    public void removeItem(String description){
        items.remove(description);
    }

    public List<Item> getAllItems(){
        return new ArrayList<>(items.values());
    }
    /**
     * 得到所有开放的出口方向
     * @return 所有出口方向拼接的字符串
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * 得到对应出口方向的房间
     * @param direction 出口方向
     * @return 出口方向所连接的房间类
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(description, room.description) && Objects.equals(exits, room.exits) && Objects.equals(items, room.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, exits, items);
    }
}


