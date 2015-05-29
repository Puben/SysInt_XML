/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package purchase;

public class Item {
   private String productName;
   private String quantity;
   private String price;
   private String comment; 
   private String shipDate;
   
       public Item(String productName, String quantity, String price, String comment, String shipDate) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.comment = comment;
        this.shipDate = shipDate;
    }

    @Override
    public String toString() {
        return "Item{" + "productName=" + productName + ", quantity=" + quantity + ", price=" + price + ", comment=" + comment + ", shipDate=" + shipDate + '}';
    }

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
    
    
       
}
