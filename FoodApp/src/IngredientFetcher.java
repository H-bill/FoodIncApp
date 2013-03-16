import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/*
 * AngelTest for http://clickslide.co/angelhack6 test site
 */
public class IngredientFetcher {
	
	/*
	 * Refactored by AGF: Move the URLs into variables so they can change dynamically
	 */
	public String posturl;
	public String geturl; 
	public String loginurl;
	public String puturl;
	
	/*
	 * Refactored by AGF
	 * 
	 * Moved HttpClient into a protected variable for use through out the class. 
	 * There is no need to create a new instance every time you want to use it. 
	 * Create it once and use it throughout the class. Saves time and overhead.
	 */
	public HttpClient httpclient;
	public IngredientFetcher (String a){
		// set up the URLs we need for testing.
		// TODO: by AGF, eventually automate these via data in a database
		this.posturl= a;
		
		// the put url needs the ID added dynamically like so:
		// http://clickslide.co/angelhack6/basicpages/ + ID + .xml
		this.puturl = a;
		this.loginurl = a;
		this.geturl = a;
		
		/*
		 * ADDED by AGF 8/5/12
		 * Instantiate the private static httpclient variable in the constructor
		 */
		this.httpclient = new DefaultHttpClient();
		// set the httpclient cookie policy to ignore cookies
		// TODO: this doesn't seem to affect anything.
		this.httpclient.getParams().setParameter("CookiePolicy", CookiePolicy.IGNORE_COOKIES);
	}
	
	
	/**
	 * Displays the data from a page if the correct session ID is entered.
	 * If unsuccessful an auto-generated catch block is displayed instead.
	 */
	public String getIt(){
		String htmlString = "";

		String info = "";
		
		System.out.println("Executing post request now");
        HttpGet httpget = new HttpGet(posturl);

        try {
            // Execute HTTP Post Request
        	HttpResponse response = this.httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            info = (""+EntityUtils.toString(entity));
            Header[] headers = response.getAllHeaders();
           // for(int i=0;i<headers.length;i++){
            	System.out.println();
            	System.out.println();
            	
           // 	System.out.println(i);
            	System.out.println();
            	System.out.println();
            	htmlString = ""+headers[20];
            	
            //	System.out.println(htmlString);

            	
          //  }
            System.out.println("\n"+info);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
	
	// Return a string
    return htmlString;
}
        
        
        public static String getUrlSource(String url2) throws IOException {
            URL yahoo = new URL(url2);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder a = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                a.append(inputLine);
            in.close();

            return a.toString();
        }
		

	

	protected HttpContext manageCookies(String sessid){
		CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("Set-Cookie", sessid);
        cookie.setPath("/");
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        return localContext;
	}
	public ArrayList<String> getIngredients() throws IOException{
	//	String rustic = "http://recipes.wikia.com/wiki/Curried_Pork_Medley";
	//	IngredientFetcher infe = new IngredientFetcher(rustic);
		String ing = getUrlSource(posturl);
		int a = ing.indexOf("Ingredients");
		int b = ing.indexOf("Directions");
		String ingr = ing.substring(a,b);
		a = ingr.indexOf("<ul>");
		b = ingr.indexOf("</ul");
		ingr = ingr.substring(a, b);
		ArrayList<String> ingredients = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(ingr,">");
		while (st.hasMoreTokens()) {//while there are more chunks
			String c = st.nextToken();//gets the next chunk
		//	System.out.println(c);
			if(c.contains("title")){
				String d = st.nextToken();
				int da = d.indexOf("<");

				ingredients.add(d.substring(0,da));
			}
			/*
			int ca = c.indexOf(">");
			ca++;
			int cb = c.indexOf("</a>");*/
		//	ingredients.add(c.substring(ca,cb));
			
	 }
		for(int j = 0; j<ingredients.size();j++){
			System.out.println(ingredients.get(j));
		}
		return(ingredients);
	}
	
	

}
