package cn.edu.whut.sept.zuul;

public class CommandWords
{
    private static final String[] validCommands = {
            "go", "quit", "help","look"
    };

    /**
     * 初始化有效命令类
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * 验证玩家输入单词是否为有效指令
     * @param aString 玩家输入的单词
     * @return 如果输入的单词为有效指令，返回true，否则返回false
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        return false;
    }

    /**
     * 展示输出所有有效指令
     */
    public void showAll()
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
