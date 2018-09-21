package FX;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import FX.FX;

public class ToggleSwitch extends HBox {
	
	private final Label label = new Label();
	private final Button button = new Button();

	
	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
	public SimpleBooleanProperty switchOnProperty() { return switchedOn; }
	
	private void init() {		
		label.setText("OFF");	
		getChildren().addAll(label, button);	
		button.setOnAction((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		label.setOnMouseClicked((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		setStyle();
		bindProperties();
	}
	
	private void setStyle() {
		//Default Width
		setWidth(80);
		label.setAlignment(Pos.CENTER);
		setStyle("-fx-background-color: red; -fx-text-fill:black; -fx-background-radius: 4;");
		setAlignment(Pos.CENTER_LEFT);
	}
	
	private void bindProperties() {
		label.prefWidthProperty().bind(widthProperty().divide(2));
		label.prefHeightProperty().bind(heightProperty());
		button.prefWidthProperty().bind(widthProperty().divide(2));
		button.prefHeightProperty().bind(heightProperty());
	}
	
	public ToggleSwitch() {
		init();
		switchedOn.addListener((a,b,c) -> {
			if (c) {
                		label.setText("ON");
                		System.out.println("Toggle ON");
                		FX.testProtocol = true;
                		setStyle("-fx-background-color: green;");
                		label.toFront();
            		}
            		else {
            			label.setText("OFF");
            			System.out.println("Toggle OFF");
            			FX.testProtocol = false;
            			setStyle("-fx-background-color: red;");
                		button.toFront();
            		}
		});
	}
}