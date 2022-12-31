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

