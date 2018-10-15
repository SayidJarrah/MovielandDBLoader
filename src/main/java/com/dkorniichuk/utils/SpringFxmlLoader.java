package com.dkorniichuk.utils;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SpringFxmlLoader {
    Logger logger = LoggerFactory.getLogger(SpringFxmlLoader.class);

    private ApplicationContext context;

    public SpringFxmlLoader(ApplicationContext context) {
        this.context = context;
    }

    public Object load(String resource) {
        try (InputStream fxmlStream = getClass().getResourceAsStream(resource)) {
            FXMLLoader loader = new FXMLLoader();
            URL location = getClass().getResource(resource);
            loader.setLocation(location);
            loader.setControllerFactory(context::getBean); //TODO: take a look
            return loader.load(fxmlStream);
        } catch (IOException e) {
            logger.error("SpringFXMLLoader error :" + e);
            throw new RuntimeException(e);
        }

    }


}
