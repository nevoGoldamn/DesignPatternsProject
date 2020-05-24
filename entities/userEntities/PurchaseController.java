package userEntities;

import java.util.ArrayList;

/**
 * part of MVC (the controller)
 * The Class PurchaseController.
 */
public class PurchaseController {//controller
	
	
	/** The Constant INSTANCE_CONTROLLER. */
private static final PurchaseController PURCHASE_CONTROLLER = new PurchaseController();
	
	/** The Purchases. */
	private ArrayList<Purchase> Purchases;
	
	/**
	 * Instantiates a new purchase controller.
	 * Singelton purchase controller.
	 */
	private PurchaseController() {
		Purchases = new ArrayList<Purchase>();
	}
	
	/**
	 * Gets the purchase controller.
	 *
	 * @return the purchase controller
	 */
	public static PurchaseController getPurchaseController() {
		return PURCHASE_CONTROLLER;
	}
	
	/**
	 * Gets the purchases.
	 *
	 * @return the purchases
	 */
	public ArrayList<Purchase> getPurchases() {
        return Purchases;
    }
	
	/**
	 * Adds a purchase.
	 *
	 * @param purchase the purchase
	 */
	public void addPurchase(Purchase purchase) {
		Purchases.add(purchase);
	}
}
