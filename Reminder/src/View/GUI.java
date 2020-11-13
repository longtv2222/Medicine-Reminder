package View;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import Model.Alarm;
import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Doctor appointment also
public class GUI {
	private String css;
	private BorderPane borderPane;
	private Controller controller;

	public GUI(Controller controller) {
		setBorderPane(new BorderPane());
		css = getClass().getResource("Style.css").toString();
		this.controller = controller;
	}

	public Button roundedButton(String buttonName, int width, int height) {
		Button button = new Button(buttonName);
		button.setPrefHeight(height);
		button.setPrefWidth(width);
		button.setId("one_rounded_button");

		button.setOnMouseEntered(e -> button.setId("hover_button"));
		button.setOnMouseExited(e -> button.setId("one_rounded_button"));
		button.setOnMousePressed(e -> button.setId("pressed_button"));
		button.setOnMouseReleased(e -> button.setId("hover_button"));
		return button;
	}

	private VBox leftPanel(String userName) {
		VBox vbox = new VBox();

		vbox.setId("left_panel");
		VBox subVbox = new VBox();
		subVbox.setId("subVbox");
		ArrayList<Button> buttonList = controller.leftPanelButtons();

		subVbox.getChildren().addAll(buttonList);

		ScrollPane sp = new ScrollPane();
		sp.setId("scroll_bar_left_panel");
		sp.setContent(subVbox);

		Text welcome = new Text("Hi," + userName + "!\n");
		welcome.setId("welcome_text");
		vbox.getChildren().addAll(welcome, sp, subVbox);
		return vbox;
	}



	public StackPane alarmView(String alarmTimeName, String alarmNote, Alarm alarm2) {
		StackPane sp = new StackPane();
		GridPane grid = new GridPane();
		HBox hbox = new HBox(50);
		grid.setHgap(10);
		grid.setVgap(10);
		Label alarm = new Label(alarmTimeName);
		alarm.setMinWidth(220);

		Label noti = new Label(alarmNote);

		hbox.getChildren().addAll(alarm, toggleButton(alarm2));

		grid.add(hbox, 4, 2);
		grid.add(noti, 4, 3);

		Rectangle rect = new Rectangle(350, 100);
		rect.setId("center_rectangle");
		sp.getChildren().addAll(rect, grid);
		return sp;
	}

	public StackPane medView(String medName) {
		StackPane sp = new StackPane();
		BorderPane bp = new BorderPane();
		Label name = new Label(medName);
		bp.setCenter(name);
		Rectangle rect = new Rectangle(350, 100);
		rect.setId("center_rectangle");
		sp.getChildren().addAll(rect, bp);
		return sp;
	}

	private ToggleButton toggleButton(Alarm alarm2) {
		ToggleButton button = new ToggleButton();
		button.setId("toggle_button_clicked_action");
		button.setOnMouseClicked(e -> {
			if (button.isSelected()) {
				button.setId("toggle_button");
				alarm2.setStatus(false);
			} else {
				button.setId("toggle_button_clicked_action");
				alarm2.setStatus(true);
			}
		});
		return button;
	}

	public VBox centerPanel(ArrayList<StackPane> stackPane, String centerTitle) {
		VBox vbox = new VBox();
		vbox.setId("center_panel");

		VBox subvbox = new VBox(30);

		subvbox.setId("subVbox");

		subvbox.getChildren().addAll(stackPane);

		ScrollPane sp = new ScrollPane();
		sp.setId("scroll_bar_center_panel");
		sp.setContent(subvbox);

		Text text = new Text(centerTitle + "\n");
		text.setId("center_text");
		vbox.getChildren().addAll(text, subvbox, sp);

		return vbox;
	}

	private VBox rightPanel() {
		VBox vbox = new VBox();
		vbox.setId("right_panel");
		return vbox;
	}

	public void createGUI() {
		getBorderPane().setId("border_pane");
		getBorderPane().setRight(rightPanel());

		Scene homeScene = new Scene(getBorderPane());
		homeScene.getStylesheets().add(css);

		Stage home = new Stage();
		home.setWidth(1280);
		home.setHeight(720);
		home.setScene(homeScene);
		home.show();
	}

	public void createLeftPanel(String userName) {
		getBorderPane().setLeft(leftPanel(userName));
	}

	public void createCenterPanel() {
		controller.viewAlarm();
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}

	public void setBorderPane(BorderPane borderPane) {
		this.borderPane = borderPane;
	}

}
