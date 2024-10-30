/**
 * Controlador de la interfaz de usuario para la aplicación del reloj digital.
 * Esta clase implementa {@link javafx.fxml.Initializable} para permitir la
 * inicialización del controlador después de que se ha cargado el archivo FXML.
 *
 * <p>
 * La clase gestiona la visualización de la hora actual utilizando imágenes de
 * dígitos y actualiza la interfaz cada segundo.
 * </p>
 */
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

    // Definición de los elementos de la interfaz que representan cada dígito
    @FXML
    private ImageView h1; // Dígito de las horas (decenas)
    @FXML
    private ImageView h2; // Dígito de las horas (unidades)
    @FXML
    private ImageView min1; // Dígito de los minutos (decenas)
    @FXML
    private ImageView min2; // Dígito de los minutos (unidades)
    @FXML
    private ImageView s1; // Dígito de los segundos (decenas)
    @FXML
    private ImageView s2; // Dígito de los segundos (unidades)

    // Array de rutas de imágenes para representar cada dígito del 0 al 9
    private final String[] digitImages = {
            "/com/example/k/images/ZERO.png", "/com/example/k/images/ONE.png",
            "/com/example/k/images/TWO.png", "/com/example/k/images/THREE.png",
            "/com/example/k/images/FOUR.png", "/com/example/k/images/FIVE.png",
            "/com/example/k/images/SIX.png", "/com/example/k/images/SEVEN.png",
            "/com/example/k/images/EIGHT.png", "/com/example/k/images/NINE.png"};

    /**
     * Actualiza los dígitos del reloj cada segundo utilizando la hora actual del sistema.
     * Se obtienen las horas, minutos y segundos, que se formatean y se utilizan para
     * actualizar las imágenes correspondientes.
     */
    private void updateClock() {
        LocalDateTime now = LocalDateTime.now(); // Obtiene la hora actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss"); // Define el formato de la hora
        String formattedTime = now.format(formatter); // Formatea la hora actual

        // Actualizar las imágenes de los dígitos
        try {
            setDigitImage(h1, formattedTime.charAt(0)); // Horas (decenas)
            setDigitImage(h2, formattedTime.charAt(1)); // Horas (unidades)
            setDigitImage(min1, formattedTime.charAt(2)); // Minutos (decenas)
            setDigitImage(min2, formattedTime.charAt(3)); // Minutos (unidades)
            setDigitImage(s1, formattedTime.charAt(4)); // Segundos (decenas)
            setDigitImage(s2, formattedTime.charAt(5)); // Segundos (unidades)
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones si algo falla al establecer la imagen
        }
    }

    /**
     * Establece la imagen del dígito correspondiente en un ImageView dado.
     *
     * @param imageView El ImageView en el que se va a establecer la imagen del dígito.
     * @param digit El carácter que representa el dígito (de '0' a '9').
     */
    private void setDigitImage(ImageView imageView, char digit) {
        int index = Character.getNumericValue(digit); // Convierte el carácter a un valor numérico
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(digitImages[index])));
        imageView.setImage(image); // Establece la imagen en el ImageView
    }

    /**
     * Método de inicialización llamado por el sistema JavaFX al cargar el archivo FXML.
     * Configura el reloj y programa su actualización cada segundo.
     *
     * @param url La URL que apunta al recurso.
     * @param resourceBundle El ResourceBundle que contiene los recursos localizados.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateClock(); // Actualización inicial del reloj
        Timer timer = new Timer(true); // Crea un temporizador en segundo plano
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                javafx.application.Platform.runLater(() -> updateClock()); // Actualiza el reloj en el hilo de la interfaz
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); // Programa la tarea para que se ejecute cada segundo
    }
}
