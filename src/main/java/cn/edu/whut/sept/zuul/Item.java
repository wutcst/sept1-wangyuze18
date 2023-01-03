package cn.edu.whut.sept.zuul;

/**
 * @author:wangyuze
 * @create: 2023-01-01 18:58
 * @Description:
 */
public class Item {
    private int weight;
    private String description;

    public Item(String description,int weight){
        this.description=description;
        this.weight=weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }
}
