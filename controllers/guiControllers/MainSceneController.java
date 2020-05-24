package guiControllers;


import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;

import StrategyJson.FileUploader;
import StrategyJson.JsonUploader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import logger.Loger;
import userEntities.Purchase;
import userEntities.PurchaseController;

// TODO: Auto-generated Javadoc
/**
 * @author Nevo Goldman
 * The Class MainSceneController.
 */
//Using MVC 
public class MainSceneController implements Initializable{ //view

	/** The view TA. */
 @FXML
	private TextArea viewTA;

	/** The main TA. */
	@FXML
	private TextArea mainTA;

	/** The reg TA. */
	@FXML
	private TextArea regTA;

	/** The Get data frame. */
	@FXML
	private AnchorPane GetDataFrame;

	/** The first name TF. */
	@FXML
	private TextField firstNameTF;

	/** The last name TF. */
	@FXML
	private TextField lastNameTF;

	/** The id TF. */
	@FXML
	private TextField idTF;

	/** The date picker. */
	@FXML
	private DatePicker datePicker;

	/** The remarks TF. */
	@FXML
	private TextField remarksTF;

	/** The type combo box. */
	@FXML
	private ComboBox<String> typeComboBox;

	/** The save button. */
	@FXML
	private Button saveButton;

	/** The back button. */
	@FXML
	private Button backButton;

	/** The back button 1. */
	@FXML
	private Button backButton1;

	/** The main frame. */
	@FXML
	private AnchorPane mainFrame;

	/** The make insurance button. */
	@FXML
	private Button makeInsuranceButton;

	/** The view purchases button. */
	@FXML
	private Button viewPurchasesButton;

	/** The purchases frame. */
	@FXML
	private AnchorPane purchasesFrame;

	/** The data table. */
	@FXML
	private TableView<Purchase> dataTable;

	/** The first name culomn. */
	@FXML
	private TableColumn<Purchase, String> firstNameCulomn;

	/** The last name culomn. */
	@FXML
	private TableColumn<Purchase, String> lastNameCulomn;

	/** The date culomn. */
	@FXML
	private TableColumn<Purchase, String> dateCulomn;

	/** The remarks culomn. */
	@FXML
	private TableColumn<Purchase, String> remarksCulomn;

	/** The type culomn. */
	@FXML
	private TableColumn<Purchase, String> typeCulomn;
	
	/** The id culomn. */
	@FXML
	private TableColumn<Purchase, String> idCulomn;

	/** The purchase controller. */
	private PurchaseController purchaseController;

	/** The file uploader. */
	private FileUploader fileUploader;

	/**
	 * Handel clicks.
	 *
	 * @param event the event
	 */
	@FXML
	void handelClicks(ActionEvent event) {
		if(event.getSource() == backButton) {
			Loger.writeLineToTextFile("'back' button clicked" + System.lineSeparator());
			clearAllFields();
			mainFrame.toFront();

		} else if (event.getSource() == saveButton) {
			Loger.writeLineToTextFile("'save' button clicked" + System.lineSeparator());

			if(!validateInput()) {
				Notifications notificationsBuilder = Notifications.create()
						.title("Save")
						.text("All fields must be enterd")
						.graphic(null)
						.hideAfter(Duration.seconds((3)))
						.position(Pos.CENTER);
				notificationsBuilder.darkStyle();
				notificationsBuilder.showError();

			} else if(!validateNumberdInput()) {

				Notifications notificationsBuilder = Notifications.create()
						.title("Save")
						.text("Names can't contain numbers / ID can't contain letters")
						.graphic(null)
						.hideAfter(Duration.seconds((3)))
						.position(Pos.CENTER);
				notificationsBuilder.darkStyle();
				notificationsBuilder.showError();

			} else if(!validateDetailedInput()) {

				Notifications notificationsBuilder = Notifications.create()
						.title("Save")
						.text("ID already exiests OR date invalid")
						.graphic(null)
						.hideAfter(Duration.seconds((3)))
						.position(Pos.CENTER);
				notificationsBuilder.darkStyle();
				notificationsBuilder.showError();

			} else {

				Purchase purchase = new Purchase(firstNameTF.getText().toString(),
						lastNameTF.getText().toString(),
						remarksTF.getText().toString(),
						typeComboBox.getValue().toString(),
						datePicker.getValue().toString(),
						idTF.getText().toString());

				purchaseController.addPurchase(purchase);

				Loger.writeLineToTextFile("Name,. Last Name,. Date,. Remaks,. Type" + System.lineSeparator());
				Loger.writeLineToTextFile(purchase.toString() + System.lineSeparator());

				Image img = new Image("/icons/mark.png");
				Notifications notificationsBuilder = Notifications.create()
						.title("Save")
						.text("Purchase Saved!")
						.graphic(new ImageView(img))
						.hideAfter(Duration.seconds((3)))
						.position(Pos.CENTER);

				notificationsBuilder.darkStyle();
				notificationsBuilder.show();
			}

			clearAllFields();


		} else if(event.getSource() == makeInsuranceButton) {
			Loger.writeLineToTextFile("'Make Insurance' button clicked" + System.lineSeparator());
			GetDataFrame.toFront();

		} else if(event.getSource() == viewPurchasesButton) {
			Loger.writeLineToTextFile("'View Purchases' button clicked" + System.lineSeparator());
			purchasesFrame.toFront();
			setPurchasesToTable(purchaseController.getPurchases());

		} else if(event.getSource() == backButton1) {
			Loger.writeLineToTextFile("'back1' button clicked" + System.lineSeparator());
			mainFrame.toFront();
		}
	}

	/**
	 * Validate input.
	 *
	 * @return true, if successful
	 */
	private boolean validateInput() {

		if(firstNameTF.getText().toString().trim().length() == 0 ||
				lastNameTF.getText().trim().length() == 0 ||
				datePicker.getValue() == null ||
				remarksTF.getText().trim().length() == 0 ||
				typeComboBox.getValue() == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the purchases to table.
	 *
	 * @param purchases the new purchases to table
	 */
	public void setPurchasesToTable(ArrayList<Purchase> purchases) {
		ObservableList<Purchase> listOfPurchases;
		listOfPurchases = FXCollections.observableArrayList();
		for(Purchase p: purchases) {
			listOfPurchases.add(p);
		}

		dataTable.setItems(listOfPurchases);
	}

	/**
	 * Clear all fields.
	 */
	private void clearAllFields() {

		firstNameTF.clear();
		lastNameTF.clear();
		idTF.clear();
		datePicker.setValue(null);
		typeComboBox.setValue(null);
		remarksTF.clear();
	}

	/**
	 * Initialize.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mainTA.setEditable(false);
		viewTA.setEditable(false);
		regTA.setEditable(false);
		mainFrame.toFront();
		Loger.getLoger();
		dataTable.setEditable(false);
		purchaseController = PurchaseController.getPurchaseController();
		firstNameCulomn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameCulomn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		dateCulomn.setCellValueFactory(new PropertyValueFactory<>("date"));
		remarksCulomn.setCellValueFactory(new PropertyValueFactory<>("remarks"));
		typeCulomn.setCellValueFactory(new PropertyValueFactory<>("type"));
		idCulomn.setCellValueFactory(new PropertyValueFactory<>("id"));
		fileUploader = new JsonUploader();
		fileUploader.uploadFile();
		JsonUploader jsonUploader = (JsonUploader)fileUploader;
		mainTA.setText(jsonUploader.toString());
		viewTA.setText(jsonUploader.toString());
		regTA.setText(jsonUploader.toString());
		setTypeComboBox();
	}


	/**
	 * Validate numberd input.
	 *
	 * @return true, if successful
	 */
	private boolean validateNumberdInput() {
		if(firstNameTF.getText().toString().matches(".*\\d.*") ||
				lastNameTF.getText().matches(".*\\d.*") ||
				checking(idTF.getText().toString())){
			return false;
		}
		return true;
	}
	
	/**
	 * Checking.
	 *
	 * @param str the str
	 * @return true, if successful
	 */
	private boolean checking(String str) {
		 int len = str.length();
	      for (int i = 0; i < len; i++) {
	         if ((Character.isLetter(str.charAt(i)))) {
	            return true;
	         }
	      }
	      return false;
	}

	/**
	 * Validate detailed input.
	 *
	 * @return true, if successful
	 */
	private boolean validateDetailedInput() {

		String dateStampString = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null,d2 = null;
		try {
			d1 = simpleDateFormat.parse(datePicker.getValue().toString());
			d2 = simpleDateFormat.parse(dateStampString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(d1.compareTo(d2) < 0) {
			return false;
		}

		for(Purchase p: purchaseController.getPurchases()) {
			if(p.getId().equals(idTF.getText().toString())) {
				return false;
			}
		}		
		return true;
	}

	/**
	 * Sets the type combo box.
	 */
	public void setTypeComboBox() {
		ObservableList<String> listOfTypes;
		ArrayList<String> typeList = new ArrayList<String>();	
		typeList.add("Car");
		typeList.add("Health");
		typeList.add("Life");
		typeList.add("Apartment");

		listOfTypes = FXCollections.observableArrayList(typeList);

		typeComboBox.setItems(listOfTypes);
	}
}











