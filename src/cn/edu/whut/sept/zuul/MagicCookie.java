package cn.edu.whut.sept.zuul;

/**
 * @author:wangyuze
 * @create: 2023-01-03 18:32
 * @Description: 魔法饼干，提升背包容量
 */
public class MagicCookie extends Item{
    int energy=100;

    public MagicCookie(String description, int weight) {
        super(description, weight);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
