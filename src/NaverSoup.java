import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NaverSoup {
	public static void main(String[] args) {
		try {
			String url="https://movie.naver.com/movie/running/current.nhn";
			Document doc=Jsoup.connect(url).get();
			Elements movieList=doc.select(".lst_detail_t1").select("li");
			int size=movieList.size();
			
			for(int i=0; i<size;i++) {
				Element movie=movieList.get(i);
				String tit=movie.select(".tit").select("span").text();
				//System.out.println(tit);
				String title=movie.select(".tit").select("a").text();
				//System.out.println(title);
				String score=movie.select(".star")
						.select(".info_star").select(".num").text();
				//System.out.println(score);
				Elements elements=movie.select(".info_txt1").select("dd");
				String str=elements.get(0).text();
				//System.out.println(str);
				StringTokenizer st=new StringTokenizer(str,"|");
				int cnt=st.countTokens();
				String genre=null;
				String time=null;
				String open=null;
				if(cnt==3) {
					genre=st.nextToken();
					genre=genre.trim();
					time=st.nextToken();
					open=st.nextToken();
				}else {
					time=st.nextToken();
					open=st.nextToken();
				}
				
				time=time.trim();
				open=open.trim();
//				System.out.println(genre);
//				System.out.println(time);
//				System.out.println(open);
				String directors=null;
				String actors=null;
				directors=elements.get(1).text();
				//System.out.println("director:"+directors);
				try {
					actors=elements.get(2).text();
				}catch(Exception ex) {
					actors="없음";
				}
				//System.out.println("actors:"+actors);
				
				String strImgUrl=movie.select("img").attr("src");
				//System.out.println(strImgUrl);
				int start=strImgUrl.lastIndexOf("/");
				int end=strImgUrl.lastIndexOf(".");
				String strImgName=strImgUrl.substring(start+1,end+4);
				//System.out.println(strImgName);
				//strImgName=strImgName.substring(0,end);
				String ext=strImgName.substring(strImgName.indexOf("."));
				//System.out.println(ext);
				String imageName="movie"+i+ext;
				downloadImage(imageName,strImgUrl);
				
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();

		}
	}
	private static void downloadImage(String imageName, String strImgUrl) {
		try {
			
			URL urlImage=new URL(strImgUrl);
			InputStream in=urlImage.openStream();
			byte[] buffer=new byte[4096];
			int n=-1;
			OutputStream os=new FileOutputStream("images/"+imageName);
			while((n=in.read(buffer))!=-1) {
				os.write(buffer,0,n);
			}
			os.close();
			System.out.println("image saved"+imageName);
		}catch(Exception ex) {
			ex.getSuppressed();
		}
	}

}
