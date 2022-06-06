package ChainOfResponsibility;

import Fish.Fish;
import com.example.fishingproject.HelloController;

import java.util.Random;

public class FishHandler extends BaseHandler {

    private float percent = 0;
    private Fish fish;

    public FishHandler(BaseHandler baseHandler, Fish fish, float percent) {
        super(baseHandler);

        this.percent = percent;
        this.fish = fish;
    }

    @Override
    public Fish check() {

        Random random = new Random();
        float rand = (float) Math.random();

        if(percent > rand)
            return fish;
        else {
            percent += 1;
        }

        return checkNext();
    }
}
