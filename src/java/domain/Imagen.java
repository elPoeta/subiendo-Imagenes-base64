package domain;

public class Imagen {
	private Integer id;
	private String nombre;
	private int fileSize;
	private String fileType;
	private String imgBase64;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if(nombre == "" || nombre==null) {
			throw new IllegalArgumentException("El nombre esta vacio");
		}
		this.nombre = nombre;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		if(fileSize < 0) {
			throw new IllegalArgumentException("Error");
		}
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		if(fileType == "" || fileType==null) {
			throw new IllegalArgumentException("El tipo esta vacio");
		}
		this.fileType = fileType;
	}
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		if(imgBase64 == "" || imgBase64==null) {
			throw new IllegalArgumentException("La imagen esta vacia");
		}
		this.imgBase64 = imgBase64;
	}
	@Override
	public String toString() {
		return "Imagen [id=" + id + ", nombre=" + nombre + ", fileSize=" + fileSize + ", fileType=" + fileType
				+ ", imgBase64=" + imgBase64 + "]";
	}
	
	
	
}
