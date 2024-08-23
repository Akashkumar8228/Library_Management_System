package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Book {
	
	private static String url = "jdbc:mysql://localhost:3306/library_database";
	private static String username = "root";
	private static String pass = "root";
	private static Scanner sc = new Scanner(System.in);
	
	public void addBook() {
		System.out.print("Enter Book Id: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Book Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Book Price: ");
		double price = sc.nextDouble();
		sc.nextLine();
		System.out.print("Enter Author: ");
		String author = sc.nextLine();
		System.out.print("Enter Book Quantity: ");
		int quantity = sc.nextInt();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, pass);
			String query = "insert into book(book_id, book_name, book_price, book_author, book_quantity) values(?, ?, ?, ?, ?)";
			PreparedStatement state = conn.prepareStatement(query);
			state.setInt(1, id);
			state.setString(2, name);
			state.setDouble(3, price);
			state.setString(4, author);
			state.setInt(5, quantity);
			int result = state.executeUpdate();
			if(result > 0) {
				System.out.println("===> Book Added <===");
			}else {
				System.out.println("===> Something Wend Wrong <===");
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public void readBook() {
		
		System.out.print("Enter Book Id: ");
		int id = sc.nextInt();
		
		try {
			Connection conn = DriverManager.getConnection(url, username, pass);
			String query = "select * from book where book_id = ?";
			PreparedStatement state = conn.prepareStatement(query);
			state.setInt(1, id);
			
			ResultSet result = state.executeQuery();
			if(result.next()) {
				System.out.println("<==========>");
				System.out.println("Book Id: " + result.getInt("book_id"));
				System.out.println("Book Name: " + result.getString("book_name"));
				System.out.println("Book Price: " + result.getDouble("book_price"));
				System.out.println("Book Author: " + result.getString("book_author"));
				System.out.println("Book Quantity: " + result.getInt("book_quantity"));
			}else {
				System.out.println("Something Wend Wrong !");
			}
			
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void updateBook() {
		System.out.print("Enter Book Old-Id: ");
		int oldId = sc.nextInt();
		System.out.print("Enter Book New Id: ");
		int newId = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Book Name: ");
		String name = sc.nextLine();
		System.out.print("Enter Book Price: ");
		double price = sc.nextDouble();
		sc.nextLine();
		System.out.print("Enter Book Author: ");
		String author = sc.nextLine();
		System.out.print("Enter Book Quantity: ");
		int quantity = sc.nextInt();
		
		try {
			Connection conn = DriverManager.getConnection(url, username, pass);
			String query = "update book set book_id = ? , book_name = ?, book_price = ?, book_author = ?, book_quantity = ? where book_id = ?";
			PreparedStatement state = conn.prepareStatement(query);
			state.setInt(1, newId);
			state.setString(2, name);
			state.setDouble(3, price);
			state.setString(4, author);
			state.setInt(5, quantity);
			state.setInt(6, oldId);
			
			int result = state.executeUpdate();
			if(result > 0) {
				System.out.println("===> Book Details Updated <===");
			}else {
				System.out.println("===> Updation Failed <===");
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteBook() {
		System.out.print("Enter Book Id: ");
		int id = sc.nextInt();
		try {
			Connection conn = DriverManager.getConnection(url, username, pass);
			String query = "delete from book where book_id = ?";
			PreparedStatement state = conn.prepareStatement(query);
			state.setInt(1, id);
			
			int result = state.executeUpdate();
			if(result > 0) {
				System.out.println("===> Deletion Successful <===");
				
			}else {
				System.out.println("===> Something Went Wrong ! <===");
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void exit() {
		System.out.println("=====> Thank You <=====");
	}

}
