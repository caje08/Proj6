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

import com.chartlyrics.api.Apiv1;
import com.chartlyrics.api.Apiv1Soap;
import com.chartlyrics.api.GetLyricResult;

public class ClientChartlyrics {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		Apiv1 apiv1 = new Apiv1();
		Apiv1Soap apiv1soap = apiv1.getApiv1Soap();

		String result = "";

		GetLyricResult musiclyric = null;
		boolean test = true;

		int count=0;

		
		while (test) {

			try {

				System.out.println("Procurar ChartLyrics por SOAP text\n");
				musiclyric = apiv1soap.searchLyricDirect("Metallica", "One");

				// Thread.sleep(5000);
			} catch (WebServiceException s) {
				
				System.out.println("Count= "+count+"--> erro socket! :"+s.getMessage());
			}

			if (musiclyric != null) {
				System.out.println("\nEncontrou Lyric via SOAP() após "+count+" tentativas!\n");
				System.out.println(musiclyric.getLyric().toString());
				test = false;
			}
			count++;
		}
	}
}
