import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Document doc=Jsoup.connect("http://www.naver.com").get();
			Connection.Response response=Jsoup.connect("http://www.naver.com")
					.method(Connection.Method.GET)
					.execute();
			Document doc=response.parse();
			Elements imgs=doc.select("img");
			for(int i=0; i<imgs.size();i++) {
				System.out.println(imgs.get(i).attr("src"));
			}
			/*
			 * Elements imgs=doc.getElementsByTag("a"); if(imgs.size()>0) { for(int i=0;
			 * i<imgs.size();i++) { String src=imgs.get(i).attr("href");
			 * System.out.println(src); }
			 * 
			 * }
			 */
			
			String html=doc.html();
			String text=doc.text();
			//System.out.println(html);
			System.out.println("++++++++++++++++++++++++++++++++++++++++");
			System.out.println(text);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
