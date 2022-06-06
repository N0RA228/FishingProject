package Player;

import FishingRod.FishingRod;

public class Player {

    private String name;
    private int money;

    private FishingRod fishingRod;

    public Player(String name) {
        this.name = name;
        money = 0;
    }

    @Override
    public String toString() {
        return name + ": " + money;
    }

    public String getName () {
        return name;
    }

    public void setFishingRod(FishingRod fishingRod) {
        this.fishingRod = fishingRod;
    }

    public FishingRod getFishingRod () {
        return fishingRod;
    }

    public boolean addMoney (int add) {

        if(add < 0)
            return false;

        money += add;

        return true;
    }

    public boolean deleteMoney (int delete) {

        if(delete < 0)
            return false;

        if (money - delete < 0)
            return false;

        money -= delete;

        return true;
    }

    public int getMoney () {

        return money;
    }

}
