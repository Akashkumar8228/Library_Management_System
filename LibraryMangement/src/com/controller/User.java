package com.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class User {
	private static String url = "jdbc:mysql://localhost:3306/library_database";
	private static String username = "root";
	private static String pass = "root";
    private static	Scanner sc = new Scanner(System.in);
	public void buyBook() {
		System.out.print("Enter Book Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Book Id: ");
		int id = sc.nextInt();
		System.out.print("Enter Book Quantity: ");
		int quantity = sc.nextInt();
		double bookPrice;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, pass);
			
			String query = "select book_price from book where book_id = ?";
			PreparedStatement statement1 = conn.prepareStatement(query);
			statement1.setInt(1, id);
			ResultSet resultSet = statement1.executeQuery();
			if(resultSet.next()) {
				bookPrice = resultSet.getDouble("book_price");
				double totalPrice = quantity * bookPrice;
				
				String insertQuery = "insert into user(book_name, book_id, book_quantity, total_price) values(?, ?, ?, ?)";
				PreparedStatement state = conn.prepareStatement(insertQuery);
				state.setString(1, name);
				state.setInt(2, id);
				state.setInt(3, quantity);
				state.setDouble(4, totalPrice);
				int result = state.executeUpdate();
				if(result > 0 ) {
					int minusQuantity = quantity;
					
					String fetchQuery = "select book_quantity from book where book_id = ?";
					PreparedStatement statement = conn.prepareStatement(fetchQuery);
					statement.setInt(1, id);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						int oldQuantity = rs.getInt("book_quantity");
						int updatedQuantity = oldQuantity - minusQuantity;
						
						String updateQuery = "update book set book_quantity = ? where book_id = ?";
						PreparedStatement st = conn.prepareStatement(updateQuery);
						st.setInt(1, updatedQuantity);
						st.setInt(2, id);
						int change = st.executeUpdate();
						if(change > 0) {
							System.out.println("===> Book Purchased <===");
						}else {
							System.out.println("Something went wrong !");
						}
						
					}else {
						System.out.println("Something went wrong !");
					}
				}else {
					System.out.println("Something went wrong !");
				}
			}else {
				System.out.println("===> Book is not available <===");
			}
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
}
