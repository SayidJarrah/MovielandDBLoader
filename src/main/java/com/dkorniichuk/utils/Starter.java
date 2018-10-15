package com.dkorniichuk.utils;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class Starter extends Application {
    private AbstractApplicationContext context;

    public static void main(String[] args) {
        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        SpringFxmlLoader loader = new SpringFxmlLoader(context);
        Parent panel = (Parent) loader.load("/main.fxml");
        Scene scene = new Scene(panel,750,400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("MovieLand DB Loader v1.0");
        primaryStage.show();
    }

    @Override
    public void stop() {
        context.close();
    }
}
