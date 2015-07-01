package dei.uc.pt.ar.paj.ejb;

//	import com.LyricWiki.LyricWikiPortType_Stub;
//	import com.LyricWiki.LyricWiki_Impl;
//	import com.LyricWiki.LyricsResult;
import com.chartlyrics.api.Apiv1;
import com.chartlyrics.api.GetLyricResult;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceRef;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import wiki.api.GetLyriWikiResult;
import dei.uc.pt.ar.paj.Entities.*;

	/**
	 *
	 * @author 
	 */
	@Named
	@RequestScoped
	public class WebServiceControl {

	//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ChartLyrics.wsdl")
	    private com.chartlyrics.api.Apiv1 service;

	   
	    private String webservice;
	    private MusicEntity m;
	    private boolean disable;
	    private MusicEntity music;
	    private String result;
	    private ResteasyClient client;
	    private ResteasyWebTarget target = null;
	    private Response response=null;
	    
	    @Inject
	    private LyricControl lyricontrol;
//	    @Inject
//	    private GeneralController generalController;
	    
	 //   static Logger logger = (Logger) LoggerFactory.getLogger(WebServiceControl.class);
	    /**
	     * Creates a new instance of WebServiceController
	     */
	    public WebServiceControl() {
	    }

	    @PostConstruct
	    public void init() {
	        result = "";
	        client = new ResteasyClientBuilder().build();
	    }
	    
	    	    //---ChartLyric---
	    public String getLyricSong(MusicEntity m) {
	        GetLyricResult glr = searchLyricDirect(m.getInterprete(), m.getNomemusica());
	        this.result = glr.getLyric();
	        if (!result.isEmpty()) {
	            return result;
	        } else {
	            return "Not Found";
	        }
	    }
	    
	    public String getSoapLyricMusic(MusicEntity m) {
	        GetLyricResult glr = searchLyricDirect(m.getInterprete(), m.getNomemusica());
	        this.result = glr.getLyric();
	        if (!result.isEmpty()) {
	            return result;
	        } else {
	            return "Not Found";
	        }
	    }

	    private com.chartlyrics.api.GetLyricResult searchLyricDirect(java.lang.String artist, java.lang.String song) {
	        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
	        // If the calling of port operations may lead to race condition some synchronization is required.
	        com.chartlyrics.api.Apiv1Soap port = service.getApiv1Soap();
	        return port.searchLyricDirect(artist, song);
	    }

	    public String getMusicRESTResult(MusicEntity m) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
	        String artista=m.getInterprete();
	        String musica=m.getNomemusica();
	        
	    	this.target = client.target("http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?artist="+artista+"&song="+musica);
	        response = target.request(MediaType.APPLICATION_XML).get();
	        this.result=response.readEntity(GetLyricResult.class).getLyric();
	        
	        if (!result.isEmpty()) {
	            return result;
	        } else {
	            return "Not Found";
	        }
	    }
	    
	    public String getLyricWikiRESTResult(MusicEntity m) {
	    	String artista=m.getInterprete();
	        String musica=m.getNomemusica();
	        
	    	this.target = client.target("http://lyrics.wikia.com/api.php?artist="+artista+"&song="+musica+"&fmt=xml");
	    	response = target.request(MediaType.APPLICATION_XML)
					.get();
			result = response.readEntity(GetLyriWikiResult.class).getLyric();
	    	
			if (!result.isEmpty()) {
	            return result;
	        } else {
	            return "Not Found";
	        }
	    }
	    
	    public String getLyricsMusicExists(MusicEntity m) throws Throwable {
	        boolean existSong = false;
	        String text="";
	        int i=0;
	        
	        while(i<100 && !existSong){
	        
	          if(i%3==0){	
	             try {	           
	               text = lyricontrol.getLyricsToMusic(m, "getLikiwiki");
	               Logger.getLogger(WebServiceControl.class.getName()).log(Level.INFO, "Lyric found by REST from lyrics.wikia.com ");
	             } catch (Exception e1) {
	        	   System.out.println("getLyricsMusicExists() exception error into webServiceControl class with text 'getLikiwiki'\n");
	               Logger.getLogger(WebServiceControl.class.getName()).log(Level.SEVERE, null, e1);
	             }
	          }else if(i%3==1){
	        	  try{
	        		  text = lyricontrol.getLyricsToMusic(m, "getLyricrest");
	        		  Logger.getLogger(WebServiceControl.class.getName()).log(Level.INFO, "Lyric found by REST from chartlyrics.com ");
	        	  }catch (Exception e2) {
	        	  
	        		  System.out.println("getLyricsMusicExists() exception error into webServiceControl class with text 'getLyricrest'\n");
		               Logger.getLogger(WebServiceControl.class.getName()).log(Level.SEVERE, null, e2);
	        	  }
	          }else{
	        	  
	        	  try{
	        		  text = lyricontrol.getLyricsToMusic(m, "getLyricsoap");
	        		  Logger.getLogger(WebServiceControl.class.getName()).log(Level.INFO, "Lyric found by SOAP from chartlyrics.com ");
	        	  }catch(Exception e3) {
		        	   System.out.println("getLyricsMusicExists() exception error into webServiceControl class with text 'getLikiwiki'\n");
		               Logger.getLogger(WebServiceControl.class.getName()).log(Level.SEVERE, null, e3);
		             }	          
	          }

	        
	           if(text!=null && text!="" && !text.equals("Not Found"))
	        	 existSong=true;
	           i++;
	        }
	        return text;
	    }
	    
	    public boolean checkMusicExists(MusicEntity m) throws Throwable {
	        boolean existSong = false;
	        String text="";
	        try {	           
	            text = lyricontrol.callGetLyricsService(m, "getLikiwiki");
	            
	        } catch (Exception ex) {
	        	System.out.println("checkMusicExists() exception error into webServiceControl class with text 'getLikiwiki'\n");
	            Logger.getLogger(WebServiceControl.class.getName()).log(Level.SEVERE, null, ex);
	           
	            text = lyricontrol.callGetLyricsService(m, "getLyricrest");
	            if(text==null || text=="")
	            	text = lyricontrol.callGetLyricsService(m, "getLyricsoap");
	        }
	        
	        if(text!=null && text!="")
	        	existSong=true;
	        	        
	        return existSong;
	    }

	    //NOT USED - TO DELETE
	    
	    public String songRESTResult(MusicEntity m) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
	        this.result = client.target("http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect")
	                .queryParam("artist", m.getInterprete())
	                .queryParam("song", m.getNomemusica())
	                .request(MediaType.TEXT_PLAIN)
	                .get(String.class);

	        InputSource source = new InputSource(new StringReader(result));

	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document document = db.parse(source);

	        XPathFactory xpathFactory = XPathFactory.newInstance();
	        XPath xpath = xpathFactory.newXPath();

	        result = xpath.evaluate("/GetLyricResult/Lyric", document);

	        if (!result.isEmpty()) {
	            return result;
	        } else {
	            return "Not Found";
	        }
	    }
	    //NOT USED - TO DELETE
	    public String lyricRESTResult(MusicEntity m) {
	        this.result = client.target("http://lyrics.wikia.com/api.php")
	                .queryParam("func", "getSong")
	                .queryParam("artist", m.getInterprete())
	                .queryParam("song", m.getNomemusica())
	                .queryParam("fmt", "text")
	                .request(MediaType.TEXT_PLAIN)
	                .get(String.class);
	        return result;
	    }
	    
	  /*  //---LyricWiki---
	    public String songLyricWikiSOAP(MusicEntity m) throws RemoteException {
	        LyricWikiPortType_Stub lw = createProxy();
	        LyricsResult lr = lw.getSong(m.getInterprete(), m.getNomemusica());
	        return this.result = lr.getLyrics();
	    }

	    public String lyricRESTResult(MusicEntity m) {
	        this.result = client.target("http://lyrics.wikia.com/api.php")
	                .queryParam("func", "getSong")
	                .queryParam("artist", m.getInterprete())
	                .queryParam("song", m.getNomemusica())
	                .queryParam("fmt", "text")
	                .request(MediaType.TEXT_PLAIN)
	                .get(String.class);
	        return result;
	    }

	    private static LyricWikiPortType_Stub createProxy() {
	        return (LyricWikiPortType_Stub) (new LyricWiki_Impl().getLyricWikiPort());
	    }

	    public boolean checkSongExists(MusicEntity m) {
	        boolean existSong = false;
	        try {
	            LyricWikiPortType_Stub lwpts = createProxy();
	            existSong = lwpts.checkSongExists(m.getInterprete(), m.getNomemusica());
	            return existSong;
	        } catch (RemoteException ex) {
	            Logger.getLogger(WebServiceControl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return existSong;
	    }

	    public String buttonLyric(MusicEntity m) {
	        if (m.isLyricExist()) {
	            return "LYRIC";
	        } else if (checkSongExists(m)) {
	            return "LYRIC";
	        }
	        return "NO LYRIC";
	    }*/

	    //---GETTERS E SETTERS
	    public String getResult() {
	        return result;
	    }

	
	    public String getWebservice() {
	        return webservice;
	    }

	    public void setWebservice(String webservice) {
	        this.webservice = webservice;
	    }

	    public boolean isDisable() {
	        return disable;
	    }

	    public void setDisable(boolean disable) {
	        this.disable = disable;
	    }

	    public MusicEntity getMusic() {
	        return music;
	    }

	    public void setMusic(MusicEntity music) {
	        this.music = music;
	    }

	 /*   public boolean disable(MusicEntity m) {
	        return !checkSongExists(m);
	    }
*/
	    public Apiv1 getService() {
	        return service;
	    }

	    public void setService(Apiv1 service) {
	        this.service = service;
	    }

	    public MusicEntity getM() {
	        return m;
	    }

	    public void setM(MusicEntity m) {
	        this.m = m;
	    }

	     public void setResult(String result) {
	        this.result = result;
	    }
}
