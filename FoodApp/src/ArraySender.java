import java.util.ArrayList;

/**
 * This class provides methods to easily manually add
 * a set of IDs, add in a range for the IDs or to
 * add a database to be put in an array.
 * 
 * @author 	virginiacook
 * @version	2012-8-2, 1.0
 * @since	1.0
 * 
 */
public class ArraySender {
	//public String ArrayList to store sites in
	public ArrayList<String> siteList=new ArrayList<String>();
	//constructor
	public ArraySender(){
	}
	/**
	 * This method returns an ArrayList using 
	 * other methods in the ArraySender class.
	 * Edit this class to change values, you 
	 * can also access other methods to add to
	 * the database.
	 * 
	 * @return	an ArrayList of strings
	 */
	public ArrayList<String> idListCreator(){
		/*use methods to add to testing array
		returns the array with added methods*/
		return siteList;
	}
	/**
	 * This method returns an ArrayList of URL
	 * strings concatenated with the demo list
	 * page URL parameters.
	 * 
	 * @return	an ArrayList of URLs in String form
	 */
	public ArrayList<String> getDemoListPageURLs(String path1,String path2){
		//calls the idListCreator to generate IDs
		idListCreator();
		//creates a new ArrayList to store URL strings
		ArrayList<String> urls =new ArrayList<String>();
		//new String to store the Strings that will be retrieved from the siteList ArrayList
		String IDTemp="";
		//adds concatenated strings into new array using data from old array
		for(int x=0;x<siteList.size();x++){
			IDTemp=siteList.get(x);
			urls.add("http://clickslide.co/"+path1+"/"+path2+"/"+IDTemp+".xml");
		}
		return urls;
	}
	/**
	 * This method allows one to manually
	 * add objects into the siteList ArrayList.
	 * 
	 * @param	id	a string with the desired siteID to add to the list
	 */
	public void manualAdd(String id){
		//adds objects using ArrayList add method
		siteList.add(id);
	}
	/**
	 * This method allows one to enter a range
	 * of numbers and adds all numbers within
	 * that range to the array.
	 * @param	start	the starting value of the range
	 * @param	end		the ending value of the range
	 */
	public void rangeAdd(int start,int end){
		//uses a while loop to add in every value between start and end (inclusive)
		while(start<=end){
			siteList.add(""+start);
			start++;
		}
	}
	/**
	 * This method allows one to add items from the temp ArrayList to
	 * the ArrayList that will be tested.
	 * 
	 * @param dataBase	the database that will be added to the ArrayList
	 */
	public void dataBaseAdd(ArrayList<String> dataBase){
		//uses a for loop to add in every element of another ArrayList 
		for(int a=0;a<dataBase.size();a++){
			siteList.add(dataBase.get(a));
		}
	}
}
