/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u2.a1;

/**
 *
 * @author D
 */
public class Order {
    private int id;
	private String orderDate;

	
	public Order(){
		this.id = 0;
		this.orderDate = "";
	}
	
	public Order(int id, String orderDate){
		this.id = id;
		this.orderDate = orderDate;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	
	public String getOrderDate(){
		return orderDate;
	}
	
	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}
	
    
}
