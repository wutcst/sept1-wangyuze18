package cn.edu.whut.sept.zuul;

import java.util.Scanner;

public class Parser
{
    private CommandWords commands;
    private Scanner reader;

    /**
     * 创建指令解析器并初始化有效指令类和输入流
     */
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * 获取指令，通过命令行读取用户输入的单词，如果只有一个单词，则secondWord赋值为null，停止读入，如果有两个单词，则继续读入下一个。
     * 最后进行指令有效性验证，如果第一个单词为有效指令，则commonWord赋值为此单词，否则commonWord为null，最终包装返回Command类
     * @return 命令类，用于说明下一步的操作
     */
    public Command getCommand()
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();   
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2);
        }
    }

    /**
     * 展示输出所有有效指令
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
