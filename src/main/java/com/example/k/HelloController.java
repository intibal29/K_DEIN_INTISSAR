package com.example.k;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelloController implements Initializable {

    @FXML
    private ImageView h1;
    @FXML
    private ImageView h2;
    @FXML
    private ImageView min1;
    @FXML
    private ImageView min2;
    @FXML
    private ImageView s1;
    @FXML
    private ImageView s2;

    // Array de imágenes para representar dígitos
    private final String[] digitImages = {
            "/com/example/k/images/ZERO.png", "/com/example/k/images/ONE.png", "/com/example/k/images/TWO.png",
            "/com/example/k/images/THREE.png", "/com/example/k/images/FOUR.png", "/com/example/k/images/FIVE.png",
            "/com/example/k/images/SIX.png", "/com/example/k/images/SEVEN.png", "/com/example/k/images/EIGHT.png",
            "/com/example/k/images/NINE.png"};

    private void updateClock() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedTime = now.format(formatter);

        // Actualizar las imágenes de los dígitos
        try {
            setDigitImage(h1, formattedTime.charAt(0));
            setDigitImage(h2, formattedTime.charAt(1));
            setDigitImage(min1, formattedTime.charAt(2));
            setDigitImage(min2, formattedTime.charAt(3));
            setDigitImage(s1, formattedTime.charAt(4));
            setDigitImage(s2, formattedTime.charAt(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDigitImage(ImageView imageView, char digit) {
        int index = Character.getNumericValue(digit);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(digitImages[index])));
        imageView.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateClock(); // Actualización inicial
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                javafx.application.Platform.runLater(() -> updateClock());
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); // Actualización cada segundo
    }
}
