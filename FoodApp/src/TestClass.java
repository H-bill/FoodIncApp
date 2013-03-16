import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class TestClass {
	public static void main (String [] args) throws IOException{
		String rustic = "http://recipes.wikia.com/wiki/Curried_Pork_Medley";
		IngredientFetcher infe = new IngredientFetcher(rustic);
		infe.getIngredients();
	}

}
