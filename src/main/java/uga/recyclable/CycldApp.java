package uga.recyclable;

import javafx.application.ConditionalFeature;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;
import javafx.scene.text.Text;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionScopes;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;
import com.google.api.gax.paging.Page;
//import com.google.auth.appengine.AppEngineCredentials;
import com.google.auth.oauth2.ComputeEngineCredentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionScopes;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.cloud.vision.v1.*;
//import com.google.protobuf.ByteString;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.event.Event;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import java.io.FileInputStream;

import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;

import com.google.common.collect.ImmutableList;

import javafx.scene.control.ToolBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
//import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
//import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

/**
 * This class is the driver class for the Gallery application, it implements
 * other methods and classes necessary to run a simulation of an iTunes picture
 * gallery.
 * 
 * @author MaryAshley Etefia
 * @author Twumasi Pennoh
 * @author Caitlin Jones
 */
public class CycldApp extends Application {
	//Add an option for the user to tells us if we made a mistake deeming it un-recyclable and tell us what their item is
	
	public Text texty;
	public Text one;
	public Text two;
	public Text three;

	@Override
	public void start(Stage stage) {
		VBox vbox = new VBox();
		Pane canvas = new Pane();
		canvas.setPrefSize(500, 500);
		Scene scene = new Scene(vbox);
		HBox menu = new HBox();
		// Creates a menu list
		Menu file = new Menu("File");
		Menu help = new Menu("Help");
		// Creates menu item for File
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction((event) -> Platform.exit());
		file.getItems().add(exit);
		MenuBar menuBar = new MenuBar();
		// Adds menu items to menu bar
		menuBar.getMenus().addAll(file, help);
		// Sizes menu bar to fit the stage's width
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		menu.getChildren().add(menuBar);

		canvas.setStyle("-fx-background-color: #008B8B;");
		javafx.scene.image.Image l = new javafx.scene.image.Image(
				"https://i.ibb.co/h9G1dyv/seeds-clipart-garden-seed-152732-6104978.png");
		ImageView logo = new ImageView();
		logo.setImage(l);
		logo.setX(60);
		javafx.scene.image.Image c = new javafx.scene.image.Image(
				"https://i.ibb.co/B4MFv6t/imageedit-1-8363157059.png");
		ImageView cyc = new ImageView();
		cyc.setImage(c);
		cyc.setX(175);
		cyc.setY(400);

		logo.setFitWidth(400);
		logo.setFitHeight(400);
		canvas.getChildren().addAll(logo, cyc);
		vbox.getChildren().addAll(menu, canvas);
		Text title = new Text("\"CYCLD\"");
		title.setFont(new Font(40));
		title.setX(75);
		title.setY(75);
		canvas.getChildren().add(title);
		// HomePage
		canvas.setOnMouseClicked(e -> {
			Stage stage2 = new Stage();
			VBox pane = new VBox(); // everything goes on this
			pane.setMinHeight(500);
			pane.setMinWidth(500);

			VBox buttons = new VBox(15); // buttons on home page go here
			Button scanObject = new Button("Scan object");
			scanObject.setPadding(new Insets(15)); // sets padding for scan object
			scanObject.setFont(new Font(15)); // sets text size to 20
			scanObject.setOnAction(event -> {
				setScanObjectAction();
				loadRecyclablePage(stage);
			});

			Button searchObject = new Button("Search object");
			searchObject.setPadding(new Insets(15));
			searchObject.setFont(new Font(15)); // sets text size to 20
			Text instr = new Text("Please give the camera at least 15 seconds to screenshot your item!");
			instr.setFont(new Font(20));
			buttons.getChildren().addAll(scanObject, searchObject, instr);
			buttons.setAlignment(Pos.CENTER); // centers the VBox

			pane.setMargin(buttons, new Insets(150, 0, 150, 0));
			pane.getChildren().addAll(menuBar, buttons); // adds everything to scene

			Scene scene2 = new Scene(pane);
			stage.setScene(scene2);
			stage.sizeToScene();
		});

		// Sizes and gives title to the application
		stage.setMaxWidth(500);
		stage.setMaxHeight(500);
		stage.setTitle("UGAHACKS 4 - CYCLD");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		try {
			TimeUnit.SECONDS.sleep(4);
			Stage stage2 = new Stage();
			VBox pane = new VBox(); // everything goes on this
			pane.setMinHeight(500);
			pane.setMinWidth(500);

			VBox buttons = new VBox(15); // buttons on home page go here
			Button scanObject = new Button("Scan object");
			scanObject.setPadding(new Insets(15)); // sets padding for scan object
			scanObject.setFont(new Font(15)); // sets text size to 20
			scanObject.setOnAction(event -> {
				setScanObjectAction();
				loadRecyclablePage(stage);
			});

			Button searchObject = new Button("Search object");
			searchObject.setPadding(new Insets(15));
			searchObject.setFont(new Font(15)); // sets text size to 20
			Text instr = new Text("Please give the camera at least 15 seconds to screenshot your item!");
			instr.setFont(new Font(20));
			buttons.getChildren().addAll(scanObject, searchObject, instr);
			buttons.setAlignment(Pos.CENTER); // centers the VBox

			pane.setMargin(buttons, new Insets(150, 0, 150, 0));
			pane.getChildren().addAll(menuBar, buttons); // adds everything to scene

			Scene scene2 = new Scene(pane);
			stage.setScene(scene2);
			stage.sizeToScene();

		} catch (Exception e) {
			System.out.println("Scene 2 not showing");
		}

	} // start

	private void setScanObjectAction() {
		Platform.isSupported(ConditionalFeature.SCENE3D);
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(320, 240));

		WebcamPanel webcamPanel = new WebcamPanel(webcam);
		webcamPanel.setMirrored(true);

		JFrame frame = new JFrame();
		frame.add(webcamPanel);
		frame.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		Point newLocation = new Point(middle.x - (frame.getWidth() / 2), middle.y - (frame.getHeight() / 2));
		frame.setLocation(newLocation);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		try {
			webcam.open();
			TimeUnit.SECONDS.sleep(5);
			String filePath = "C:\\Users\\Ashley\\Pictures\\ugahacksdemo.jpg";
			ImageIO.write(webcam.getImage(), "JPG", new File(filePath));
			webcam.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		frame.dispose();
	}

	private void loadRecyclablePage(Stage stage) {
		VBox pane = new VBox(); // everything goes on this
		pane.setMinHeight(500);
		pane.setMinWidth(500);

		MenuBar menuBar = new MenuBar();
		menuBar.setMinWidth(500);
		Menu menu1 = new Menu("File");
		Menu menu2 = new Menu("About");
		menuBar.getMenus().addAll(menu1, menu2);

		HBox picture = new HBox();
		String filePath = "C:\\Users\\Ashley\\Pictures\\ugahacksdemo.jpg";
		File f = new File(filePath);
		javafx.scene.image.Image image1 = new javafx.scene.image.Image(f.toURI().toString());
		ImageView imageViewer = new ImageView();
		imageViewer.setImage(image1);
		imageViewer.setFitHeight(280.0);
		imageViewer.setFitWidth(350.0);
		texty = new Text("");
		texty.setFont(new Font(20));
		String[] arr = new String[3];
		try {
			String[] s = new String[1];
			s[0] = filePath;
			Path imagePath = Paths.get(filePath);
			LabelApp app = new LabelApp(LabelApp.getVisionService());
			app.printLabels(System.out, imagePath, app.labelImage(imagePath, 3), arr);
			
		} catch (GeneralSecurityException g) {
			System.out.println("Security Exception!");
			g.printStackTrace();
		} catch (IOException i) {
			System.out.println("IO Exception!");
			i.printStackTrace();
		}

		picture.getChildren().addAll(imageViewer);
		picture.setAlignment(Pos.CENTER); // centers the VBox
		
		one = new Text(arr[0]);
		two = new Text(arr[1]);
		three = new Text(arr[2]);
		one.setFont(new Font(15));
		two.setFont(new Font(15));
		three.setFont(new Font(15));
		int r = 0;
		for (int k = 0; k <= 2; k++) {
			if (!(arr[k].equals(null))) {
				if (arr[k].contains("Plastic")) {
					r++;
				}
				//Add any other terms deemed as recyclable
				if (arr[k].contains("Transparent")) {
					r++;
				}
				if (arr[k].contains("Lays")) {
					r++;
				}
			}
		}
		if (r == 0) {
			texty.setText("This item is NOT recyclable!");
		}
		if (r > 0) {
			texty.setText("This item IS recyclable!");
		}
		
		pane.getChildren().addAll(menuBar, picture, one, two, three, texty);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.sizeToScene();
	}

	/**
	 * This method launches the Recycle application.
	 * 
	 * @param args allows for the system's string outputs
	 */
	public static void main(String[] args) {
		try {
			Application.launch(args);

		} catch (UnsupportedOperationException e) {
			System.out.println(e);
			System.err.println("If this is a DISPLAY problem, then your X server connection");
			System.err.println("has likely timed out. This can generally be fixed by logging");
			System.err.println("out and logging back in.");
			System.exit(1);
		} // try
	} // main

} // CycldApp
