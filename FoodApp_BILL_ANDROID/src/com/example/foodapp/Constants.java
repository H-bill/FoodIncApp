package com.example.foodapp;

import java.util.Locale;

public class Constants {
	
	public static final String ACCESS_KEY_ID = "AKIAJ53SF726JAFT4EOA";
	public static final String SECRET_KEY = "a72OoOPhlDur0SGhSLMMK4P9l2ownI9K3FnX3oIp";
		
	public static String getPictureBucket(String bucket) {
		return ("rafaeldalibera" + ACCESS_KEY_ID + bucket).toLowerCase(Locale.US);
	}

}
