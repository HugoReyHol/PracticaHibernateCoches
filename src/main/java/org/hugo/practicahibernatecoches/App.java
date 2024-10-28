package org.hugo.practicahibernatecoches;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hugo.practicahibernatecoches.controller.MenuCtrll;
import org.hugo.practicahibernatecoches.util.R;
import java.io.IOException;


public class App extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("menu.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Menu coches");
		stage.setScene(scene);

		// Cerrar el programa
		MenuCtrll menuCtrll = fxmlLoader.getController();
		stage.setOnCloseRequest(e -> menuCtrll.close());

		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}