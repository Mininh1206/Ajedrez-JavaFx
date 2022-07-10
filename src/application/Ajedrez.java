package application;
	
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Ajedrez extends Application {
	
	private final static double PXBLOQUE = 50;
	private final static double BLOQUES = 8;
	private final static double ANCHO = PXBLOQUE*BLOQUES; 
	private final static double ALTO = PXBLOQUE*BLOQUES;
	private static Pane tablero;
	private static ImageView piezaSelec = new ImageView();
	private static int turno;
	private static ArrayList<ImageView> piezasn;
	private static ArrayList<ImageView> piezasb;
	private static Scene scene;
	private static Stage stage;
	private static ArrayList<Rectangle> movimientos;
	
	//Cargo todas las imagenes
	
	private static Image peonb = new Image("img/peonb.png");
	private static Image peonn = new Image("img/peonn.png");
	private static Image alfilb = new Image("img/alfilb.png");
	private static Image alfiln = new Image("img/alfiln.png");
	private static Image torreb = new Image("img/torreb.png");
	private static Image torren = new Image("img/torren.png");
	private static Image caballob = new Image("img/caballob.png");
	private static Image caballon = new Image("img/caballon.png");
	private static Image reyb = new Image("img/reyb.png");
	private static Image reyn = new Image("img/reyn.png");
	private static Image reinab = new Image("img/reinab.png");
	private static Image reinan = new Image("img/reinan.png");
	
	private static boolean mreyn;
	private static boolean mreyb;
	
	@Override
	public void start(Stage principal) {
		try {
			
			stage = principal;
			stage.getIcons().add(new Image("img/ajedrez.png"));
			
			//Creo los botones y los añado a su Pane para crear el menu
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
				crearTablero();
				scene.setRoot(tablero);
			});
			
			continuar.setOnAction(e->{
				if (tablero == null)
					crearTablero();
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
	
	//Crea el tablero con las cuadriculas
	
	public static void crearTablero() {
		
		resetearTablero();
		
		ArrayList<Rectangle> cuadriculas = new ArrayList<Rectangle>();
		
		for (int y = 0;y<BLOQUES;y++) {
			for (int x = 0;x<BLOQUES;x++) {
				Rectangle nc = new Rectangle(x*PXBLOQUE, y*PXBLOQUE, PXBLOQUE, PXBLOQUE);;
				if((x%2!=0 && y%2==0) || (x%2==0 && y%2!=0)) {
					nc.setFill(Color.rgb(100, 100, 100));
				} else {
					nc.setFill(Color.rgb(160, 160, 160));
				}
				nc.setId("tablero");
				nc.setOnMouseClicked(e->quitarMov());
				cuadriculas.add(nc);
			}
		}
		
		tablero.getChildren().addAll(cuadriculas);
		
		//Añado las piezas negras al tablero
		
		ImageView torren1 = new ImageView(torren);
		torren1.setId("torre");
		ImageView caballon1 = new ImageView(caballon);
		caballon1.setId("caballo");
		ImageView alfiln1 = new ImageView(alfiln);
		alfiln1.setId("alfil");
		ImageView reinan1 = new ImageView(reinan);
		reinan1.setId("reina");
		ImageView reyn1 = new ImageView(reyn);
		reyn1.setId("rey");
		ImageView alfiln2 = new ImageView(alfiln);
		alfiln2.setId("alfil");
		ImageView caballon2 = new ImageView(caballon);
		caballon2.setId("caballo");
		ImageView torren2 = new ImageView(torren);
		torren2.setId("torre");
		ImageView peonn1 = new ImageView(peonn);
		peonn1.setId("peonn");
		ImageView peonn2 = new ImageView(peonn);
		peonn2.setId("peonn");
		ImageView peonn3 = new ImageView(peonn);
		peonn3.setId("peonn");
		ImageView peonn4 = new ImageView(peonn);
		peonn4.setId("peonn");
		ImageView peonn5 = new ImageView(peonn);
		peonn5.setId("peonn");
		ImageView peonn6 = new ImageView(peonn);
		peonn6.setId("peonn");
		ImageView peonn7 = new ImageView(peonn);
		peonn7.setId("peonn");
		ImageView peonn8 = new ImageView(peonn);
		peonn8.setId("peonn");
		
		piezasn.addAll(Arrays.asList(torren1, caballon1, alfiln1, reinan1, reyn1, alfiln2, caballon2, torren2, peonn1, peonn2, peonn3, peonn4, peonn5, peonn6, peonn7, peonn8));
		
		for (int pos = 0;pos<piezasn.size();pos++) {
			ImageView pieza = piezasn.get(pos);
			pieza.setOnMouseClicked(e->{
				piezaSelec = pieza;
				comprobarPieza(pieza);
			});
		}
		
		tablero.getChildren().addAll(piezasn);
		
		//Añado las piezas blancas al tablero
		
		ImageView torreb1 = new ImageView(torreb.getUrl());
		torreb1.setId("torre");
		ImageView caballob1 = new ImageView(caballob);
		caballob1.setId("caballo");
		ImageView alfilb1 = new ImageView(alfilb);
		alfilb1.setId("alfil");
		ImageView reinab1 = new ImageView(reinab);
		reinab1.setId("reina");
		ImageView reyb1 = new ImageView(reyb);
		reyb1.setId("rey");
		ImageView alfilb2 = new ImageView(alfilb);
		alfilb2.setId("alfil");
		ImageView caballob2 = new ImageView(caballob);
		caballob2.setId("caballo");
		ImageView torreb2 = new ImageView(torreb);
		torreb2.setId("torre");
		ImageView peonb1 = new ImageView(peonb);
		peonb1.setId("peonb");
		ImageView peonb2 = new ImageView(peonb);
		peonb2.setId("peonb");
		ImageView peonb3 = new ImageView(peonb);
		peonb3.setId("peonb");
		ImageView peonb4 = new ImageView(peonb);
		peonb4.setId("peonb");
		ImageView peonb5 = new ImageView(peonb);
		peonb5.setId("peonb");
		ImageView peonb6 = new ImageView(peonb);
		peonb6.setId("peonb");
		ImageView peonb7 = new ImageView(peonb);
		peonb7.setId("peonb");
		ImageView peonb8 = new ImageView(peonb);
		peonb8.setId("peonb");
		
		piezasb.addAll(Arrays.asList(torreb1, caballob1, alfilb1, reinab1, reyb1, alfilb2, caballob2, torreb2, peonb1, peonb2, peonb3, peonb4, peonb5, peonb6, peonb7, peonb8));
		
		for (int pos = 0;pos<piezasb.size();pos++) {
			ImageView pieza = piezasb.get(pos);
			pieza.setX(pos%8*PXBLOQUE);
			pieza.setY((BLOQUES-1-pos/8)*PXBLOQUE);
			pieza.setOnMouseClicked(e->{
				piezaSelec = pieza;
				comprobarPieza(pieza);
			});
		}
		
		tablero.getChildren().addAll(piezasb);
	}

	public static void resetearTablero() {
		tablero = new Pane();
		turno = 0;
		piezasn = new ArrayList<ImageView>();
		piezasb = new ArrayList<ImageView>();
		mreyn = false;
		mreyb = false;
	}
	
	public static void comerPieza(Rectangle movimiento) {
		for (int pos = 0; pos<tablero.getChildren().size();pos++) {
			if (tablero.getChildren().get(pos).getClass().isInstance(new ImageView())) {
				ImageView temp = (ImageView)tablero.getChildren().get(pos);
				if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE) {
					if (obtenerColor(temp)=='b') {
						piezasb.remove(temp);
					} else if (obtenerColor(temp)=='n') {
						piezasn.remove(temp);
					}
					tablero.getChildren().remove(pos);
					pos--;
				}
			}
		}
	}
	
	public static boolean comprobarComerPieza(ImageView pieza, Rectangle movimiento) {
		for (int pos = 0; pos<tablero.getChildren().size();pos++) {
			if (tablero.getChildren().get(pos).getClass().isInstance(new ImageView())) {
				ImageView temp = (ImageView)tablero.getChildren().get(pos);
				if (temp.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && temp.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
					if (obtenerColor(pieza)!=obtenerColor(temp))
						return true;
			}
		}
		
		return false;
	}
	
	public static void quitarMov() {
		for (int pos = 0;pos<tablero.getChildren().size();pos++) {
			if (tablero.getChildren().get(pos).getId()!=null)
				if (tablero.getChildren().get(pos).getId().equalsIgnoreCase("mov") || tablero.getChildren().get(pos).getId().equalsIgnoreCase("com")) {
					tablero.getChildren().remove(pos);
					pos--;
				}
		}
	}
	
	public static boolean comprobarObstruido(Rectangle movimiento) {
		for (int pos = 0;pos<tablero.getChildren().size();pos++) {
			if (tablero.getChildren().get(pos).getClass().isInstance(new ImageView())) {
				ImageView pieza = (ImageView)tablero.getChildren().get(pos);
				if (pieza.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && pieza.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE)
					return true;
			}
		}
		
		return false;
	}
	
	public static boolean comprobarEnroque(ImageView pieza, Rectangle movimiento) {
		if (obtenerColor(pieza)=='n' && !mreyn) {
			
		} else if (obtenerColor(pieza)=='b' && !mreyb) {
			
		}
		
		return false;
	}
	
	public static char obtenerColor(ImageView pieza) {
		String url = pieza.getImage().getUrl();
		return url.charAt(url.length()-5);
	}
	
	public static void comprobarPieza(ImageView pieza) {
		quitarMov();
		
		if ((turno%2==0 && !piezasb.contains(pieza)) || (turno%2==1 && !piezasn.contains(pieza))) {
			return;
		}
		movimientos = new ArrayList<Rectangle>();
		Rectangle mov; 
		
		switch (pieza.getId()) {
		case "torre":
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY(), PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY(), PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			break;
		case "caballo":
			for (int pos = 0;pos<BLOQUES;pos++) {
				if (pos<BLOQUES/2) {
					if (pos%2==0) {
						if (pos/2==0) {
							mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()+PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
						} else {
							mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()-PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
						}
					} else {
						if (pos/2==1) {
							mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()+PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
						} else {
							mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()-PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
						}
					}
				} else {
					if (pos%2==0) {
						if (pos/2==2) {
							mov = new Rectangle(pieza.getX()+PXBLOQUE*2, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
						} else {
							mov = new Rectangle(pieza.getX()-PXBLOQUE*2, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
						}
					} else {
						if (pos/2==3) {
							mov = new Rectangle(pieza.getX()-PXBLOQUE*2, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
						} else {
							mov = new Rectangle(pieza.getX()+PXBLOQUE*2, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
						}
					}
				}
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
				}
			}
			break;
		case "alfil":
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			break;
		case "rey":
			mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY(), PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY(), PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			break;
		case "reina":
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY(), PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY(), PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			break;
		case "peonn":
			mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov))
				movimientos.add(mov);
			if (pieza.getY()/PXBLOQUE==1 && !comprobarObstruido(mov)) {
				mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov))
					movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			break;
		case "peonb":
			mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov))
				movimientos.add(mov);
			if (pieza.getY()/PXBLOQUE==BLOQUES-2 && !comprobarObstruido(mov)) {
				mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov))
					movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			break;
		default:
			System.err.println("Pieza incorrecta");
		}
		movimientos.forEach(e->{
			if (e.getId()==null)
				e.setId("mov");
			e.setFill(Color.rgb(155, 200, 255, .6));
			e.setOnMouseClicked(m->{
				moverPieza(e);
			});
		});
		tablero.getChildren().addAll(movimientos);
	}
	
	public static void comprobarPieza(ImageView pieza, boolean comprobando) {
		quitarMov();
		
		movimientos = new ArrayList<Rectangle>();
		Rectangle mov; 
		
		switch (pieza.getId()) {
		case "torre":
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY(), PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY(), PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			break;
		case "caballo":
			for (int pos = 0;pos<BLOQUES;pos++) {
				if (pos<BLOQUES/2) {
					if (pos%2==0) {
						if (pos/2==0) {
							mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()+PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
						} else {
							mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()-PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
						}
					} else {
						if (pos/2==1) {
							mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()+PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
						} else {
							mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()-PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
						}
					}
				} else {
					if (pos%2==0) {
						if (pos/2==2) {
							mov = new Rectangle(pieza.getX()+PXBLOQUE*2, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
						} else {
							mov = new Rectangle(pieza.getX()-PXBLOQUE*2, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
						}
					} else {
						if (pos/2==3) {
							mov = new Rectangle(pieza.getX()-PXBLOQUE*2, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
						} else {
							mov = new Rectangle(pieza.getX()+PXBLOQUE*2, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
						}
					}
				}
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
				}
			}
			break;
		case "alfil":
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			break;
		case "rey":
			mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY(), PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY(), PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov)) {
				movimientos.add(mov);
			} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			break;
		case "reina":
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY(), PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY(), PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()+PXBLOQUE*pos, pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY()+PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			for (int pos = 1;pos<BLOQUES;pos++) {
				mov = new Rectangle(pieza.getX()-PXBLOQUE*pos, pieza.getY()-PXBLOQUE*pos, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov)) {
					movimientos.add(mov);
				} else if (comprobarObstruido(mov) && comprobarComerPieza(pieza, mov)) {
					mov.setId("com");
					movimientos.add(mov);
					break;
				} else {
					break;
				}
			}
			break;
		case "peonn":
			mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov))
				movimientos.add(mov);
			if (pieza.getY()/PXBLOQUE==1 && !comprobarObstruido(mov)) {
				mov = new Rectangle(pieza.getX(), pieza.getY()+PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov))
					movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()+PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			break;
		case "peonb":
			mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (!comprobarObstruido(mov))
				movimientos.add(mov);
			if (pieza.getY()/PXBLOQUE==BLOQUES-2 && !comprobarObstruido(mov)) {
				mov = new Rectangle(pieza.getX(), pieza.getY()-PXBLOQUE*2, PXBLOQUE, PXBLOQUE);
				if (!comprobarObstruido(mov))
					movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()-PXBLOQUE, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			mov = new Rectangle(pieza.getX()+PXBLOQUE, pieza.getY()-PXBLOQUE, PXBLOQUE, PXBLOQUE);
			if (comprobarComerPieza(pieza, mov)) {
				mov.setId("com");
				movimientos.add(mov);
			}
			break;
		default:
			System.err.println("Pieza incorrecta");
		}
		movimientos.forEach(e->{
			if (e.getId()==null)
				e.setId("mov");
			e.setFill(Color.rgb(155, 200, 255, .6));
			e.setOnMouseClicked(m->{
				moverPieza(e);
			});
		});
		tablero.getChildren().addAll(movimientos);
	}
	
	public static void moverPieza(Rectangle movimiento) {
		ImageView piezaTemp = new ImageView();
		piezaTemp.setX(piezaSelec.getX());
		piezaTemp.setY(piezaSelec.getY());
		piezaSelec.setX(movimiento.getX());
		piezaSelec.setY(movimiento.getY());
		if (!comprobarJaque(movimiento)) {
			piezaSelec.setX(piezaTemp.getX());
			piezaSelec.setY(piezaTemp.getY());
			if (movimiento.getId().equalsIgnoreCase("com"))
				comerPieza(movimiento);
			piezaSelec.setX(movimiento.getX());
			piezaSelec.setY(movimiento.getY());
			if (piezaSelec.getId().equalsIgnoreCase("rey")) {
				if (obtenerColor(piezaSelec)=='n') {
					mreyn = true;
				} else if (obtenerColor(piezaSelec)=='b') {
					mreyb = true;
				}
			}
			quitarMov();
			turno++;
			if ((piezaSelec.getId().equalsIgnoreCase("peonn") && piezaSelec.getY()/PXBLOQUE==BLOQUES-1) || (piezaSelec.getId().equalsIgnoreCase("peonb") && piezaSelec.getY()/PXBLOQUE==0))
				elegirPieza(movimiento, obtenerColor(piezaSelec));
			
		} else {
			piezaSelec.setX(piezaTemp.getX());
			piezaSelec.setY(piezaTemp.getY());
			comprobarPieza(piezaSelec);
			Text jaque = new Text("Jaque");
			Text continuar = new Text("(Haz click para continuar)");
			jaque.setFont(new Font("Arial", 30));
			jaque.setFill(Color.WHITESMOKE);
			continuar.setFont(new Font("Arial", 30));
			continuar.setFill(Color.WHITESMOKE);
			VBox notificacionJaque = new VBox(jaque, continuar);
			notificacionJaque.setAlignment(Pos.CENTER);
			notificacionJaque.setBackground(new Background(new BackgroundFill(Color.rgb(120, 120, 120, .7), null, null)));
			notificacionJaque.setPrefWidth(ANCHO);
			notificacionJaque.setPrefHeight(ALTO);
			tablero.getChildren().add(notificacionJaque);
			notificacionJaque.setOnMouseClicked(e->{
				tablero.getChildren().remove(notificacionJaque);
			});
		}
	}
	
	public static boolean comprobarJaque(Rectangle movPieza) {
		if (obtenerColor(piezaSelec)=='b') {
			for (int pos = 0;pos<piezasb.size();pos++) {
				if (piezasb.get(pos).getId().equalsIgnoreCase("rey")) {
					ImageView rey = piezasb.get(pos);
					for (int pieza = 0;pieza<piezasn.size();pieza++) {
						if (piezasn.get(pieza).getX()/PXBLOQUE==movPieza.getX()/PXBLOQUE && piezasn.get(pieza).getY()/PXBLOQUE==movPieza.getY()/PXBLOQUE) {
							continue;
						}
						comprobarPieza(piezasn.get(pieza), true);
						for (int mov = 0;mov<tablero.getChildren().size();mov++) {
							if (tablero.getChildren().get(mov).getId()!=null) {
								if (tablero.getChildren().get(mov).getId().equalsIgnoreCase("com")) {
									Rectangle movimiento = (Rectangle)tablero.getChildren().get(mov);
									if (rey.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && rey.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE) {
										quitarMov();
										return true;
									}
								}
							}
						}
					}
				}
			}
		} else if (obtenerColor(piezaSelec)=='n') {
			for (int pos = 0;pos<piezasn.size();pos++) {
				if (piezasn.get(pos).getId().equalsIgnoreCase("rey")) {
					ImageView rey = piezasn.get(pos);
					for (int pieza = 0;pieza<piezasb.size();pieza++) {
						if (piezasb.get(pieza).getX()/PXBLOQUE==movPieza.getX()/PXBLOQUE && piezasb.get(pieza).getY()/PXBLOQUE==movPieza.getY()/PXBLOQUE) {
							continue;
						}
						comprobarPieza(piezasb.get(pieza), true);
						for (int mov = 0;mov<tablero.getChildren().size();mov++) {
							if (tablero.getChildren().get(mov).getId()!=null) {
								if (tablero.getChildren().get(mov).getId().equalsIgnoreCase("com")) {
									Rectangle movimiento = (Rectangle)tablero.getChildren().get(mov);
									if (rey.getX()/PXBLOQUE==movimiento.getX()/PXBLOQUE && rey.getY()/PXBLOQUE==movimiento.getY()/PXBLOQUE) {
										quitarMov();
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public static void elegirPieza(Rectangle movimiento, char color) {
		HBox pane = new HBox();
		pane.setAlignment(Pos.CENTER);
		pane.setBackground(new Background(new BackgroundFill(Color.rgb(120, 120, 120), null, null)));
		Scene eleccionPieza = new Scene(pane, ANCHO, ALTO);
		ArrayList<ImageView> piezas = new ArrayList<>();
		ImageView torre = new ImageView();
		ImageView caballo = new ImageView();
		ImageView alfil = new ImageView();
		ImageView reina = new ImageView();
		if (color=='n') {
			torre = new ImageView(torren);
			torre.setId("torre");
			caballo = new ImageView(caballon);
			caballo.setId("caballo");
			alfil = new ImageView(alfiln);
			alfil.setId("alfil");
			reina = new ImageView(reinan);
			reina.setId("reina");
		} else if (color=='b') {
			torre = new ImageView(torreb);
			torre.setId("torre");
			caballo = new ImageView(caballob);
			caballo.setId("caballo");
			alfil = new ImageView(alfilb);
			alfil.setId("alfil");
			reina = new ImageView(reinab);
			reina.setId("reina");
		}
		piezas.addAll(Arrays.asList(torre, caballo, alfil, reina));
		piezas.forEach(e->{
			e.setOnMouseClicked(a->{
				stage.setScene(scene);
				cambiarPieza(e, color);
			});
		});
		pane.getChildren().addAll(piezas);
		stage.setScene(eleccionPieza);
	}
	
	public static void cambiarPieza(ImageView pieza, char color) {
		if (color=='n') {
			piezasn.get(piezasn.indexOf(piezaSelec)).setId(pieza.getId());
			piezasn.get(piezasn.indexOf(piezaSelec)).setImage(pieza.getImage());
		} else if (color=='b') {
			piezasb.get(piezasb.indexOf(piezaSelec)).setId(pieza.getId());
			piezasb.get(piezasb.indexOf(piezaSelec)).setImage(pieza.getImage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
