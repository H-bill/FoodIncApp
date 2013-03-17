import java.io.IOException;




public class TestClass {
	public static void main (String [] args) throws IOException{
		

		
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
		while(f<1){
		if (infe.isRecipe()){
		
			if(infe.drank().equals("drink")){
				System.out.println(infe.theURL());
				
				infe.getIngredients();
				System.out.println(infe.drank());
				infe.info();
				f++;
				}
		}
		}

	}

}
