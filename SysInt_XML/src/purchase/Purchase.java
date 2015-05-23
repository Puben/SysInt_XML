/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package purchase;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
     private String orderDate;
     private String shipToCountry;
     private String shipName;
     private String shipStreet;
     private String shipCity;
     private String shipState;
     private String shipZip;
     private String billToCountry;
     private String billName;
     private String billStreet;
     private String billCity;
     private String billState;
     private String billZip;
     private List<Item> items;

    public Purchase(String orderDate, String shipToCountry, String shipName, String shipStreet, String shipCity, String shipState, String shipZip, String billToCountry, String billName, String billStreet, String billCity, String billState, String billZip, List<Item> items) {
        this.orderDate = orderDate;
        this.shipToCountry = shipToCountry;
        this.shipName = shipName;
        this.shipStreet = shipStreet;
        this.shipCity = shipCity;
        this.shipState = shipState;
        this.shipZip = shipZip;
        this.billToCountry = billToCountry;
        this.billName = billName;
        this.billStreet = billStreet;
        this.billCity = billCity;
        this.billState = billState;
        this.billZip = billZip;
        this.items = items;
    }
    
    public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipToCountry() {
		return shipToCountry;
	}

	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipStreet() {
		return shipStreet;
	}

	public void setShipStreet(String shipStreet) {
		this.shipStreet = shipStreet;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipState() {
		return shipState;
	}

	public void setShipState(String shipState) {
		this.shipState = shipState;
	}

	public String getShipZip() {
		return shipZip;
	}

	public void setShipZip(String shipZip) {
		this.shipZip = shipZip;
	}

	public String getBillToCountry() {
		return billToCountry;
	}

	public void setBillToCountry(String billToCountry) {
		this.billToCountry = billToCountry;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillStreet() {
		return billStreet;
	}

	public void setBillStreet(String billStreet) {
		this.billStreet = billStreet;
	}

	public String getBillCity() {
		return billCity;
	}

	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}

	public String getBillState() {
		return billState;
	}

	public void setBillState(String billState) {
		this.billState = billState;
	}

	public String getBillZip() {
		return billZip;
	}

	public void setBillZip(String billZip) {
		this.billZip = billZip;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
    public String toString() {
        return "Purchase{" + "orderDate=" + orderDate + ", shipToCountry=" + shipToCountry + ", shipName=" + shipName + ", shipStreet=" + shipStreet + ", shipCity=" + shipCity + ", shipState=" + shipState + ", shipZip=" + shipZip + ", billToCountry=" + billToCountry + ", billName=" + billName + ", billStreet=" + billStreet + ", billCity=" + billCity + ", billState=" + billState + ", billZip=" + billZip + ", items=" + items + '}';
    }

     
     
}