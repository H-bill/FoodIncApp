import java.io.IOException;




public class TestClass {
	public static void main (String [] args) throws IOException{
		
		String rustic = "http://recipes.wikia.com/wiki/Special:Random";
		
		IngredientFetcher infe = new IngredientFetcher();

		if (infe.isRecipe()){
			System.out.println(infe.theURL());
		
			infe.getIngredients();
		}

	}

}
