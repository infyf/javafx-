package org.example.javafxlr16;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CirclePane extends Pane {


    public CirclePane() {
        setPrefSize(400, 400);

        ToggleGroup colorGroup = new ToggleGroup();

        RadioButton redButton = new RadioButton("Red");
        redButton.setToggleGroup(colorGroup);
        redButton.setSelected(true);
        redButton.setUserData("Red");

        RadioButton blueButton = new RadioButton("Blue");
        blueButton.setToggleGroup(colorGroup);
        blueButton.setUserData("Blue");

        RadioButton greenButton = new RadioButton("Green");
        greenButton.setToggleGroup(colorGroup);
        greenButton.setUserData("Green");

        getChildren().addAll(redButton, blueButton, greenButton);
        redButton.setLayoutY(10);
        blueButton.setLayoutY(30);
        greenButton.setLayoutY(50);

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Color selectedColor = getColor(colorGroup.getSelectedToggle().getUserData().toString());
                double radius = 20;
                Circle circle = new Circle(event.getX(), event.getY(), radius, selectedColor);
                getChildren().add(circle);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (event.getTarget() instanceof Circle) {
                    Circle circle = (Circle) event.getTarget();
                    getChildren().remove(circle);
                }
            }
        });
    }

    private Color getColor(String colorName) {
        switch (colorName) {
            case "Red":
                return Color.RED;
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            default:
                return Color.BLACK;
        }
    }
}

Лістинг коду: 
package org.example.javafxlr16;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        CirclePane circlePane = new CirclePane();
        Scene scene = new Scene(circlePane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Circle Drawer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
