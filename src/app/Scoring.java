package app;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.SubScene;
import javafx.scene.text.Text;

public class Scoring {
	private static final double TREE_VALUE = 0.01;
	private static final double TEMPLE_VALUE = 0.02;
	
	private static double coins;
	private static Map<String, Integer> elements;
	private static String[] codes;
	private static double income;
	
	
	static {
		coins = 0;
		income = 0;
		elements = new HashMap<String, Integer>();
		codes = new String[] {"Temples", "Trees"};
		elements.put("Temples", 0);
		elements.put("Trees", 0);
	}
	
	public static void increase(int code) {
		String element = codes[code];
		elements.put(element, elements.get(element) + 1);
	}
	
	public static int getNOTrees() {
		return elements.get("Trees");
	}
	
	public static int getNOTemples() {
		return elements.get("Temples");
	}

	public static double update() {
		coins = coins + 2*income;
		return coins;
	}
	
	public static void addCoin() {
		coins++;
	}
	
	public static double getCoins() {
		return coins;
	}
	
	public static double getIncome() {
		return income;
	}

	public static void update(SubScene hud) {
		double income = Scoring.updateIncome();
		double coins = Scoring.update();
		Text coinsTxt = (Text) hud.getRoot().lookup("#coins");
		coinsTxt.setText(String.format("%.2f", coins) + " + " + String.format("%.2f", income) + "/s");
	}
	
	public static double updateIncome() {
		 income = TREE_VALUE * getNOTrees() + TEMPLE_VALUE * getNOTemples();
		 return income;
	}
}
