package FishingRod;

public class FishingRod {

    private int maxCondition;
    private int currentCondition;

    public FishingRod(int maxCondition) {
        this.maxCondition = maxCondition;
        this.currentCondition = maxCondition;
    }

    public int getMaxCondition () {
        return maxCondition;
    }

    public int getCurrentCondition () {
        return currentCondition;
    }

    public boolean deleteCondition (int delete) {

        if(delete < 0)
            return false;

        currentCondition -= delete;

        if(currentCondition < 0)
            currentCondition = 0;

        return true;
    }
}
