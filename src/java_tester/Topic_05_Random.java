package java_tester;

import java.util.Random;

public class Topic_05_Random {

	public static void main(String[] args) {
		Random ran = new Random();
		System.out.println(ran.nextDouble());
		System.out.println(ran.nextFloat());
		System.out.println(ran.nextLong());
		System.out.println(ran.nextInt(9999));
		System.out.println("automationFC" + ran.nextInt(9999) + "@gmail.com");

	}

}
