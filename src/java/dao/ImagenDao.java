package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Imagen;
import conexion.*;


public class ImagenDao {
	private static ImagenDao INSTANCE = null;
	private final static String SQL_IMAGENES_SELECT = "SELECT * FROM imagen;";
	private final static String SQL_IMAGEN_INSERT = "INSERT INTO imagen (nombre, file_size, file_type, img_base_64)values(?,?,?,?);";
	
	private ImagenDao()  throws ClassNotFoundException,
    IOException, SQLException {
		
	}
	
	public static ImagenDao getInstance() throws ClassNotFoundException,
	IOException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new ImagenDao();
		}
		return INSTANCE;
	}
	
	public ArrayList<Imagen> obtenerImagenes() throws ClassNotFoundException, IOException, SQLException{
		ArrayList<Imagen> imagenes = new ArrayList();
		Connection conexion = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		try {
			conexion = DB.getInstance().getConnection();
			ptsmt = conexion.prepareStatement(SQL_IMAGENES_SELECT);
			rs = ptsmt.executeQuery();
			Imagen imagen = null;
			while (rs.next()) {
			    try {
			        imagen = new Imagen();
			        imagen.setId(rs.getInt("id"));
			        imagen.setNombre(rs.getString("nombre"));
			        imagen.setFileSize(rs.getInt("file_size"));
			        imagen.setFileType(rs.getString("file_type"));
			        imagen.setImgBase64(rs.getString("img_base_64"));
			 
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			    imagenes.add(imagen);
			}
			} finally {
			try {
			    rs.close();
			} finally {
			    try {
			        ptsmt.close();
			    } finally {
			        conexion.close();
			    }
			}
			}
		
		return imagenes;
	}
	
	public static void insertar(Imagen img)
		       throws ClassNotFoundException,
		       IOException, SQLException {
		   Connection c = null;
		   PreparedStatement ptsmt = null;
		   try {
		       c = DB.getInstance().getConnection();
		       ptsmt = c.prepareStatement(SQL_IMAGEN_INSERT);
		       ptsmt.setString(1, img.getNombre());
		       ptsmt.setInt(2, img.getFileSize());
		       ptsmt.setString(3, img.getFileType());
		       ptsmt.setString(4, img.getImgBase64());
		       ptsmt.execute();
		   } finally {
		       try {
		           ptsmt.close();
		       } finally {
		           c.close();
		       }
		   }
		}
}
