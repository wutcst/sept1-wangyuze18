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
    public Player(String name){
        this.name=name;
        items=new ArrayList<>();
    }
    public Player(String name,int strength){
        this(name);
        this.strength=strength;
    }


}
