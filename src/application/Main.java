package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Tablero;

import static helpers.Constants.*;

public class Main extends Application {
	private Scene scene;
	private Stage stage;
	private Tablero tablero;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage principal) throws Exception {
		try
		{
			stage = principal;
			stage.getIcons().add(new Image("img/ajedrez.png"));
			
			Button jugar = new Button("Jugar");
			Button continuar = new Button("Continuar");
			Button salir = new Button("Salir");
			
			Image fondo = new Image("img/fondo.png");
			ImageView fondomenu = new ImageView(fondo);
			fondomenu.setFitHeight(ALTO);
			fondomenu.setFitWidth(ANCHO);
			
			VBox botonesP = new VBox(20, jugar, continuar, salir);
			botonesP.setAlignment(Pos.CENTER);
			botonesP.setAlignment(Pos.CENTER);
			botonesP.getChildren().forEach(e->{
				e.setStyle("-fx-text-fill:whitesmoke;"
						+ "-fx-font-size:15;"
						+ "-fx-border-color:black;"
						+ "-fx-font-family: Arial;"
						+ "-fx-background-color:linear-gradient(#20262b, #191d22);");
			});
			
			Text creditos = new Text("Creado por Daniel Marchena Jiménez");
			creditos.setFont(new Font(10));
			creditos.setFill(Color.WHITESMOKE);
			
			BorderPane menu = new BorderPane(botonesP, null, null, creditos, null);
			menu.setBackground(new Background(new BackgroundImage(fondo, null, null, null, new BackgroundSize(ANCHO, ALTO, false, false, false, false))));
			
			scene = new Scene(menu,ANCHO,ALTO);
			
			stage.setScene(scene);
			stage.setTitle("Ajedrez");
			stage.setResizable(false);
			stage.show();
			
			salir.setOnAction(e->System.exit(0));
			
			jugar.setOnAction(e->{
				tablero = new Tablero();
				
				scene.setRoot(tablero);
			});
			
			continuar.setOnAction(e->{
				if (tablero == null)
				{
					tablero = new Tablero();
				}
				
				scene.setRoot(tablero);
			});
			
			scene.setOnKeyPressed(e->{
				if (e.getCode()==KeyCode.ESCAPE) {
					scene.setRoot(menu);
				}
			});
			
		} catch(Exception e) {
			System.err.println("Ha ocurrido un error, por favor reinicia la aplicación");
		}
	}
}
