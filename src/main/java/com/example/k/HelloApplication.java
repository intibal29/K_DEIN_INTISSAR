/**
 * Clase principal de la aplicación JavaFX que carga la interfaz de usuario y establece el ícono y el título de la ventana.
 * Esta clase extiende la clase {@link javafx.application.Application} y utiliza un archivo FXML
 * para definir la interfaz de usuario.
 *
 * <p>
 * El objetivo de la aplicación es cargar un reloj gráfico que se actualiza en tiempo real
 * y mostrarlo en una ventana de JavaFX.
 * </p>
 */
package com.example.k;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloApplication extends Application {

    /**
     * Método principal de inicio de la aplicación. Configura la interfaz de usuario,
     * el ícono, el título y muestra la ventana principal de la aplicación.
     * Este método se ejecuta cuando se inicia la aplicación.
     *
     * @param primaryStage La ventana principal (Stage) proporcionada por el entorno de JavaFX.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carga la estructura de la interfaz definida en el archivo FXML.
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/k/hello-view.fxml")));

            // Crea una escena con el diseño especificado en el archivo FXML.
            Scene scene = new Scene(root);

            // Carga y establece el ícono de la aplicación desde el recurso en el directorio especificado.
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/k/images/Icono.ico")));
            primaryStage.getIcons().add(icon);

            // Establece la escena en la ventana principal, asigna un título, y muestra la ventana.
            primaryStage.setScene(scene);
            primaryStage.setTitle("RELOJ");
            primaryStage.show();
        } catch(Exception e) {
            // Captura y muestra cualquier excepción que ocurra durante el inicio de la aplicación.
            e.printStackTrace();
        }
    }

    /**
     * Método main de la aplicación. Inicia el lanzamiento de la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
