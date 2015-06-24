package dei.uc.pt.ar.paj.lyrics;
import java.io.IOException;
import java.io.StringReader;
import java.net.SocketException;

import com.chartlyrics.api.*;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import test.app.GetLyriWikiResult;

import com.chartlyrics.api.Apiv1;
import com.chartlyrics.api.Apiv1Soap;
import com.chartlyrics.api.GetLyricResult;

public class RestClientLyricWIKI {

	public static void main(String[] args) throws SocketException {

		ResteasyClient reClient = new ResteasyClientBuilder().build();
		String result = "", result2="";

		GetLyricResult musiclyric = null;
		boolean test = true;
		ResteasyWebTarget tgt = null;
		int count=0;
		String artista="metallica";
		String musica="One";
		int t1=0, t2=0, t3=0;
		
	//	while (test) {

				System.out.println("Count ="+(count++));
			
				if(t2==0){
					artista="Cake";
					musica="Dime";
					artista="FAITH NO MORE";
					musica="We Care A Lot";
					artista="Alesso";
					musica="Forever";
					artista="Muse";
					musica="Drones";
					
					System.out.println("Procurar LiricWIKI API por REST Result\n");
					try {
						Thread.sleep(1000);
						
						tgt = reClient
								.target("http://lyrics.wikia.com/api.php?artist="+artista+"&song="+musica+"&fmt=xml");
						Response response = tgt.request(MediaType.APPLICATION_XML)
								.get();
						result2 = response.readEntity(GetLyriWikiResult.class).getLyric();
						System.out.println("Musica="+musica+", Artista= "+artista+"\n"+result2);
						t2=1;
			//			break;
					} catch (Exception e) {
						System.err.println("erro no socket para liriWIKI");
						
					}
				}	
//				try{
//					result=musicRESTResult("U2", "One",reClient, tgt);
//				} catch (XPathExpressionException
//						| ParserConfigurationException | SAXException | ProcessingException
//						| IOException e) {
//					// TODO Auto-generated catch block
//					System.out.println("erro socket 2! :"+e.getMessage());
//					//e.printStackTrace();
//				}

	//		}

			if(t2==1){
				System.out.println("\nEncontrou Lyric via RESTeasy()\n");
				System.out.println(result.toString());
				test=false;
			}
		}

	public static String musicRESTResult(String artista, String titulo, ResteasyClient client, ResteasyWebTarget tgt)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
			String result="", result1="";
			//GetLyricResult result2;
		
			tgt = client.target("http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?artist="+artista+"&song="+titulo);
			
			try{

				GetLyricResult result2 = tgt.request().get(GetLyricResult.class);
				result = result2.getLyric();
				if (!result.isEmpty()){
					System.out.println("\nEncontrou Lyric via RESTeasy() GetLyricResult\n");
					System.out.println(result.toString());
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			 
		
		if (!result.isEmpty()) {
			System.out.println("\nEncontrou Lyric via REST metodo 1\n");
			return result;
		}else
			return "Not Found";
		} 
	}
