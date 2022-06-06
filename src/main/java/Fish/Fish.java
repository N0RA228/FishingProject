package Fish;

public class Fish {

    private String name;
    private int price;
    private int deleteFishingRod;

    public Fish(String name, int price, int deleteFishingRod) {
        this.name = name;
        this.price = price;
        this.deleteFishingRod = deleteFishingRod;
    }

    public String getName () {
        return name;
    }

    public int getPrice () {
        return price;
    }

    public int getDeleteFishingRod () {
        return deleteFishingRod;
    }
}
