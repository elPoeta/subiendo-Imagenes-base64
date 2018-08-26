package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ImagenDao;
import domain.Imagen;



@WebServlet(name="ImagenServer", urlPatterns= {"/api/imagenServer"})
public class ImagenServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Gson CONVERTIR = new Gson();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         try {
             ArrayList<Imagen> imagenes = ImagenDao.getInstance().obtenerImagenes();
              System.out.println("img get "+imagenes);
             String resultado = CONVERTIR.toJson(imagenes);
             out.println("" + resultado);

         } catch (ClassNotFoundException ex) {
             out.println("Verificar:" + ex.getMessage());
         } catch (SQLException ex) {
             out.println("Verificar:" + ex.getMessage());
         } catch (Exception ex) {
             out.println("Verificar:" + ex.getMessage());
         } finally {
             out.close();
         }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        try {
	            String texto = request.getReader().readLine();

	            Imagen ImgParametro = CONVERTIR.fromJson(texto, Imagen.class);
                    System.out.println("img post "+ImgParametro);
	            ImagenDao.getInstance();
				ImagenDao.insertar(ImgParametro);
	            out.println(CONVERTIR.toJson("OK"));

	        } catch (ClassNotFoundException ex) {
	            out.println("Verificar: " + ex.getMessage());
	            System.out.println("Class > "+ex.getMessage());
	        } catch (SQLException ex) {
	            out.println("Verificar:" + ex.getMessage());
	            System.out.println("SQL > "+ex.getMessage());
	        } catch (Exception ex) {
	            out.println("Verificar:" + ex.getMessage());
	            System.out.println("EXcep > "+ex.getMessage());
	        } finally {
	            out.close();
	        }
	    }
	}

