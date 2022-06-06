package com.example.fishingproject;

import ChainOfResponsibility.BaseHandler;
import ChainOfResponsibility.FishHandler;
import ChainOfResponsibility.NegativeHandler;
import Fish.Fish;
import FishingRod.FishingRod;
import Player.Player;
import UI.FishCatchPresenter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    /** Панель создания игрока - вся панель */
    @FXML
    private GridPane newGamePanel;

    /** Панель создания игрока - полеввода имени */
    @FXML
    private TextField inputNameTextField;

    /** Область для рыбалки */
    @FXML
    private AnchorPane fishingRegion;

    /** Экран победы */
    @FXML
    private GridPane fishСatchView;

    /** Название пойманной добычи */
    @FXML
    private Label nameFishСatchTextField ;

    /** Цена пойманной добычи */
    @FXML
    private Label priceFishСatchTextField;

    /** Уменьшение прочности удочки */
    @FXML
    private Label fishingRodDeleteLabel;

    /** Попдавок */
    @FXML
    private Button floatButton;

    @FXML
    private Label moneyTextLabel;

    @FXML
    private Label fishingRodTextLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView bgroundImage;

    @FXML
    private GridPane gameOverGridPane;

    @FXML
    private ListView leaderboard;

    private List<Player> leaders;

    private FishCatchPresenter fishCatchPresenter;

    /** Текущий игрок */
    private Player player;

    private BaseHandler baseHandler;
    private Fish fishCatch;

    Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(player == null)
            newGamePanel.setVisible(true);
        else
            newGamePanel.setVisible(false);

        leaders = new ArrayList<Player>();

        fishCatchPresenter = new FishCatchPresenter(fishСatchView, nameFishСatchTextField, priceFishСatchTextField, fishingRodDeleteLabel);


        bgroundImage.setImage(new Image(getClass().getResourceAsStream("/Bg_3.jpg")));


        floatButton.setVisible(false);

        fishingRegion.onMousePressedProperty().setValue(mouseEvent -> {

            floatButton.setTranslateX(mouseEvent.getX());
            floatButton.setTranslateY(mouseEvent.getY());

            floatButton.setVisible(true);

            BaseHandler negativeHandler = new NegativeHandler(null);
            BaseHandler baseHandler5 = new FishHandler(negativeHandler, new Fish("Колесо", 0, 40), 0.7f);
            BaseHandler baseHandler4 = new FishHandler(baseHandler5, new Fish("Золотая рыбка", 400, 20), 0.01f);
            BaseHandler baseHandler3 = new FishHandler(baseHandler4, new Fish("Черная рыбка", 200, 15), 0.1f);
            BaseHandler baseHandler2 = new FishHandler(baseHandler3, new Fish("Голубая рыбка", 100, 10), 0.3f);
            baseHandler = new FishHandler(baseHandler2, new Fish("Красная рыбка", 200, 5), 0.3f);


            if(timeline != null)
                timeline.stop();

            timeline = new Timeline (
                    new KeyFrame(
                            Duration.millis(1000), //1 сек
                            ae -> {
                                Catch(baseHandler.check());
                                return;
                            }
                    )
            );

            timeline.play();

        });
    }

    @FXML
    protected void onCreatePlayer() {

        if(inputNameTextField.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Ошибка ввода имени");
            alert.setTitle("Введите корректроне имя!");

            alert.showAndWait();
            return;
        }

        player = new Player(inputNameTextField.getText().trim());
        player.setFishingRod(new FishingRod(100));

        newGamePanel.setVisible(false);
        gameOverGridPane.setVisible(false);

        UpdateMoneyText();
    }

    public void Catch (Fish fish) {

        floatButton.visibleProperty().setValue(false);

        this.fishCatch = fish;
        fishCatchPresenter.setFish(fish);
        fishCatchPresenter.show();
    }

    @FXML
    public void onAddFishCatch () {

        fishCatchPresenter.hide();

        player.addMoney(fishCatch.getPrice());
        player.getFishingRod().deleteCondition(fishCatch.getDeleteFishingRod());

        UpdateMoneyText();

        if(player.getFishingRod().getCurrentCondition() == 0) {

            gameOverGridPane.setVisible(true);
        }
    }

    @FXML
    public void onGameOver () {

        newGamePanel.setVisible(true);
        inputNameTextField.setText("");
    }

    @FXML
    public void onExit () {

        if (timeline != null)
            timeline.stop();

        newGamePanel.setVisible(true);
        inputNameTextField.setText("");

        if(leaders.size() < 10)
            leaders.add(player);
        else if (leaders.get(leaders.size() - 1).getMoney() < player.getMoney())
            leaders.add(player);


        leaders.sort(Comparator.comparing(Player::getMoney).reversed());

        leaderboard.getItems().clear();
        for (Player item: leaders) {

            leaderboard.getItems().add(item);
        }

    }

    public void UpdateMoneyText () {
        moneyTextLabel.setText("Монеты: " + player.getMoney());
        fishingRodTextLabel.setText("Удочка: " + player.getFishingRod().getCurrentCondition() + "/" + player.getFishingRod().getMaxCondition());
        nameLabel.setText("Рыбак: " + player.getName());
    }
}