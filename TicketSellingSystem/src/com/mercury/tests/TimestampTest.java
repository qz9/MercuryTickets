package com.mercury.tests;

import java.util.Date;
import java.sql.Timestamp;

public class TimestampTest {
	public static void main(String[] args) {
		System.out.println(new Timestamp(new Date().getTime()));
		System.out.println(0%3);
		System.out.println(1%3);
		System.out.println(2%3);
		System.out.println(3%3);
	}
}
