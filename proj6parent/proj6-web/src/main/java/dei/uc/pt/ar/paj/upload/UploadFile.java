package dei.uc.pt.ar.paj.upload;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dei.uc.pt.ar.paj.login.LoginMB;

@ManagedBean
@ApplicationScoped
public class UploadFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5537671907313363474L;
	static Logger logger = LoggerFactory.getLogger(UploadFile.class);

	private Part file;

	private String path;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void init() {
		this.file=null;
		this.fileName=this.path="";
	}

	public boolean validExtension() {
		if(this.file!=null){
			this.fileName=getFilename(this.file);

			if(this.fileName.length()>4){
				String extension=path.substring(path.length()-3);
				
				if(extension.equals("mp3")){
					return true;
				}else{
					this.file=null;
				}
				
			}
		}

		return false;
	}

	private String generatePath(Long musicId) {
		return "C:\\SpotiflyServerFileStorage\\"+musicId+".mp3";
	}

	private static String getFilename(Part part) {  
		for (String cd : part.getHeader("content-disposition").split(";")) {  
			if (cd.trim().startsWith("filename")) {  
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");  
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
			}
		}
		return "";  
	}

	//Cuidado com as excep��es
	public String upload(Long musicId){
		String path=generatePath(musicId);

		try {
			file.write("C:\\SpotiflyServerFileStorage\\"+musicId+".mp3");
			logger.info("Ficheiro = C:\\SpotiflyServerFileStorage\\"+musicId+".mp3 criado com sucesso!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Error generating upload file. See trace output.");
		}

		return "C:\\SpotiflyServerFileStorage\\"+musicId+".mp3";
	}


}