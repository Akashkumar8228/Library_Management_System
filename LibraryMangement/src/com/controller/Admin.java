package com.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.model.Book;
public class Admin {
	private static String url = "jdbc:mysql://localhost:3306/library_database";
	private static String username = "root";
	private static String pass = "root";
	private static Scanner sc = new Scanner(System.in);
	private static Book book = new Book();
	public void adminRegister() {
		System.out.print("Enter Name: ");
		String name = sc.nextLine();
		
		System.out.print("Enter Email: ");
		
		String email = sc.next();
		System.out.print("Enter Password: ");
		String password = sc.next();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, pass);
			
			String query = "insert into admin(name, email, password) values(?, ?, ?)";
			
			PreparedStatement state = conn.prepareStatement(query);
			state.setString(1, name);
			state.setString(2, email);
			state.setString(3, password);
			
			int result = state.executeUpdate();
			if(result > 0) {
				System.out.println("===> Admin Registration Successful <===");
			}else {
				System.out.println("Something Went Wrong !");
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void adminLogin() {
		
		System.out.print("Enter Email: ");
		String email = sc.next();
		System.out.print("Enter Password: ");
		String password = sc.next();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, pass);
			String query = "select * from admin where email = ? and password = ?";
			PreparedStatement state = conn.prepareStatement(query);
			state.setString(1, email);
			state.setString(2, password);
			
			ResultSet result = state.executeQuery();
			if(result.next()) {
				System.out.println("===> You are Logged In <===");
				System.out.println();
				
				System.out.println("Enter: 1=> Add Book");
				System.out.println("Enter: 2=> Read Book");
				System.out.println("Enter: 3=> Update Book");
				System.out.println("Enter: 4=> Delete Book");
				System.out.println("Enter: 0=> Exit");
				System.out.print("Enter Here: ");
				int enter = sc.nextInt();
				switch(enter) {
				case 1:{
					book.addBook();
					break;
				}
				
				case 2:{
					book.readBook();
					break;
				}
				
				case 3:{
					book.updateBook();
					break;
				}
				
				case 4:{
					book.deleteBook();
					break;
				}
				
				case 0:{
					book.exit();
					break;
				}
				}
			}else {
				System.out.println("Invalid Credentials !");
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
	
	}
	
	public void exit() {
		System.out.println("=====> Thank You <=====");
	}
}
