package dei.uc.pt.ar.paj.lyrics;
import java.io.IOException;
import java.io.StringReader;
import java.net.SocketException;
import dei.uc.pt.ar.paj.chartlyricsapi.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import dei.uc.pt.ar.paj.chartlyricsapi.Apiv1;
import dei.uc.pt.ar.paj.chartlyricsapi.Apiv1Soap;
import dei.uc.pt.ar.paj.chartlyricsapi.GetLyricResult;

public class ClientChartlyrics {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		Apiv1 apiv1 = new Apiv1();
		Apiv1Soap apiv1soap = apiv1.getApiv1Soap();
		Client client=ClientBuilder.newClient();
		String result = "";
		// Apiv1 apiv1;//=new Apiv1();
		// Apiv1Soap apiv1soap = null;// = apiv1.getApiv1Soap();
		GetLyricResult musiclyric = null;
		boolean test = true;

//		 @PostConstruct
//		    public void init() {
//		        result = "";
//		        client = ClientBuilder.newClient();
//		    }
		
		while (test) {

			try {
				// apiv1=new Apiv1();
				// apiv1soap = apiv1.getApiv1Soap();
				// Thread.sleep(15000);
				System.out.println("Procurar ChartLyrics por SOAP text\n");
				musiclyric = apiv1soap.searchLyricDirect("Metallica", "One");

				// Thread.sleep(5000);
			} catch (WebServiceException s) {
				System.out.println("erro socket! :"+s.getMessage());
				
				try {
					System.out.println("Procurar ChartLyrics por REST Result\n");
					result=musicRESTResult("Metallica", "One", client);
				} catch (XPathExpressionException
						| ParserConfigurationException | SAXException
						| IOException e) {
					// TODO Auto-generated catch block
					System.out.println("erro socket 2! :"+e.getMessage());
					//e.printStackTrace();
				}

			}

			if (musiclyric != null) {
				System.out.println(musiclyric.getLyric().toString());
				test = false;
			} else if(result!=null){
				System.out.println(result.toString());
			}
		}
	}

	public static String musicRESTResult(String artista, String titulo, Client client)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
			String result;
			
			result = client
				.target("http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?artist=string&song=string")
				.queryParam("artist", artista).queryParam("song", titulo)
				.request(MediaType.TEXT_PLAIN).get(String.class);

//		InputSource source = new InputSource(new StringReader(result));
//
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		Document document = db.parse(source);
//
//		XPathFactory xpathFactory = XPathFactory.newInstance();
//		XPath xpath = xpathFactory.newXPath();
//
//		result = xpath.evaluate("/GetLyricResult/Lyric", document);

		if (!result.isEmpty()) {
			return result;
		} else {
			return "Not Found";
		}
	}
}
