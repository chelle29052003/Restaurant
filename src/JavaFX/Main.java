package JavaFX;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {

//	Initialization
	public Label lbPasswordLog, lbRoleLog, lbSignInLog, lbSignUpLog, lbEmailLog, lbTitleMenu, lbMenuSelect, lbQtyMenu,
			lbTitleMenuUser, lbDescUserTop, lbDescUserMid, lbDescUserBottom, lSignUp, lFullName, lPhone, lGender,
			lEmail, lPassword, lAddress, lbTitleMenuAdmin, lbDescAdminTop, lbDescAdminMid, lbDescAdminBottom, lVMO,
			descriptionVMO, ltotalPayment;
	public Button btSignInLog, btSignup, btAddCart, btOrderMenuUser, btBackToSignIn, btSignUpSubmit, btOrderVMO,
			btRemoveFromCart;
	public TextField tfEmailLog, tfSelect, tfFullName, tfEmail, tfPhone;
	public TextArea taAddress;
	public PasswordField pfPasswordLog, pfPassword;
	public ComboBox<String> cbRoleLog;
	public Spinner<Integer> spQtyMenu;
	public Menu meHome, meMenu, meOrder, meSettings, meHomeAdmin, meManage, meSettingsAdmin;
	public MenuItem miOrder, miMenuIdn, miSignOut, miManageMenu, miSignOutAdmin, miHome, miHomeAdmin;
	public MenuBar mbMenu, mbMenuAdmin;
	public BorderPane parentSignin, parentMenu, parentMenuUser, bpSignUp, parentMenuAdmin, bpVMO;
	public GridPane gpSignIn, gpMenu, gpMenuUser, gpSignUp, gpMenuAdmin, gpVMO;
	public Scene sceneSignin, sceneMenu, sceneMenuUser, sceneSignUp, sceneMenuAdmin, sceneVMO;
	public ToggleGroup tgGender;
	public RadioButton rbMan, rbWoman;
	public TableView<Menus> tvMenu = new TableView<>();
	public TableView<Carts> tvCart = new TableView<>();

//	Table Column
//	Menu
	TableColumn<Menus, String> idCol = new TableColumn("ID");
	TableColumn<Menus, String> nameCol = new TableColumn("Name");
	TableColumn<Menus, Integer> priceCol = new TableColumn("Price");
	TableColumn<Menus, Integer> stockCol = new TableColumn("Stock");

//	Cart
	TableColumn<Carts, String> menuIDVMO = new TableColumn("Menu ID");
	TableColumn<Carts, String> menuNameVMO = new TableColumn("Menu Name");
	TableColumn<Carts, Integer> priceVMO = new TableColumn("Price");
	TableColumn<Carts, Integer> qtyVMO = new TableColumn("Quantity");
	TableColumn<Carts, Integer> totalOrderVMO = new TableColumn("Total");

//	Menu
	TableColumn<Menus, String> menuIDCol = new TableColumn<>("Id");
	TableColumn<Menus, String> menuNameCol = new TableColumn<>("Name");
	TableColumn<Menus, Integer> menuPriceCol = new TableColumn<>("Price");
	TableColumn<Menus, Integer> menuStockCol = new TableColumn<>("Stock");

//	Database Connection
	private Connect connect = Connect.getInstance();

//	ArrayList
	ArrayList<Customers> customers = new ArrayList<>();
	ArrayList<Admins> admins = new ArrayList<>();
	ArrayList<Menus> menus = new ArrayList<>();
	ArrayList<OrderDetail> orderDetail = new ArrayList<>();
	ArrayList<OrderHeader> orderHeader = new ArrayList<>();
	ArrayList<Carts> carts = new ArrayList<>();

//	Random
	Random rad = new Random();

//	Additional
	String userId;
	String adminId = "AD001";
	String orderId = "";

	public void initViewOrder() {
		bpVMO = new BorderPane();
		sceneVMO = new Scene(bpVMO, 1280, 832);
		gpVMO = new GridPane();

		bpVMO.setStyle("-fx-background-color: #8CB0A4");

		// Label View My Order
		lVMO = new Label("View My Order");
		lVMO.setFont(Font.font("ARIAL", FontWeight.BOLD, 40));

		descriptionVMO = new Label(
				"Please choose the menu that you want to remove from your order list then click the Remove from Order Cart button below.");
		descriptionVMO.setFont(Font.font("ARIAL", FontWeight.NORMAL, 16));

		ltotalPayment = new Label("Total Payment : ");
		ltotalPayment.setFont(Font.font("ARIAL", FontWeight.BOLD, 32));

		btRemoveFromCart = new Button("Remove from Order Cart");
		btRemoveFromCart.setStyle("-fx-background-color: #FFC54D");
		btRemoveFromCart.setFont(Font.font("ARIAL", FontWeight.BOLD, 15));
		btRemoveFromCart.setMinSize(201, 37);

		btOrderVMO = new Button("Order");
		btOrderVMO.setStyle("-fx-background-color: #FFC54D");
		btOrderVMO.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));
		btOrderVMO.setMinSize(410, 60);

		HBox hbTotalPayment = new HBox();
		hbTotalPayment.getChildren().addAll(ltotalPayment);
		hbTotalPayment.setSpacing(30);
		hbTotalPayment.setPadding(new Insets(0, 0, 0, 0));

		VBox vbBtOrder = new VBox();
		vbBtOrder.getChildren().addAll(btOrderVMO);
		vbBtOrder.setPadding(new Insets(30, 0, 0, 0));
		vbBtOrder.setAlignment(Pos.CENTER_LEFT);

		VBox vbBawah = new VBox();
		vbBawah.getChildren().addAll(descriptionVMO, btRemoveFromCart);
		vbBawah.setPadding(new Insets(5, 0, 30, 0));
		vbBawah.setSpacing(30);

		gpVMO.add(vbBawah, 0, 0);
		gpVMO.add(hbTotalPayment, 0, 1);
		gpVMO.add(vbBtOrder, 0, 2);

		VBox vbAtas = new VBox();
		vbAtas.getChildren().addAll(lVMO, tvCart, gpVMO);
		vbAtas.setSpacing(30);
		vbAtas.setPadding(new Insets(25, 25, 50, 25));

		bpVMO.setCenter(vbAtas);

	}

	public void initSignin() {
//	Layout
		parentSignin = new BorderPane();
		sceneSignin = new Scene(parentSignin, 1280, 832);
		gpSignIn = new GridPane();

//	Label
		lbSignInLog = new Label("Sign In");
		HBox hbLbSignInLog = new HBox();

		lbEmailLog = new Label("Email");
		VBox vbUserNameLog = new VBox();

		lbPasswordLog = new Label("Password");
		VBox vbPasswordLog = new VBox();

		lbRoleLog = new Label("Role");
		VBox vbRoleLog = new VBox();

		lbSignUpLog = new Label("Don't have an account?");

//	TextField
		tfEmailLog = new TextField();

//	PasswordField
		pfPasswordLog = new PasswordField();

//	ComboBox
		cbRoleLog = new ComboBox<>();

//	Button
		btSignInLog = new Button("Sign In");
		HBox hbButtonSignInLog = new HBox();

		btSignup = new Button("Sign Up");
		VBox vbButtonSignUpLog = new VBox();

//		Label
		lbEmailLog.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lbPasswordLog.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lbRoleLog.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lbSignUpLog.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		lbSignInLog.setFont(Font.font("Arial", FontWeight.BLACK, 36));
		hbLbSignInLog.getChildren().addAll(lbSignInLog);
		hbLbSignInLog.setPadding(new Insets(80, 0, 57, 0));
		hbLbSignInLog.setAlignment(Pos.CENTER_LEFT);

//		TextField
		tfEmailLog.setMinSize(410, 46);
		tfEmailLog.setPromptText("Email");
		vbUserNameLog.getChildren().addAll(lbEmailLog, tfEmailLog);
		vbUserNameLog.setSpacing(5);

//		PasswordField
		pfPasswordLog.setMinSize(410, 46);
		pfPasswordLog.setPromptText("Password");
		vbPasswordLog.getChildren().addAll(lbPasswordLog, pfPasswordLog);
		vbPasswordLog.setSpacing(5);

//		ComboBox
		cbRoleLog.setMinSize(410, 46);
		cbRoleLog.getItems().addAll("Admin", "Customer");
		vbRoleLog.getChildren().addAll(lbRoleLog, cbRoleLog);
		vbRoleLog.setSpacing(5);

//		Button
		btSignInLog.setMinSize(293, 49);
		btSignInLog.setStyle("-fx-background-color: #FFC54D");
		btSignInLog.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		hbButtonSignInLog.getChildren().addAll(btSignInLog);
		hbButtonSignInLog.setPadding(new Insets(46, 0, 62, 0));
		hbButtonSignInLog.setAlignment(Pos.CENTER);

		btSignup.setMinSize(293, 49);
		btSignup.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		btSignup.setStyle("-fx-background-color: #000000");
		btSignup.setTextFill(javafx.scene.paint.Color.WHITE);
		vbButtonSignUpLog.getChildren().addAll(lbSignUpLog, btSignup);
		vbButtonSignUpLog.setSpacing(12);
		vbButtonSignUpLog.setAlignment(Pos.CENTER);
		vbButtonSignUpLog.setPadding(new Insets(0, 0, 46, 0));

//		Add Components
		gpSignIn.setMinSize(579, 832);
		gpSignIn.setPadding(new Insets(20, 82, 20, 82));
		gpSignIn.setVgap(20);
		gpSignIn.setStyle("-fx-background-color: #8CB0A4");

		gpSignIn.add(hbLbSignInLog, 0, 0);
		gpSignIn.add(vbUserNameLog, 0, 1);
		gpSignIn.add(vbPasswordLog, 0, 2);
		gpSignIn.add(vbRoleLog, 0, 3);
		gpSignIn.add(hbButtonSignInLog, 0, 4);
		gpSignIn.add(vbButtonSignUpLog, 0, 5);

		parentSignin.setRight(gpSignIn);
		parentSignin.setStyle(
				"-fx-background-image: url('https://images.unsplash.com/photo-1552611052-33e04de081de?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fHJhbWVufGVufDB8fDB8fA%3D%3D&w=1000&q=80')");

	}

	public void initSignUp() {
		bpSignUp = new BorderPane();
		sceneSignUp = new Scene(bpSignUp, 1280, 832);
		gpSignUp = new GridPane();

		gpSignUp.setStyle("-fx-background-color: #8CB0A4");

		lSignUp = new Label("Sign Up");
		lSignUp.setFont(Font.font("ARIAL", FontWeight.BOLD, 36));

		lFullName = new Label("Full Name");
		lFullName.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));

		lPhone = new Label("Phone Number");
		lPhone.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));

		lGender = new Label("Gender");
		lGender.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));

		lEmail = new Label("Email");
		lEmail.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));

		lPassword = new Label("Password");
		lPassword.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));

		lAddress = new Label("Address");
		lAddress.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));

		tfFullName = new TextField();
		tfFullName.setMinSize(408, 46);
		tfFullName.setPromptText("Full Name");

		tfEmail = new TextField();
		tfEmail.setMinSize(408, 46);
		tfEmail.setPromptText("Email");

		pfPassword = new PasswordField();
		pfPassword.setMinSize(408, 46);
		pfPassword.setPromptText("Password");

		taAddress = new TextArea();
		taAddress.setMinSize(408, 46);
		taAddress.setPromptText("Address");

		tfPhone = new TextField();
		tfPhone.setMinSize(408, 46);
		tfPhone.setPromptText("Phone Number");

		tgGender = new ToggleGroup();

		rbMan = new RadioButton("Man");
		rbMan.setToggleGroup(tgGender);
		rbMan.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));

		rbWoman = new RadioButton("Woman");
		rbWoman.setToggleGroup(tgGender);
		rbWoman.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));

		HBox hbGender = new HBox();

		btBackToSignIn = new Button("Back to Sign In");
		btBackToSignIn.setStyle("-fx-background-color: #000000");
		btBackToSignIn.setFont(Font.font("ARIAL", FontWeight.BOLD, 15));
		btBackToSignIn.setMinSize(121, 26);
		btBackToSignIn.setTextFill(javafx.scene.paint.Color.WHITE);

		btSignUpSubmit = new Button("Sign Up");
		btSignUpSubmit.setStyle("-fx-background-color: #FFC54D");
		btSignUpSubmit.setFont(Font.font("ARIAL", FontWeight.BOLD, 20));
		btSignUpSubmit.setMinSize(293, 49);

		VBox vbFullName = new VBox();
		vbFullName.getChildren().addAll(lFullName, tfFullName);
		vbFullName.setSpacing(5);

		VBox vbDob = new VBox();
		vbDob.getChildren().addAll(lPhone, tfPhone);
		vbDob.setSpacing(5);

		VBox vbGender = new VBox();
		vbGender.getChildren().addAll(lGender, hbGender);
		vbGender.setSpacing(5);

		VBox vbUsername = new VBox();
		vbUsername.getChildren().addAll(lEmail, tfEmail);
		vbUsername.setSpacing(5);

		VBox vbPassword = new VBox();
		vbPassword.getChildren().addAll(lPassword, pfPassword);
		vbPassword.setSpacing(5);

		VBox vbCPassword = new VBox();
		vbCPassword.getChildren().addAll(lAddress, taAddress);
		vbCPassword.setSpacing(5);

		HBox hbLSignUp = new HBox();
		hbLSignUp.getChildren().addAll(lSignUp, btBackToSignIn);
		hbLSignUp.setSpacing(155);

		VBox vbBtSignUp = new VBox();
		vbBtSignUp.getChildren().addAll(btSignUpSubmit);
		vbBtSignUp.setAlignment(Pos.CENTER);
		vbBtSignUp.setPadding(new Insets(25, 0, 0, 0));

		hbGender.getChildren().addAll(rbMan, rbWoman);
		hbGender.setSpacing(26);
		gpSignUp.add(hbLSignUp, 0, 0);
		gpSignUp.add(vbFullName, 0, 1);
		gpSignUp.add(vbGender, 0, 2);
		gpSignUp.add(vbDob, 0, 3);
		gpSignUp.add(vbUsername, 0, 4);
		gpSignUp.add(vbPassword, 0, 5);
		gpSignUp.add(vbCPassword, 0, 6);
		gpSignUp.add(vbBtSignUp, 0, 7);

		gpSignUp.setMinSize(579, 832);
		gpSignUp.setPadding(new Insets(48, 84, 48, 84));
		gpSignUp.setHgap(29);
		gpSignUp.setVgap(20);

		bpSignUp.setRight(gpSignUp);
		bpSignUp.setAlignment(gpSignUp, Pos.CENTER);
		bpSignUp.setStyle(
				"-fx-background-image: url('https://i.pinimg.com/originals/b5/a0/c4/b5a0c4957f7213ecdaa7f0e0890d4466.jpg')");

	}

	public void initMenuUser() {

		menuUser();

//	Layout
		parentMenuUser = new BorderPane();
		sceneMenuUser = new Scene(parentMenuUser, 1280, 832);
		gpMenuUser = new GridPane();

//	Label
		lbTitleMenuUser = new Label("REStaurant");
		lbTitleMenuUser.setFont(Font.font("Arial", FontWeight.BLACK, 36));
		HBox hbTitleButtonMenuUser = new HBox();

		lbDescUserTop = new Label(
				"REStaurant has been serving delicious food since 2020. We provide a range of high-quality");
		lbDescUserTop.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		lbDescUserMid = new Label(
				"menu options, including Indonesian appetizer, main course, dessert, and beverages. We are willing to");
		lbDescUserMid.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		lbDescUserBottom = new Label("prepare dishes of the finest quality for you.");
		lbDescUserBottom.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		VBox vbDescMenuUser = new VBox();
		vbDescMenuUser.getChildren().addAll(lbDescUserTop, lbDescUserMid, lbDescUserBottom);
		HBox hbDescMenuUser = new HBox();
		hbDescMenuUser.getChildren().addAll(vbDescMenuUser);

//		Button
		btOrderMenuUser = new Button("Order Now!");
		btOrderMenuUser.setMinSize(227, 43);
		btOrderMenuUser.setStyle("-fx-background-color: #FFC54D");
		btOrderMenuUser.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		hbTitleButtonMenuUser.getChildren().addAll(lbTitleMenuUser, btOrderMenuUser);
		hbTitleButtonMenuUser.setSpacing(40);
		hbTitleButtonMenuUser.setPadding(new Insets(0, 0, 25, 0));

		VBox vbContentMenuUser = new VBox();
		vbContentMenuUser.getChildren().addAll(hbTitleButtonMenuUser, hbDescMenuUser);

//	Add Components
		gpMenuUser.setPadding(new Insets(25, 35, 48, 35));
		gpMenuUser.setStyle("-fx-background-color: #8CB0A4");

		gpMenuUser.add(vbContentMenuUser, 0, 1);

		parentMenuUser.setTop(mbMenu);
		parentMenuUser.setBottom(gpMenuUser);

		parentMenuUser.setStyle("-fx-background-image: url('https://wallpaperaccess.com/full/1312834.jpg')");
	}

	public void initMenuAdmin() {

		menuAdmin();

//	Layout
		parentMenuAdmin = new BorderPane();
		sceneMenuAdmin = new Scene(parentMenuAdmin, 1280, 832);
		gpMenuAdmin = new GridPane();

//	Label
		lbTitleMenuAdmin = new Label("REStaurant");
		lbTitleMenuAdmin.setFont(Font.font("Arial", FontWeight.BLACK, 36));
		HBox hbTitleMenuAdmin = new HBox();
		hbTitleMenuAdmin.getChildren().addAll(lbTitleMenuAdmin);
		hbTitleMenuAdmin.setSpacing(40);
		hbTitleMenuAdmin.setPadding(new Insets(0, 0, 25, 0));

		lbDescAdminTop = new Label("Hello!");
		lbDescAdminTop.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		lbDescAdminMid = new Label(
				"Thank you for your hard work and services in helping the restaurant to keep running until now. Let’s keep growing together to");
		lbDescAdminMid.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		lbDescAdminBottom = new Label("be better!");
		lbDescAdminBottom.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

		VBox vbDescMenuAdmin = new VBox();
		vbDescMenuAdmin.getChildren().addAll(lbDescAdminTop, lbDescAdminMid, lbDescAdminBottom);
		HBox hbDescMenuAdmin = new HBox();
		hbDescMenuAdmin.getChildren().addAll(vbDescMenuAdmin);

		VBox vbContentMenuAdmin = new VBox();
		vbContentMenuAdmin.getChildren().addAll(hbTitleMenuAdmin, hbDescMenuAdmin);

//	Add Components
		gpMenuAdmin.setPadding(new Insets(25, 35, 48, 35));
		gpMenuAdmin.setStyle("-fx-background-color: #8CB0A4");

		gpMenuAdmin.add(vbContentMenuAdmin, 0, 1);

		parentMenuAdmin.setBottom(gpMenuAdmin);
		parentMenuAdmin.setTop(mbMenuAdmin);

		parentMenuAdmin.setStyle("-fx-background-image: url('https://wallpaperaccess.com/full/462778.jpg')");

	}

	public void initMenu() {

		menuUser();

//		Layout
		parentMenu = new BorderPane();
		sceneMenu = new Scene(parentMenu, 1280, 832);
		gpMenu = new GridPane();

//		Label
		lbTitleMenu = new Label();
		lbTitleMenu.setFont(Font.font("Arial", FontWeight.BLACK, 36));

		HBox hbTitleMenu = new HBox();
		hbTitleMenu.getChildren().addAll(lbTitleMenu);
		hbTitleMenu.setPadding(new Insets(42, 0, 0, 0));

		lbMenuSelect = new Label("Id of Selected Menu");
		lbMenuSelect.setFont(Font.font("Arial", FontWeight.BOLD, 15));

		lbQtyMenu = new Label("Quantity of Selected Menu");
		lbQtyMenu.setFont(Font.font("Arial", FontWeight.BOLD, 15));

//		TextField
		tfSelect = new TextField();
		tfSelect.setMinSize(410, 46);
		tfSelect.setPromptText("Your Selected Menu Will Appears here...");
		VBox vbSelectMenu = new VBox();
		vbSelectMenu.getChildren().addAll(lbMenuSelect, tfSelect);
		vbSelectMenu.setSpacing(5);

//		Spinner
		spQtyMenu = new Spinner<>(0, 100, 1);
		spQtyMenu.setEditable(true);
		spQtyMenu.setMinSize(247, 46);
		VBox vbQtyMenu = new VBox();
		vbQtyMenu.getChildren().addAll(lbQtyMenu, spQtyMenu);
		vbQtyMenu.setSpacing(5);

		HBox hbMenu = new HBox();
		hbMenu.getChildren().addAll(vbSelectMenu, vbQtyMenu);
		hbMenu.setSpacing(10);

//		Button
		btAddCart = new Button("Add to Order Cart");
		btAddCart.setStyle("-fx-background-color: #FFC54D");
		btAddCart.setMinSize(410, 60);
		btAddCart.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		HBox hbButtonMenu = new HBox();
		hbButtonMenu.getChildren().addAll(btAddCart);
		hbButtonMenu.setPadding(new Insets(50, 0, 0, 0));

//		TableView
		tvMenu = new TableView<>();
		tvMenu.setStyle("-fx-background-color: #FFC54D");

//		Add Components
		gpMenu.setMinSize(782, 792);
		gpMenu.setPadding(new Insets(0, 42, 57, 42));
		gpMenu.setVgap(20);
		gpMenu.setStyle("-fx-background-color: #8CB0A4");

		gpMenu.add(hbTitleMenu, 0, 0);
		gpMenu.add(tvMenu, 0, 1);
		gpMenu.add(hbMenu, 0, 2);
		gpMenu.add(hbButtonMenu, 0, 3);

		parentMenu.setRight(gpMenu);
		parentMenuUser.setTop(mbMenu);

	}

//	Manage Menu
	Stage window;
	BorderPane bpManageMenu;
	GridPane gpManageMenu;
	Scene manageMenu;
	Label manageMenuLabel, foodId, foodName, foodPrice, foodStock;
	TextField foodIdTF, foodNameTF;
	Button updateBtn, deleteBtn;
	Spinner<Integer> spPrice;
	Spinner<Integer> spStock;
	HBox hboxFoodId, hboxFoodName, hboxFoodPrice, hboxBawah, hboxFoodStock;
	VBox vboxKiri, vboxKanan, vboxAtas, vboxGabungan;
	TableView<Menus> tvManageMenu;

	public void initialize() {
		bpManageMenu = new BorderPane();
		gpManageMenu = new GridPane();
		bpManageMenu.setStyle("-fx-background-color: #8CB0A4");
		manageMenu = new Scene(bpManageMenu, 1280, 832);

		manageMenuLabel = new Label("Manage Menu");
		manageMenuLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));

		tvManageMenu = new TableView<>();

		menuIDCol.setMinWidth(200);
		menuNameCol.setMinWidth(500);
		menuPriceCol.setMinWidth(250);
		menuStockCol.setMinWidth(100);

//		Label
		foodId = new Label("Food ID");
		foodId.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		foodName = new Label("Food Name");
		foodName.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		foodPrice = new Label("Food Price");
		foodPrice.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		foodStock = new Label("Food Stock");
		foodStock.setFont(Font.font("Arial", FontWeight.BOLD, 20));

//		TextField
		foodIdTF = new TextField();
		foodIdTF.setMinSize(410, 46);
		foodIdTF.setEditable(false);

		foodNameTF = new TextField();
		foodNameTF.setMinSize(410, 46);

//		Button
		updateBtn = new Button("Update");
		updateBtn.setStyle("-fx-background-color: #FFC54D");
		updateBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		updateBtn.setMinSize(227, 43);

		deleteBtn = new Button("Delete");
		deleteBtn.setStyle("-fx-background-color: #FFC54D");
		deleteBtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		deleteBtn.setMinSize(227, 43);

//		Spinner
		spPrice = new Spinner<>(0, 1000000, 1);
		spPrice.setEditable(true);
		spPrice.setMinSize(410, 46);

		spStock = new Spinner<>(0, 1000000, 1);
		spStock.setEditable(true);
		spStock.setMinSize(410, 46);
	}

	private Insets Insets(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPosition() {

		hboxFoodId = new HBox();
		hboxFoodId.getChildren().addAll(foodId, foodIdTF);
		hboxFoodId.setSpacing(124);

		hboxFoodName = new HBox();
		hboxFoodName.getChildren().addAll(foodName, foodNameTF);
		hboxFoodName.setSpacing(87);

		hboxFoodPrice = new HBox();
		hboxFoodPrice.getChildren().addAll(foodPrice, spPrice);
		hboxFoodPrice.setSpacing(93);

		hboxFoodStock = new HBox();
		hboxFoodStock.getChildren().addAll(foodStock, spStock);
		hboxFoodStock.setSpacing(87);

		vboxKiri = new VBox();
		vboxKiri.getChildren().addAll(hboxFoodId, hboxFoodName, hboxFoodPrice, hboxFoodStock);
		vboxKiri.setSpacing(21);

		vboxKanan = new VBox();
		vboxKanan.getChildren().addAll(updateBtn, deleteBtn);
		vboxKanan.setSpacing(27);

		hboxBawah = new HBox();
		hboxBawah.getChildren().addAll(vboxKiri, vboxKanan);
		hboxBawah.setSpacing(104);

		vboxAtas = new VBox();
		vboxAtas.getChildren().addAll(manageMenuLabel);

		vboxGabungan = new VBox();
		vboxGabungan.getChildren().addAll(vboxAtas, tvManageMenu, hboxBawah);
		vboxGabungan.setSpacing(20);

//		Masukin ke GP
		gpManageMenu.add(vboxGabungan, 0, 0);
		gpManageMenu.setPadding(new Insets(0, 100, 50, 50));

//		Masukin ke BP
		bpManageMenu.setTop(mbMenuAdmin);
		bpManageMenu.setBottom(gpManageMenu);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage PrimaryStage) throws Exception {
		// TODO Auto-generated method stub

//		Method
		menuUser();
		menuAdmin();

		initSignin();
		initSignUp();
		initMenuUser();
		initMenuAdmin();
		initMenu();
		initViewOrder();
		initialize();
		setPosition();

//		Table
		tvMenu.getColumns().addAll(idCol, nameCol, priceCol, stockCol);
		tvMenu.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tvCart.getColumns().addAll(menuIDVMO, menuNameVMO, priceVMO, qtyVMO, totalOrderVMO);
		tvCart.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tvManageMenu.getColumns().addAll(menuIDCol, menuNameCol, menuPriceCol, menuStockCol);
		tvManageMenu.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

//		Program Run
		PrimaryStage.setTitle("Sign In");
		PrimaryStage.show();
		PrimaryStage.setScene(sceneSignin);

//		Sign Up Page
		btSignUpSubmit.setOnAction(e -> {

			if (tfFullName.getText().isEmpty() || tfEmail.getText().isEmpty() || pfPassword.getText().isEmpty()
					|| tfPhone.getText().isEmpty() || taAddress.getText().isEmpty()) {
				Alert alertR = new Alert(AlertType.ERROR, "Please fill the empty field");
				alertR.showAndWait();
			} else if (tfFullName.getText().length() <= 3 || tfFullName.getText().length() >= 30) {
				Alert alertR = new Alert(AlertType.ERROR, "Full name must be 3-30 characters");
				alertR.showAndWait();
			} else if (tgGender.getSelectedToggle() == null) {
				Alert alGenderRegValid = new Alert(AlertType.ERROR);
				alGenderRegValid.setContentText("Gender must be choosed!");
				alGenderRegValid.showAndWait();
			} else if ((!(tfPhone.getText().length() == 12)) || (!tfPhone.getText().startsWith("0"))) {
				Alert alPhoneRegValid = new Alert(AlertType.ERROR);
				alPhoneRegValid.setContentText("Phone Number must contain 12 characters and starts with '0'!");
				alPhoneRegValid.showAndWait();
			} else {

				getDataCus();

				boolean emailValid = false;

				for (int i = 0; i < customers.size(); i++) {
					if (tfEmail.getText().equals(customers.get(i).getEmail())) {

						emailValid = true;

						Alert alEmailRegValid = new Alert(AlertType.ERROR);
						alEmailRegValid.setContentText(
								"Email must end with '@gmail.com', contains 1 '@' and not in front, and unique!");
						alEmailRegValid.showAndWait();
					}
				}

				if (!emailValid) {

					if (!tfEmail.getText().endsWith("@gmail.com") || tfEmail.getText().startsWith("@")
							|| !tfEmail.getText().matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
						Alert alEmailRegValid = new Alert(AlertType.ERROR);
						alEmailRegValid.setContentText(
								"Email must end with '@gmail.com', contains 1 '@' and not in front, and unique!");
						alEmailRegValid.showAndWait();
					} else if (pfPassword.getText().length() <= 5) {
						Alert alertR = new Alert(AlertType.ERROR, "Password must be at least 5 characters");
						alertR.showAndWait();
					} else if (taAddress.getText().length() <= 5 || taAddress.getText().length() >= 30) {
						Alert alertR = new Alert(AlertType.ERROR, "Address must be 5-30 characters");
						alertR.showAndWait();

					} else {

						Alert alertR = new Alert(AlertType.INFORMATION, "Sign up success!");
						alertR.showAndWait();

						String id = "CU" + "" + (rad.nextInt(10)) + "" + (rad.nextInt(10)) + "" + (rad.nextInt(10));
						String name = tfFullName.getText();
						String gender = ((RadioButton) tgGender.getSelectedToggle()).getText();
						String phone = tfPhone.getText();
						String address = taAddress.getText();
						String email = tfEmail.getText();
						String password = pfPassword.getText();

						customers.add(new Customers(id, name, gender, phone, email, password, address));

						insertCus(id, name, gender, phone, address, email, password);

						PrimaryStage.setTitle("Sign In");
						PrimaryStage.setScene(sceneSignin);

						clean();

					}
				}
			}

		});

		btBackToSignIn.setOnAction(e ->

		{
			PrimaryStage.setScene(sceneSignin);

			clean();
		});

//		Sign In Page
		btSignInLog.setOnAction(e -> {
			if (tfEmailLog.getText().isEmpty()) {
				Alert alEmailEmpty = new Alert(AlertType.ERROR);
				alEmailEmpty.setContentText("Email must be filled!");
				alEmailEmpty.showAndWait();
			} else if (pfPasswordLog.getText().isEmpty()) {
				Alert alPasswordEmpty = new Alert(AlertType.ERROR);
				alPasswordEmpty.setContentText("Password must be filled!");
				alPasswordEmpty.showAndWait();
			} else if (cbRoleLog.getSelectionModel().isEmpty()) {
				Alert alRoleEmpty = new Alert(AlertType.ERROR);
				alRoleEmpty.setContentText("Role must be chosen!");
				alRoleEmpty.showAndWait();
			} else {

				Boolean emailExistCus = false;
				Boolean emailExist = false;
				Boolean wrongPassword = false;
				Boolean wrongPasswordAdm = false;

				getDataCus();
				getDataAdm();

				if (cbRoleLog.getValue().equals("Customer")) {

					for (int i = 0; i < customers.size(); i++) {
						if (customers.get(i).getEmail().equals(tfEmailLog.getText())) {
							emailExistCus = true;
							if (customers.get(i).getPassword().equals(pfPasswordLog.getText())) {
								userId = customers.get(i).getId();
								PrimaryStage.setTitle("Home");
								PrimaryStage.setScene(sceneMenuUser);

								parentMenuUser.setTop(mbMenu);
								clean();

								break;
							} else {

								wrongPassword = true;
								break;
							}
						}
					}

					if (!emailExistCus) {
						Alert alEmailLog = new Alert(AlertType.WARNING);
						alEmailLog.setContentText("Email doesn't exist!");
						alEmailLog.showAndWait();
					}

					if (wrongPassword) {
						Alert alPasswordLogMatch = new Alert(AlertType.WARNING);
						alPasswordLogMatch.setContentText("Password doesn't match!");
						alPasswordLogMatch.showAndWait();
					}

				} else if (cbRoleLog.getValue().equals("Admin")) {
					for (int i = 0; i < admins.size(); i++) {
						if (admins.get(i).getEmail().equals(tfEmailLog.getText())) {
							emailExist = true;
							if (admins.get(i).getPassword().equals(pfPasswordLog.getText())) {
								adminId = admins.get(i).getId();

								PrimaryStage.setTitle("Home");
								PrimaryStage.setScene(sceneMenuAdmin);

								parentMenuAdmin.setTop(mbMenuAdmin);
								clean();

								break;
							} else {

								wrongPasswordAdm = true;
								break;
							}
						}
					}

					if (!emailExist) {
						Alert alEmailLog = new Alert(AlertType.WARNING);
						alEmailLog.setContentText("Email doesn't exist!");
						alEmailLog.showAndWait();
					}

					if (wrongPasswordAdm) {
						Alert alPasswordLogMatch = new Alert(AlertType.WARNING);
						alPasswordLogMatch.setContentText("Password doesn't match!");
						alPasswordLogMatch.showAndWait();
					}

				}
			}

		});

		btSignup.setOnAction(e ->

		{
			PrimaryStage.setTitle("Sign Up");
			PrimaryStage.setScene(sceneSignUp);

			clean();
		});

//		Menu User
		miHome.setOnAction(e -> {
			PrimaryStage.setTitle("Home");
			PrimaryStage.setScene(sceneMenuUser);

			parentMenuUser.setTop(mbMenu);

			clean();

		});

		btOrderMenuUser.setOnAction(e -> {
			PrimaryStage.setTitle("Indonesian Menu");
			PrimaryStage.setScene(sceneMenu);

			parentMenu.setTop(mbMenu);
			lbTitleMenu.setText("Indonesian Food");

			parentMenu.setStyle(
					"-fx-background-image: url('https://c1.wallpaperflare.com/preview/873/760/3/indonesian-food-food-indonesian-meal-traditional-vegetables.jpg')");

			getDataMenuIdn();

			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
			stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

			refreshTableMenuIdn();

			tvMenu.setOnMouseClicked(tableMouseEvent());

			clean();
		});

		miMenuIdn.setOnAction(e -> {
			PrimaryStage.setTitle("Indonesian Menu");
			PrimaryStage.setScene(sceneMenu);

			parentMenu.setTop(mbMenu);
			lbTitleMenu.setText("Indonesian Food");

			parentMenu.setStyle(
					"-fx-background-image: url('https://c1.wallpaperflare.com/preview/873/760/3/indonesian-food-food-indonesian-meal-traditional-vegetables.jpg')");

			getDataMenuIdn();

			idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
			stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

			refreshTableMenuIdn();

			tvMenu.setOnMouseClicked(tableMouseEvent());

			clean();

		});

		btAddCart.setOnAction(e ->

		{

			getDataMenuIdn();
			getDataCart();

			if (tfSelect.getText().isEmpty()) {
				Alert alMenuIdEmpty = new Alert(AlertType.ERROR);
				alMenuIdEmpty.setContentText("Menu Id must be filled!");
				alMenuIdEmpty.showAndWait();
			} else if (spQtyMenu.getValue().toString().isEmpty()) {
				Alert alQtyMenu = new Alert(AlertType.ERROR);
				alQtyMenu.setContentText("Quantity of menu must be filled!");
				alQtyMenu.showAndWait();
			} else if (spQtyMenu.getValue() < 1) {
				Alert alQtyMenuZero = new Alert(AlertType.ERROR);
				alQtyMenuZero.setContentText("Quantity of menu must be filled!");
				alQtyMenuZero.showAndWait();
			} else {

				Boolean menuExist = false;

				int index = 0;
				Boolean validateExist = false;

				for (int i = 0; i < menus.size(); i++) {

					if (menus.get(i).getId().equals(tfSelect.getText())) {

						menuExist = true;
						index = i;

						if (menus.get(index).getStock() < spQtyMenu.getValue()) {
							Alert alQtyOrderMatch = new Alert(AlertType.ERROR);
							alQtyOrderMatch.setContentText("Quantity of menu must be less than available stock!");
							alQtyOrderMatch.showAndWait();

						}

						for (int j = 0; j < carts.size(); j++) {
							if ((menus.get(index).getId().equals(carts.get(j).getId())
									&& menus.get(index).getStock() >= spQtyMenu.getValue())) {
								validateExist = true;

								int qtyUpdate = carts.get(j).getQty() + spQtyMenu.getValue();

								String queryUpdateOrder = String.format(
										"UPDATE cart CA JOIN customer CU ON CU.customerID = CA.customerID JOIN menu ME ON ME.menuID = CA.menuID SET CA.quantity = '%d' WHERE CA.menuID = '%s' AND CA.customerID = '%s'",
										qtyUpdate, carts.get(j).getId(), userId);

								connect.execUpdate(queryUpdateOrder);

								refreshTableCart();

								Alert alAddQtySuccess = new Alert(AlertType.INFORMATION);
								alAddQtySuccess.setContentText("Menu quantity successfully added to order!");
								alAddQtySuccess.showAndWait();

								clean();

								break;

							}
						}

					}

				}

				if (!menuExist) {
					Alert alMenuId = new Alert(AlertType.ERROR);
					alMenuId.setContentText("Menu Id doesn't exist!");
					alMenuId.showAndWait();

				} else if (!validateExist && menus.get(index).getStock() >= spQtyMenu.getValue()) {

					String customerID = userId;
					String menuID = tfSelect.getText();
					int quantity = spQtyMenu.getValue();
					insertCart(customerID, menuID, quantity);

					refreshTableCart();

					Alert alProductAddCart = new Alert(AlertType.INFORMATION);
					alProductAddCart.setContentText("Product successfully added to order!");
					alProductAddCart.showAndWait();

					clean();
				}

			}

		});

		btRemoveFromCart.setOnAction(e -> {
			Boolean menuOrderExist = false;

			getDataCart();

			int indexQtyOrder = 0;

			for (int i = 0; i < carts.size(); i++) {

				if (carts.get(i).getId().equals(tvCart.getSelectionModel().getSelectedItem().getId())) {
					menuOrderExist = true;
					indexQtyOrder = i;

					Alert alRemoveOrder = new Alert(AlertType.INFORMATION);
					alRemoveOrder.setContentText("Remove from order succeed!");
					alRemoveOrder.showAndWait();

					String queryDeleteCart = String.format(
							"DELETE CA FROM cart CA JOIN customer CU ON CU.customerID = CA.customerID JOIN menu ME ON ME.menuID = CA.menuID WHERE CA.menuID = '%s' AND CA.customerID = '%s'",
							carts.get(i).getId(), userId);
					connect.execUpdate(queryDeleteCart);

					refreshTableCart();

					clean();

					break;

				}

			}

			if (!menuOrderExist) {
				Alert alMenuId = new Alert(AlertType.ERROR);
				alMenuId.setContentText("Menu Id doesn't exist!");
				alMenuId.showAndWait();
			}

			totalPrice();

			clean();

		});

		btOrderVMO.setOnAction(e -> {
			getDataMenuIdn();
			getDataOrderDetail();
			getDataOrderHeader();

			Boolean qtyOrder = true;

			for (int i = 0; i < menus.size(); i++) {
				for (int j = 0; j < carts.size(); j++) {

					if (carts.get(j).getId().equals(menus.get(i).getId())) {
						if (carts.get(j).getQty() > menus.get(i).getStock()) {
							qtyOrder = false;

						}
					}

				}

			}

			if (!qtyOrder) {
				Alert alQtyOrderMatch = new Alert(AlertType.ERROR);
				alQtyOrderMatch.setContentText("Quantity of product must be less than quantity at order!");
				alQtyOrderMatch.showAndWait();
			}

			if (carts.isEmpty()) {
				Alert alQtyOrderEmpty = new Alert(AlertType.ERROR);
				alQtyOrderEmpty.setContentText("Carts is empty!");
				alQtyOrderEmpty.showAndWait();
			}

			if (qtyOrder && !(carts.isEmpty())) {

				Alert alCheckOut = new Alert(AlertType.INFORMATION);
				alCheckOut.setContentText("Check out succeed!");
				alCheckOut.showAndWait();

				for (int i = 0; i < orderHeader.size(); i++) {
				}

				refreshTableMenuIdn();
				orderId = "OR" + "" + (rad.nextInt(10)) + "" + (rad.nextInt(10)) + ""
						+ (rad.nextInt(10));
				String orderID = orderId;
				String adminID = adminId;
				String customerID = userId;
				LocalDate date = LocalDate.now();
				String orderDate = date.toString();
				insertOrderHeader(orderID, customerID, adminID, orderDate);

				for (int a = 0; a < menus.size(); a++) {
					for (int b = 0; b < carts.size(); b++) {

						if (carts.get(b).getId().equals(menus.get(a).getId())) {

							if (carts.get(b).getQty() <= menus.get(a).getStock()) {

								int qtyCheckOut = menus.get(a).getStock() - carts.get(b).getQty();

								String queryUpdateQtyMenuCheck = String.format(
										"UPDATE menu SET menuStock = %d WHERE menuID = '%s'", qtyCheckOut,
										menus.get(a).getId());

								connect.execUpdate(queryUpdateQtyMenuCheck);

								refreshTableMenuIdn();

								String queryDeleteCart = String.format(
										"DELETE CA FROM cart CA JOIN customer CU ON CU.customerID = CA.customerID JOIN menu ME ON ME.menuID = CA.menuID WHERE CA.menuID = '%s' AND CA.customerID = '%s'",
										carts.get(b).getId(), userId);
								connect.execUpdate(queryDeleteCart);



								String menuID = carts.get(b).getId();
								int quantity = carts.get(b).getQty();
								insertOrderDetail(orderID, menuID, quantity);

							}
						}
					}
				}
			}

			refreshTableCart();

			totalPrice();

			clean();
		});

		miOrder.setOnAction(e ->

		{
			PrimaryStage.setTitle("View Order");
			PrimaryStage.setScene(sceneVMO);

			bpVMO.setTop(mbMenu);

			getDataCart();

			totalPrice();

			menuIDVMO.setCellValueFactory(new PropertyValueFactory<>("id"));
			menuNameVMO.setCellValueFactory(new PropertyValueFactory<>("name"));
			priceVMO.setCellValueFactory(new PropertyValueFactory<>("price"));
			qtyVMO.setCellValueFactory(new PropertyValueFactory<>("qty"));
			totalOrderVMO.setCellValueFactory(new PropertyValueFactory<>("total"));

			refreshTableCart();

			clean();
		});

		miSignOut.setOnAction(e -> {
			PrimaryStage.setTitle("Sign In");
			PrimaryStage.setScene(sceneSignin);

			clean();

		});

//		Menu Admin
		miHomeAdmin.setOnAction(e -> {
			PrimaryStage.setTitle("Home");
			PrimaryStage.setScene(sceneMenuAdmin);

			parentMenuAdmin.setTop(mbMenuAdmin);
			clean();

		});

		miManageMenu.setOnAction(e -> {
			PrimaryStage.setTitle("Manage Menu");
			PrimaryStage.setScene(manageMenu);

			getDataMenuIdn();

			menuIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
			menuNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
			menuPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
			menuStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

			refreshTableMenu();

			tvManageMenu.setOnMouseClicked(tableManageMouseEvent());

			bpManageMenu.setTop(mbMenuAdmin);
			clean();
		});

		updateBtn.setOnAction(e -> {

			if (foodIdTF.getText().isEmpty()) {
				Alert alMenuIdEmpty = new Alert(AlertType.ERROR);
				alMenuIdEmpty.setContentText("Menu Id must be filled!");
				alMenuIdEmpty.showAndWait();
			} else if (spPrice.getValue().toString().isEmpty()) {
				Alert alPriceMenuEmpty = new Alert(AlertType.ERROR);
				alPriceMenuEmpty.setContentText("Price must be filled!");
				alPriceMenuEmpty.showAndWait();
			} else if (spStock.getValue().toString().isEmpty()) {
				Alert alStockMenuEmpty = new Alert(AlertType.ERROR);
				alStockMenuEmpty.setContentText("Stock must be filled!");
				alStockMenuEmpty.showAndWait();
			} else if (spPrice.getValue() < 10000 || spPrice.getValue() > 100000) {
				Alert alPriceMenuMatch = new Alert(AlertType.ERROR);
				alPriceMenuMatch.setContentText("Price must be between 10000 - 100000!");
				alPriceMenuMatch.showAndWait();
			} else if (spStock.getValue() < 30 || spStock.getValue() > 1000) {
				Alert alStockMenuMatch = new Alert(AlertType.ERROR);
				alStockMenuMatch.setContentText("Stock must between 30 - 1000!");
				alStockMenuMatch.showAndWait();
			} else {

				Boolean menuIdExist = false;

				getDataMenuIdn();

				for (int i = 0; i < menus.size(); i++) {
					if (menus.get(i).getId().equals(foodIdTF.getText())) {
						menuIdExist = true;

						String queryUpdateMenu = String.format(
								"UPDATE `menu` SET `menuName`='%s',`menuPrice`= %d,`menuStock`= %d WHERE menuID = '%s'",
								foodNameTF.getText(), spPrice.getValue(), spStock.getValue(), menus.get(i).getId());

						connect.execUpdate(queryUpdateMenu);

						Alert alUpdateMenu = new Alert(AlertType.INFORMATION);
						alUpdateMenu.setContentText("Menu has been updated!");
						alUpdateMenu.showAndWait();

						refreshTableMenu();
						refreshTableMenuIdn();

						clean();
					}

				}

				if (!menuIdExist) {
					Alert alMenuId = new Alert(AlertType.WARNING);
					alMenuId.setContentText("Menu Id doesn't exist!");
					alMenuId.showAndWait();
				}

				refreshTableMenu();
				refreshTableMenuIdn();

			}
		});

		deleteBtn.setOnAction(e -> {
			if (foodIdTF.getText().isEmpty()) {
				Alert alMenuIdEmpty = new Alert(AlertType.ERROR);
				alMenuIdEmpty.setContentText("Menu Id must be filled!");
				alMenuIdEmpty.showAndWait();
			} else {
				Boolean menuIdExist = false;

				getDataMenuIdn();

				for (int i = 0; i < menus.size(); i++) {

					if (menus.get(i).getId().equals(foodIdTF.getText())) {
						menuIdExist = true;

						String queryDeleteMenu = String.format("DELETE FROM menu WHERE menuID = '%s'",
								menus.get(i).getId());

						connect.execUpdate(queryDeleteMenu);

						Alert alDeleteProduct = new Alert(AlertType.INFORMATION);
						alDeleteProduct.setContentText("Menu has been deleted!");
						alDeleteProduct.showAndWait();

						refreshTableMenu();
						refreshTableMenuIdn();

					}

				}

				if (!menuIdExist) {
					Alert alMenuId = new Alert(AlertType.WARNING);
					alMenuId.setContentText("Menu Id doesn't exist!");
					alMenuId.showAndWait();
				}

				clean();

			}

		});

		miSignOutAdmin.setOnAction(e -> {
			PrimaryStage.setTitle("Sign In");
			PrimaryStage.setScene(sceneSignin);

			parentMenuAdmin.setTop(mbMenuAdmin);
			clean();

		});

	}

//	Extra
//	1. Menu
	public void menuUser() {
//		Menu
		meHome = new Menu("Home");
		meMenu = new Menu("Menu");
		meOrder = new Menu("Order");
		meSettings = new Menu("Settings");

//		MenuItem
		miHome = new MenuItem("Home");
		miMenuIdn = new MenuItem("Indonesian");
		miSignOut = new MenuItem("Sign Out");
		miOrder = new MenuItem("Order");

		meHome.getItems().addAll(miHome);
		meMenu.getItems().addAll(miMenuIdn);
		meOrder.getItems().addAll(miOrder);
		meSettings.getItems().addAll(miSignOut);

//		MenuBar
		mbMenu = new MenuBar(meHome, meMenu, meOrder, meSettings);
		mbMenu.setStyle("-fx-background-colour:#FFFFFF");

	}

	public void menuAdmin() {
//		Menu
		meHomeAdmin = new Menu("Home");
		meManage = new Menu("Manage");
		meSettingsAdmin = new Menu("Settings");

//		MenuItem
		miHomeAdmin = new Menu("Home");
		miManageMenu = new MenuItem("Manage Menu");
		miSignOutAdmin = new MenuItem("Sign Out");

		meHomeAdmin.getItems().addAll(miHomeAdmin);
		meManage.getItems().addAll(miManageMenu);
		meSettingsAdmin.getItems().addAll(miSignOutAdmin);

//		MenuBar
		mbMenuAdmin = new MenuBar(meHomeAdmin, meManage, meSettingsAdmin);
		mbMenuAdmin.setStyle("-fx-background-colour:#FFFFFF");

	}

//	
//	2. Clean Page
	public void clean() {
		tfFullName.clear();
		tfSelect.clear();
		tfEmail.clear();
		tfEmailLog.clear();
		taAddress.clear();
		pfPassword.clear();
		pfPasswordLog.clear();
		cbRoleLog.valueProperty().set(null);
		tfPhone.clear();
		rbMan.setSelected(false);
		rbWoman.setSelected(false);
		spQtyMenu.getValueFactory().setValue(1);
		foodIdTF.clear();
		foodNameTF.clear();
		spPrice.getValueFactory().setValue(1);
		spStock.getValueFactory().setValue(1);
	}

//
//	3. Get Data
	public void getDataCus() {

		customers.clear();

		String querySelectCus = String.format("SELECT * FROM customer");
		connect.rs = connect.execQuery(querySelectCus);

		try {
			while (connect.rs.next()) {
				String id = connect.rs.getString("customerID");
				String name = connect.rs.getString("customerName");
				String gender = connect.rs.getString("customerGender");
				String phone = connect.rs.getString("customerPhone");
				String address = connect.rs.getString("customerAddress");
				String email = connect.rs.getString("customerEmail");
				String password = connect.rs.getString("customerPassword");

				customers.add(new Customers(id, name, gender, phone, email, password, address));
			}
		} catch (Exception e) {

		}

	}

	public void getDataAdm() {

		admins.clear();

		String querySelectAdm = String.format("SELECT * FROM admin");
		connect.rs = connect.execQuery(querySelectAdm);

		try {
			while (connect.rs.next()) {
				String id = connect.rs.getString("adminID");
				String name = connect.rs.getString("adminName");
				String gender = connect.rs.getString("adminGender");
				String phone = connect.rs.getString("adminPhone");
				String email = connect.rs.getString("adminEmail");
				String password = connect.rs.getString("adminPassword");
				Integer salary = connect.rs.getInt("adminSalary");

				admins.add(new Admins(id, name, gender, phone, email, password, salary));
			}
		} catch (Exception e) {

		}

	}

	public void getDataMenuIdn() {

		menus.clear();

		String querySelectMenuIdn = String.format("SELECT * FROM menu");
		connect.rs = connect.execQuery(querySelectMenuIdn);

		try {
			while (connect.rs.next()) {
				String id = connect.rs.getString("menuID");
				String name = connect.rs.getString("menuName");
				Integer price = connect.rs.getInt("menuPrice");
				Integer stock = connect.rs.getInt("menuStock");

				menus.add(new Menus(id, name, price, stock));
			}
		} catch (Exception e) {

		}

	}

	public void getDataCart() {
		carts.clear();

		String querySelectCart = String.format(
				"SELECT * FROM cart CA JOIN menu ME ON CA.menuID = ME.menuID JOIN customer CU ON CU.customerID = CA.customerID WHERE CU.customerID = '%s'",
				userId);
		connect.rs = connect.execQuery(querySelectCart);

		try {
			while (connect.rs.next()) {
				String menuID = connect.rs.getString("CA.menuID");
				String menuName = connect.rs.getString("ME.menuName");
				int menuPrice = connect.rs.getInt("ME.menuPrice");
				int menuQty = connect.rs.getInt("CA.quantity");
				int menuTotal = menuPrice * menuQty;

				carts.add(new Carts(menuID, menuName, menuPrice, menuQty, menuTotal));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void getDataOrderHeader() {
		orderHeader.clear();

		String querySelectOrderHeader = String.format(
				"SELECT * FROM orderheader OH JOIN orderdetail OD ON OH.orderID = OD.orderID JOIN menu ME ON OD.menuID = ME.menuID WHERE OH.customerID = '%s'",
				userId);
		connect.rs = connect.execQuery(querySelectOrderHeader);

		try {
			while (connect.rs.next()) {
				String orderID = connect.rs.getString("OD.orderID");
				String customerID = connect.rs.getString("OH.customerID");
				String adminID = connect.rs.getString("OH.adminID");
				String orderDate = connect.rs.getString("OH.orderDate");

				orderHeader.add(new OrderHeader(orderID, customerID, adminID, orderDate));
			}
		} catch (Exception e) {

		}
	}

	public void getDataOrderDetail() {

		orderDetail.clear();

		String querySelectOrderDetail = String.format(
				"SELECT * FROM orderheader OH JOIN orderdetail OD ON OH.orderID = OD.orderID JOIN menu ME ON OD.menuID = ME.menuID WHERE OH.customerID = '%s'",
				userId);
		connect.rs = connect.execQuery(querySelectOrderDetail);

		try {
			while (connect.rs.next()) {
				String orderID = connect.rs.getString("OD.orderID");
				String menuID = connect.rs.getString("OD.menuID");
				Integer qty = connect.rs.getInt("OD.quantity");

				orderDetail.add(new OrderDetail(orderID, menuID, qty));
			}
		} catch (Exception e) {

		}
	}

//	
//	4. Refresh Table
	public void refreshTableMenuIdn() {

		getDataMenuIdn();
		ObservableList<Menus> menusObs = FXCollections.observableArrayList(menus);
		tvMenu.setItems(menusObs);
	}

	public void refreshTableCart() {

		getDataCart();
		ObservableList<Carts> cartsObs = FXCollections.observableArrayList(carts);
		tvCart.setItems(cartsObs);
	}

	public void refreshTableMenu() {

		getDataMenuIdn();
		ObservableList<Menus> menusObs = FXCollections.observableArrayList(menus);
		tvManageMenu.setItems(menusObs);
	}

//	
//	5. Insert Data
	public void insertCus(String id, String name, String gender, String phone, String address, String email,
			String password) {

		String queryInsertCus = String.format("INSERT INTO customer VALUES ('%s','%s','%s','%s','%s','%s','%s')", id,
				name, gender, phone, address, email, password);
		connect.execUpdate(queryInsertCus);

	}

	public void insertOrderHeader(String orderID, String customerID, String adminID, String orderDate) {

		String queryInsertOrderHeader = String.format("INSERT INTO orderheader VALUES ('%s','%s','%s','%s')", orderID,
				customerID, adminID, orderDate);

		try {
			connect.execUpdate(queryInsertOrderHeader);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void insertOrderDetail(String orderID, String menuID, int quantity) {

		String queryInsertOrderDetail = String.format("INSERT INTO orderdetail VALUES ('%s','%s','%d')", orderID,
				menuID, quantity);
		connect.execUpdate(queryInsertOrderDetail);

	}

	public void insertCart(String customerID, String menuID, int quantity) {

		String queryinsertCart = String.format("INSERT INTO cart VALUES ('%s','%s','%d')", customerID, menuID,
				quantity);
		connect.execUpdate(queryinsertCart);

	}

//	
//	6. Event Handler
	public void handle(ActionEvent e) {

	}

	private EventHandler<MouseEvent> tableMouseEvent() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				TableSelectionModel<Menus> tableSelectionModel = tvMenu.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				Menus menus = tableSelectionModel.getSelectedItem();

				tfSelect.setText(menus.getId());

			}
		};
	}

	private EventHandler<MouseEvent> tableManageMouseEvent() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				TableSelectionModel<Menus> tableSelectionModel = tvManageMenu.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				Menus menus = tableSelectionModel.getSelectedItem();

				foodIdTF.setText(menus.getId());
				foodNameTF.setText(menus.getName());
				spPrice.getValueFactory().setValue(menus.getPrice());
				spStock.getValueFactory().setValue(menus.getStock());

			}
		};

	}

//	
//	7. Total Price
	public void totalPrice() {
		int grandTotal = 0;

		refreshTableCart();

		for (int i = 0; i < tvCart.getItems().size(); i++) {

			int totalPrice = tvCart.getItems().get(i).getTotal();
			grandTotal += totalPrice;

		}

		tvCart.refresh();

		ltotalPayment.setText("Total Price: " + grandTotal);
	}

}
