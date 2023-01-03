package test;

import cn.edu.whut.sept.zuul.*;
import javassist.compiler.MemberResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;


public class GameTest {
    private Game game;
    private Player player;

    @Before
    public void setUp() throws Exception {
        game=new Game();
        player=game.getPlayer();
    }

    @Test
    public void backTest() throws InvocationTargetException, IllegalAccessException {
        Command command=null;
        Method goRoom=PowerMockito.method(Game.class,"goRoom",Command.class);
        Method goBack=PowerMockito.method(Game.class,"goBack",Command.class);
        Room start=player.getCurrentRoom();
        //back
        command=new Command("go","east");
        goRoom.invoke(game,command);
        command=new Command("go","west");
        goRoom.invoke(game,command);
        command=new Command("back",null);
        goBack.invoke(game,command);
        Assert.assertEquals(player.getCurrentRoom().getShortDescription(),"in a lecture theater");

        //back 2
        game=new Game();
        player=game.getPlayer();
        command=new Command("go","south");
        goRoom.invoke(game,command);
        command=new Command("go","east");
        goRoom.invoke(game,command);
        command=new Command("back","2");
        goBack.invoke(game,command);
        Assert.assertEquals(player.getCurrentRoom().getShortDescription(),"outside the main entrance of the university");

        //back 10000
        game=new Game();
        player=game.getPlayer();
        command=new Command("go","south");
        goRoom.invoke(game,command);
        command=new Command("back","10000");
        goBack.invoke(game,command);
        Assert.assertEquals(player.getCurrentRoom().getShortDescription(),"outside the main entrance of the university");
    }


    @Test
    public void takeTest() throws InvocationTargetException, IllegalAccessException {
        Command command=null;
        Method take=PowerMockito.method(Game.class,"take",Command.class);
        Room currentRoom=player.getCurrentRoom();
        player.setCapacity(500);
        currentRoom.addItem("coke",200);
        currentRoom.addItem("popcorn",300);
        currentRoom.addItem("stone",1000);

        //take xxx
        command=new Command("take","xxx");
        take.invoke(game,command);
        Assert.assertEquals(currentRoom.getAllItems().size(),3);
        Assert.assertEquals(player.getCapacity(),500);

        //take coke 200
        command=new Command("take","coke");
        take.invoke(game,command);
        Assert.assertNull(currentRoom.getItem("coke"));
        Assert.assertNotNull(player.getItem("coke"));
        Assert.assertEquals(player.getCapacity(),300);

        //take popcorn 300
        command=new Command("take","popcorn");
        take.invoke(game,command);
        Assert.assertNull(currentRoom.getItem("popcorn"));
        Assert.assertNotNull(player.getItem("popcorn"));
        Assert.assertEquals(player.getCapacity(),0);

        //take stone 1000
        command=new Command("take","stone");
        take.invoke(game,command);
        Assert.assertNotNull(currentRoom.getItem("stone"));
        Assert.assertNull(player.getItem("stone"));
        Assert.assertEquals(player.getCapacity(),0);

    }

    @Test
    public void dropTest() throws InvocationTargetException, IllegalAccessException {
        Command command=null;
        Method drop=PowerMockito.method(Game.class,"drop",Command.class);
        player.setCapacity(500);
        player.addItem(new Item("coke",200));
        player.addItem(new Item("popcorn",300));
        Room currentRoom=player.getCurrentRoom();

        //drop xxx
        command=new Command("drop","xxx");
        Assert.assertNull(player.getItem("xxx"));
        Assert.assertNull(currentRoom.getItem("xxx"));
        Assert.assertEquals(player.getCapacity(),0);

        //drop coke 200
        command=new Command("drop","coke");
        drop.invoke(game,command);
        Assert.assertEquals(player.getCapacity(),200);
        Assert.assertNull(player.getItem("coke"));
        Assert.assertNotNull(currentRoom.getItem("coke"));

        //drop popcorn 300
        command=new Command("drop","popcorn");
        drop.invoke(game,command);
        Assert.assertEquals(player.getCapacity(),500);
        Assert.assertNull(player.getItem("popcorn"));
        Assert.assertNotNull(currentRoom.getItem("popcorn"));

    }

    @Test
    public void eat_cookieTest() throws InvocationTargetException, IllegalAccessException {
        Command command=null;
        Method eat=PowerMockito.method(Game.class,"eat",Command.class);
        player.setCapacity(500);

        //eat xxx
        command=new Command("eat","xxx");
        eat.invoke(game,command);
        Assert.assertEquals(player.getCapacity(),500);

        //eat cookie
        command=new Command("eat","cookie");
        eat.invoke(game,command);
        Assert.assertEquals(player.getCapacity(),500);

        player.addItem(new MagicCookie("magicCookie",0));
        //eat cookie
        command=new Command("eat","cookie");
        eat.invoke(game,command);
        Assert.assertNull(player.getItem("magicCookie"));
        Assert.assertEquals(player.getCapacity(),600);

    }




}