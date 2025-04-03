package com.berke.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class DateUtils {
	public static String getCurrentDate(Date date) {
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
		return simpleDateFormat.format(date);
	}
}
