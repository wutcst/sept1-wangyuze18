package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private List<Item> items;

    /**
     * 创建房间并初始化其位置描述和出口
     * @param description 房间位置的描述
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
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
        items.add(new Item(description,weight));
    }

    public String getAllItemDescription(){
        String details=null;
        if(items.isEmpty()){
            details="The room is empty";
        }
        else{
            int cnt= items.size();
            if(cnt==1){
                details="there is 1 item in the room\n";
            }
            else{
                details="there are "+cnt+" items in the room\n";
            }
            for(Item item:items){
                details+="you find one "+item.getDescription()+",which weights "+item.getWeight()+" g\n";
            }
        }
        return details;
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
}


