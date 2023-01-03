package cn.edu.whut.sept.zuul;

/**
 * @author:wangyuze
 * @create: 2023-01-03 01:14
 * @Description: 传送房间
 */
public class TransferRoom extends Room{

    /**
     * 创建房间并初始化其位置描述和出口
     *
     * @param description 房间位置的描述
     */
    public TransferRoom(String description) {
        super(description);
    }

    /**
     * 随机传送到另一个房间
     * @return 传送房间
     */
    public Room transferToRoom(){
        Room room=null;
        while(room==null || room.equals(this)){
            room=RoomMaker.getRandomRoom();
        }
        return room;
    }

}
