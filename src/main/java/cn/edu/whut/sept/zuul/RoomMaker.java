package cn.edu.whut.sept.zuul;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author:wangyuze
 * @create: 2023-01-02 22:38
 * @Description: 创建房间
 */
public class RoomMaker {
    private static final HashMap<String,Room> rooms=new HashMap<>();
    private static final ArrayList<Room> roomList=new ArrayList<>();

    /**
     * 创建初始化房间
     * @return 起始房间
     */
    public static Room createRoom(){
        Room outside, theater, pub, lab, office,transferRoom1;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        transferRoom1=new TransferRoom("in the transfer room 1");

        //initialise rooms
        rooms.put("outside",outside);
        rooms.put("lecture theater",theater);
        rooms.put("campus pub",pub);
        rooms.put("computing lab",lab);
        rooms.put("computing admin office",office);
        rooms.put("transfer room 1",transferRoom1);

        //initialise roomList
        roomList.addAll(rooms.values());

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);
        pub.addItem(new MagicCookie("magicCookie",0));

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("south",transferRoom1);
        //initialise room items
        theater.addItem("popcorn",300);
        theater.addItem("coke",200);



        return outside;  // start game outside
    }

    /**
     * 根据房间名来获取房间实例
     * @param roomName 房间名
     * @return 目标房间
     */
    public static Room getRoom(String roomName){
        return rooms.get(roomName);
    }

    /**
     * 随机取得一个房间
     * @return 传送房间
     */
    public static Room getRandomRoom(){
        int chooseNum=(int)(Math.random()*rooms.size());
        if(chooseNum==rooms.size()) --chooseNum;
        return roomList.get(chooseNum);
    }

}
