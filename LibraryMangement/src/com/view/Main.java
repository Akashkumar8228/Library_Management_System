package com.view;
import java.util.Scanner;

import com.controller.Admin;
import com.controller.User;
public class Main {
	private static User userAccess = new User();
	private static Admin adminAccess = new Admin();
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		
		System.out.println("=====> Library Management System <=====");
		System.out.println();
		System.out.println("Press: 1=> Admin");
		System.out.println("Press: 2=> User");
		System.out.println("Press: 0=> Exit");
		System.out.print("Press Here: ");
		int press = sc.nextInt();
		
		switch(press){
		case 1: {
			System.out.println("Choose: 1=> Register");
			System.out.println("Choose: 2=> Login");
			System.out.println("Choose: 0=> Exit");
			System.out.print("Choose Here: ");
			int choose = sc.nextInt();
			switch(choose) {
			case 1:{
				
				adminAccess.adminRegister();
				break;
			}
			
			case 2:{
				adminAccess.adminLogin();
				break;
			}
			
			case 0:{
				adminAccess.exit();
				break;
			}
			
			default:{
				System.out.println("Invalid Option !");
				break;
			}
			}
			break;
		}
		
		case 2:{
			userAccess.buyBook();
			break;
			
		}
		
		case 0:{
			adminAccess.exit();
			break;
		}
		
		default:{
			System.out.println("Invalid Credentials !");
			break;
		}
		
		}
	}

}
