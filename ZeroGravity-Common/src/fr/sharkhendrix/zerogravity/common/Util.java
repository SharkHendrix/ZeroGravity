package fr.sharkhendrix.zerogravity.common;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {

	private static NumberFormat numberFormat = new DecimalFormat("#0.00");

	public static String format(double d) {
		return numberFormat.format(d);
	}
}
