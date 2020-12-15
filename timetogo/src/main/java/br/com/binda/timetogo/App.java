package br.com.binda.timetogo;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

	private static Stage stage;

	private static Scene mainScene;
	private static Scene detailsScene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;

		Parent fxmlMain = FXMLLoader.load(getClass().getResource("/fxml/primary.fxml"));
		mainScene = new Scene(fxmlMain, 690, 467);

		Parent fxmlDetails = FXMLLoader.load(getClass().getResource("/fxml/editPage.fxml"));
		detailsScene = new Scene(fxmlDetails, 690, 467);

		primaryStage.setTitle("Time To Go");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void changeScreen(EVldTela scr, Object userData) {
		switch (scr) {
		case DETAIL_PAGE:
			stage.setScene(detailsScene);
			break;
		case MAIN:
			stage.setScene(mainScene);
			break;
		}
		notifyAllListeners(scr, userData);
	}

	public static void changeScreen(EVldTela scr) {
		changeScreen(scr, null);
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**/

	private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

	public static interface OnChangeScreen {
		void onScreenChanged(EVldTela newScreen, Object userData);
	}

	public static void addOnChangeScreenListener(OnChangeScreen newListener) {
		listeners.add(newListener);
	}

	private static void notifyAllListeners(EVldTela newScreen, Object userData) {
		for (OnChangeScreen s : listeners)
			s.onScreenChanged(newScreen, userData);
	}

	public enum EVldTela {
		MAIN("main"), //
		DETAIL_PAGE("detailPage");

		private String value;

		EVldTela(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
}
