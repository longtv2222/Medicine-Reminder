package View;

import javafx.scene.shape.Rectangle;

import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI extends Application {
	private String css = getClass().getResource("Style.css").toString();
	
	public static void display(String title, String message, String buttonMsg) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);

		Label label = new Label(message);
		Button button = new Button(buttonMsg);
		button.setOnAction(e -> window.close());

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, button);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		alarmNotification("Wake up", "11", "36");
	}

	Button roundedButton(String name, int width, int height, int ArcHeight, int ArcWidth) {
		Rectangle roundedButton = new Rectangle();
		roundedButton.setWidth(width);
		roundedButton.setHeight(height);
		roundedButton.setArcHeight(ArcHeight);
		roundedButton.setArcWidth(ArcWidth);

		Button button = new Button(name);
		button.setPrefSize(width, height);
		button.setShape(roundedButton);
		return button;
	}

	public void alarmNotification(String alarmName, String hour, String minute) {
		Button turnOff = roundedButton("Turn off", 222, 100, 60, 60);
		Button snooze = roundedButton("Snooze", 222, 100, 60, 60);
	
		HBox hbox = new HBox(48);
		hbox.setPadding(new Insets(0, 57, 79, 57));
		hbox.setAlignment(Pos.TOP_CENTER);
		hbox.getChildren().addAll(turnOff, snooze); // Add 2 buttons to Hbox
		hbox.getStylesheets().add(css);
		hbox.setId("font-button");  //Format button in this row

		Text t = new Text("11:24\n2020-11-05");
		t.setTextAlignment(TextAlignment.CENTER);

		BorderPane borderpane = new BorderPane();
		borderpane.setCenter(t);
		borderpane.setBottom(hbox);

		Scene add = new Scene(borderpane);

		Stage popup = new Stage();
		popup.setHeight(600);
		popup.setWidth(600);
		popup.setResizable(false);
		popup.setScene(add);
		popup.showAndWait();
	}

}
