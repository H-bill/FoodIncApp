import java.io.IOException;




public class TestClass {
	public static void main (String [] args) throws IOException{
		
		String rustic = "http://recipes.wikia.com/wiki/Special:Random";
		
		IngredientFetcher infe = new IngredientFetcher();

	/*	if (infe.isRecipe()){
			System.out.println(infe.theURL());
		
			infe.getIngredients();
		}
		if (infe.isRecipe()){
			System.out.println(infe.theURL());
		
			infe.getIngredients();
		}*/
		int f = 0;
		while(f<2){
		if (infe.isRecipe()){
			System.out.println(infe.theURL());
			
			infe.getIngredients();
			System.out.println(infe.drank());
			if(infe.drank().equals("drink")){

				f++;
				}
		}
		}

	}

}
