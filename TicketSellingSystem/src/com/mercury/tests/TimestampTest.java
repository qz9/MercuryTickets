package com.mercury.tests;

import java.util.Date;
import java.sql.Timestamp;

public class TimestampTest {
	public static void main(String[] args) {
		System.out.println(new Timestamp(new Date().getTime()));
	}
}
