package UI;

import Fish.Fish;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FishCatchPresenter {

    private GridPane gridPane;
    private Label nameLabel;
    private Label priceLabel;
    private Label deleteFishingRodLabel;

    private Fish fish;

    public FishCatchPresenter(GridPane gridPane, Label nameLabel, Label priceLabel, Label deleteFishingRodLabel) {
        this.gridPane = gridPane;
        this.nameLabel = nameLabel;
        this.priceLabel = priceLabel;
        this.deleteFishingRodLabel = deleteFishingRodLabel;
    }

    public void setFish (Fish fish) {

        this.fish = fish;
    }

    public void show () {

        gridPane.visibleProperty().setValue(true);
        nameLabel.setText(fish.getName());
        priceLabel.setText("Цена: " + fish.getPrice());
        deleteFishingRodLabel.setText("Удочка: -" + fish.getDeleteFishingRod() );
    }

    public void hide () {

        gridPane.visibleProperty().setValue(false);
    }
}
