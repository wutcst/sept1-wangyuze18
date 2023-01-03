/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul;

import java.util.LinkedList;
import java.util.List;

public class Game
{

    private Parser parser;
    private LinkedList<Room> records;
    private Player player;

    /**
     * 创建游戏并初始化内部数据和解析器.
     */
    public Game()
    {
        records = new LinkedList<>();
        parser = new Parser();
        player=new Player("WangYuze",500);
        player.setCurrentRoom(RoomMaker.createRoom());
        push();
    }

    /**
     * 创建所有房间对象并连接其出口用以构建迷宫.
     */

//    private void createRooms()
//    {
//        Room outside, theater, pub, lab, office;
//
//        // create the rooms
//        outside = new Room("outside the main entrance of the university");
//        theater = new Room("in a lecture theater");
//        pub = new Room("in the campus pub");
//        lab = new Room("in a computing lab");
//        office = new Room("in the computing admin office");
//
//        // initialise room exits
//        outside.setExit("east", theater);
//        outside.setExit("south", lab);
//        outside.setExit("west", pub);
//
//        theater.setExit("west", outside);
//
//        pub.setExit("east", outside);
//
//        lab.setExit("north", outside);
//        lab.setExit("east", office);
//
//        office.setExit("west", lab);
//
//        //initialise room items
//        theater.addItem("popcorn",300);
//        theater.addItem("coke",200);
//
//        currentRoom = outside;  // start game outside
//        push();
//    }

    /**
     *  游戏主控循环，直到用户输入退出命令后结束整个程序.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * 向用户输出欢迎信息.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * 执行用户输入的游戏指令.
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     * @return 如果执行的是游戏结束指令，则返回true，否则返回false.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch (commandWord){
            case "help":{
                printHelp();
                break;
            }
            case "go":{
                goRoom(command);
                break;
            }
            case "quit":{
                wantToQuit =quit(command);
                break;
            }
            case "look":{
                printRoomDetails();
                break;
            }
            case "back":{
                goBack(command);
                break;
            }
            case "take":{
                take(command);
                break;
            }
            case "drop":{
                drop(command);
                break;
            }
            case "items":{
                printAllDetails();
                break;
            }
            case "eat":{
                eat(command);
                break;
            }
            default:{
                break;
            }
        }
//        if (commandWord.equals("help")) {
//            printHelp();
//        }
//        else if (commandWord.equals("go")) {
//            goRoom(command);
//        }
//        else if (commandWord.equals("quit")) {
//            wantToQuit = quit(command);
//        }

        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * 执行help指令，在终端打印游戏帮助信息.
     * 此处会输出游戏中用户可以输入的命令列表
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * 执行go指令，向房间的指定方向出口移动，如果该出口连接了另一个房间，则会进入该房间，
     * 否则打印输出错误提示信息.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room currentRoom= player.getCurrentRoom();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            if(nextRoom instanceof TransferRoom) {
                nextRoom=transfer(nextRoom);
            }
            currentRoom = nextRoom;
            player.setCurrentRoom(currentRoom);
            push();
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * 执行Quit指令，用户退出游戏。如果用户在命令中输入了其他参数，则进一步询问用户是否真的退出.
     * @return 如果游戏需要退出则返回true，否则返回false.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    private void printAllDetails(){
        printRoomDetails();
        System.out.println();
        printPlayerDetails();
    }

    /**
     * 展示当前房间信息和房间内所有物品信息
     */
    private void printRoomDetails(){
        Room currentRoom= player.getCurrentRoom();
        System.out.println("you are "+currentRoom.getShortDescription());
        List<Item> items=currentRoom.getAllItems();
        String details="";
        int totalWeight=0;
        if(items.isEmpty()){
            details="The room is empty\n";
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
                details+=item.getDescription()+",which weights "+item.getWeight()+"\n";
                totalWeight+= item.getWeight();
            }
        }
        details+="the total weight is "+totalWeight;
        System.out.println(details);
    }

    /**
     * 展示玩家背包所有物品以及重量
     */
    private void printPlayerDetails(){
        List<Item> items=player.getAllItems();
        String details="";
        if(items.isEmpty()){
            details="The backpack is empty\n";
        }
        else{
            int cnt= items.size();
            if(cnt==1){
                details="there is 1 item in the backpack\n";
            }
            else{
                details="there are "+cnt+" items in the backpack\n";
            }
            for(Item item:items){
                details+=item.getDescription()+",which weights "+item.getWeight()+"\n";
            }
        }
        details+="the capacity of your backpack remains "+player.getCapacity();
        System.out.println(details);
    }
    /**
     * 添加房间到历史记录
     */
    private void push(){
        records.addLast(player.getCurrentRoom());
    }

    /**
     * 执行back指令,可以指定返回的层数,直至返回到起点,默认返回层数为一层，即上一层。
     * @param command
     */
    private void goBack(Command command){
        int num=1;
        if(command.hasSecondWord()){
            num=Integer.valueOf(command.getSecondWord());
        }
        while(num>0 && records.size()>1){
            records.pollLast();
            num--;
        }
        player.setCurrentRoom(records.getLast());
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * 进入传送房将随机传送到另一个房间
     * @return
     */
    private Room transfer(Room room){
        System.out.println("you are "+room.getShortDescription());
        System.out.println("transfer......");
        room=((TransferRoom)room).transferToRoom();
        return room;
    }

    /**
     * 执行take指令，输入物品名拾取物品，如果拾取物品超过上限值，则无法拾取
     */
    private void take(Command command){
        if(!command.hasSecondWord()){
            System.out.println("take what?");
            return;
        }
        Room currentRoom= player.getCurrentRoom();
        Item item=currentRoom.getItem(command.getSecondWord());
        if(item==null){
            System.out.println("There is no "+command.getCommandWord()+" in the room !");
        }
        else{
            if(item.getWeight()>player.getCapacity()){
                System.out.println("Your backpack is full !");
            }
            else{
                System.out.println("You take the "+item.getDescription()+" successfully");
                player.addItem(item);
                currentRoom.removeItem(item.getDescription());
            }
        }
    }

    /**
     * 执行drop指令，玩家将丢下对应物品在房间中
     */
    private void drop(Command command){
        if(!command.hasSecondWord()){
            System.out.println("drop what?");
            return;
        }
        Room currentRoom= player.getCurrentRoom();
        Item item= player.getItem(command.getSecondWord());
        if(item==null){
            System.out.println("there is no "+command.getSecondWord()+" in your backpack !");
        }
        else{
            System.out.println("You drop the "+item.getDescription()+" successfully");
            player.removeItem(item.getDescription());
            currentRoom.addItem(item);
        }
    }

    /**
     * 执行eat cookie指令，如果背包中有magic cookie，则执行成功，否则执行失败
     */
    private void eat(Command command){
        if(!command.hasSecondWord()){
            System.out.println("eat what?");
            return;
        }
        if(!command.getSecondWord().equals("cookie")){
            System.out.println("I don't know what you mean...");
            return;
        }
        Item item=player.getItem("magicCookie");
        if(item==null){
            System.out.println("there is no magicCookie in the backpack !");
        }
        else{
            int inc=((MagicCookie)item).getEnergy();
            player.removeItem(item.getDescription());
            player.setCapacity(player.getCapacity()+inc);
            System.out.println("the capacity of your backpack increases "+inc);
            System.out.println("the capacity of your backpack is "+player.getCapacity()+" now");
        }
    }

    public Player getPlayer() {
        return player;
    }
}