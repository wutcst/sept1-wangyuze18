##软件工程实践一实验报告
###1.样例工程的代码结构分析
####1.1类的文字描述
world of zuul主要由五个类构成。  
CommandWords类是有效命令类，定义了命令单词字符串数组，对用户输入的每一个指令进行有效性判断，同时还可以输出所有指令。  
Command类为命令类，对应着玩家进行游戏时输入的指令，定义了两个字符串来接收用户输入的一个或两个单词组成的指令。   
Room类是房间类，对应着玩家游戏过程中到达的地点，定义了方向和房间的键值对exit，通过exit可以判断每个方向所连接的房间。  
Parser类是命令分析器，可以将玩家输入的单词解析包装为Command类。  
Game类是游戏中最重要的类，Game类的实例创建游戏并初始化所有房间以及它们不同方向连接的其他房间；初始化解析器接收用户输入的指令并开始游戏。接着进行读取和执行指令来运行游戏。
####1.2uml类图
~~~mermaid
classDiagram
class Command{
    -String commandWord
    -String secondWord
    +Command(String firstWord, String secondWord)
    +getCommandWord() String
    +getSecondWord() String
    +isUnknown() boolean
    +hasSecondWord() boolean
}
class Parser{
      -CommandWords commands
      -Scanner reader
      +Parser()
      +getCommand() Command
      +showCommands()
}
class Room{
    -String description
    -HashMap<String, Room> exits
    +Room(String description)
    +setExit(String direction, Room neighbor)
    +String getShortDescription()
    +String getLongDescription()
    -String getExitString()
    +getExit(String direction) Room
}
class Game{
    -Parser parser 
    -Room currentRoom
    -createRooms()
    +play()
    -printWelcome()
    -processCommand(Command command) boolean
    -printHelp()
    -goRoom(Command command)
    -quit(Command command) boolean
}
class Main{
      +main(String[] args)*
}
class CommandWords{
    -String[] validCommands$
    +CommandWords()
    +isCommand(String aString) boolean
    +showAll()
}
Game..>Command
Game "1" o-- "1" Parser
Game "1" o-- "1" Room
Parser "1" --> "1" CommandWords


~~~

