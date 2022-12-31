package cn.edu.whut.sept.zuul;

public class Command
{
    private String commandWord;
    private String secondWord;

    /**
     * 创建命令类并初始化内部数据
     * @param firstWord 玩家输入的第一个单词
     * @param secondWord 玩家输入的第二个单词
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * 返回命令第一个单词，即具体指令
     * @return 具体指令字符串
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * 返回第二个单词，即附加指令
     * @return 附加指令字符串
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * 判断指令是否有效
     * @return 如果指令字符串为空，则返回true，否则返回false
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * 判断是否存在附加指令
     * @return 如果附加指令不为空，则返回true，否则返回false
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}
