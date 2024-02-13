package pojo;

import java.util.List;

public class Orders {
	
	/*
	 
	 Orders is a array with 01 element having 02 sub attributes
	 
	 {
    	"orders": [
        	{
            	"country": "India",
            	"productOrderedId": "65bcd9e0a86f8f74dc6aec42"
        	}
    	]
	}
	 	 
	 	 */
	
	
	private List<OrderDetail> orders; // order is an array in response and can have more than 01 values

	public List<OrderDetail> getOrders() {
		return orders;
	}

	//Need to pass OrderDetail List
	public void setOrders(List<OrderDetail> orders) {
		this.orders = orders;
	}
	


}
