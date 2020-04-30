/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblies;

import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author deste
 */
public class BiblIES {

    static ConexionSQL cc=new ConexionSQL();
    static Connection con=cc.conexion();    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ListaEnlazada listacodigo=new ListaEnlazada();
        ListaEnlazada listatitulo=new ListaEnlazada();
        ListaEnlazada listaautor=new ListaEnlazada();
        
        Cliente cliente1=new Cliente("Pregrado", 0, 1, "Daniel", "Prieto", "chupemestepenco@unal.edu.co", "1234");
        
        Admin nuevoAdmin=new Admin(4719301, "Notch", "Rodriguez", "NotchBoss99@gmail.com", "XContraseñaX");   
        
        long TInicio, TFin, tiempo;       
        TInicio = System.nanoTime();
        insertarLibrosSQL(nuevoAdmin,listacodigo,listatitulo,listaautor);
        TFin = System.nanoTime(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
        tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
        System.out.println("Tiempo de ejecución en milisegundos: " + tiempo); //Mostramos en pantalla el tiempo de ejecución en segundos
        
       
        //=======================================================================================

        nuevoAdmin.crearLibro("CABALLO DE TROYA 1", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
        nuevoAdmin.crearLibro("CABALLO DE TROYA 2", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
        nuevoAdmin.crearLibro("CABALLO DE TROYA 3", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
        nuevoAdmin.crearLibro("CABALLO DE TROYA 4", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
        nuevoAdmin.crearLibro("CABALLO DE TROYA 5", "JJ BENITEZ", "22/02/2002", 213,"ESPAÑOL", "DRAMA/POLICIACO", listacodigo, listatitulo, listaautor);
           
        Consola consola= new Consola ();
        consola.setAdmin(nuevoAdmin);
        consola.setCliente(cliente1);
        consola.insertarListas(listacodigo, listatitulo, listaautor);
        consola.encender();



        //=======================================================================================
         
         
    }

   static void insertarLibrosSQL(Admin administrador,ListaEnlazada listaCod,ListaEnlazada listaTitulo,ListaEnlazada listaAutor){        
       long TInicio = System.nanoTime();  
       
       
       String SQL="select * from millon";
        
       // String SQL="select * from libroshptas";
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            while(rs.next()){
                int codigo=rs.getInt(1);
                String titulo=rs.getString(2);
                String autor=rs.getString(3);
                String publicacion=rs.getString(4);
                int numeroPagina=rs.getInt(5);
                String idioma=rs.getString(6);
                String tema=rs.getString(7);
                //Date fechapublicacion=new Date(publicacion);
                administrador.crearLibro(codigo,titulo, autor, publicacion, numeroPagina, idioma, tema, listaCod, listaTitulo, listaAutor);
                if (rs.getInt(1)%10000==0){

                    System.out.println(rs.getInt(1));
                    
                    long TFin = System.nanoTime();
                    double tiempo = (TFin - TInicio)/10000000;
                    System.out.println("Tiempo de ejecución del ingreso en centesimas de segundos de "+rs.getInt(1)+" datos: " + tiempo);
                    System.out.print("=======================");

                }
            }
        } catch (Exception e) {
            System.out.println("valio verga"+e);
        }
    }

    void interfazAdmin(ListaEnlazada lista,Admin administrador){
        Scanner escaner=new Scanner(System.in);
        System.out.println("Seleccione la accion que desea realizar:");
        System.out.println("1.Agregar un nuevo libro");
        System.out.println("2.Borrar un libro");
        System.out.println("3.Modificar un dato");
        System.out.println("4.Buscar un libro por el nombre");
        System.out.println("5.Buscar un libro por el autor");
        int opcion=escaner.nextInt();
        switch (opcion) {
            case 1:
            System.out.print("Ingrese el titulo:");
            String titulo=escaner.nextLine();
            System.out.println("");
            System.out.print("Ingrese el nombre del autor:");
            String autor=escaner.nextLine();
            System.out.println("");
            System.out.print("Ingrese la fecha de publicacion:");
            String fecha=escaner.nextLine();
            Date fechaPublicacion=new Date(fecha);
            System.out.println("");
            System.out.print("Ingrese el numero de paginas:");
            int numeroPaginas=escaner.nextInt();
            System.out.println("");
            System.out.print("Ingrese el idioma:");
            String idioma=escaner.nextLine();
            System.out.println("");
            System.out.print("Ingrese el tema:");
            String tema=escaner.nextLine();
            System.out.println("");
          //  administrador.crearLibro(titulo, autor, fechaPublicacion, numeroPaginas, idioma, tema, lista);
                break;
            case 2:
                 System.out.print("Digite el id del libro que desea borrar:");
                 int id=escaner.nextInt();
                break;
            case 3:
                
                break;   
            case 4:
                
                break;     
            case 5:

                break;         
            default:
                break;
        }
    }
/*


    void interfazUsuario(ListaEnlazada lista,Cliente cliente){
        Scanner escaner=new Scanner(System.in);
        System.out.println("Seleccione la accion que desea realizar:");
        System.out.println("1.Mostrar todos los libros");
        System.out.println("2.Buscar un libro por el nombre");
        System.out.println("3.Buscar un libro por el autor");
        System.out.println("4.Comentar un texto");
        System.out.println("5.Calificar un texto");
        System.out.println("6.Agregar un texto a favoritos");
        System.out.println("7.Eliminar de favoritos");
        System.out.println("8.Mostrar los favoritos"); 
        int opcion=escaner.nextInt();
        switch (opcion) {
            case 1:
                cliente.imprimirTextos(lista);
                break;
            case 2:
                System.out.println("INGRESE EL NOMBRE QUE DESEA BUSCAR");
                String nombre=escaner.nextLine();
                cliente.buscarPorNombreExacto(nombre, lista);
                break;
            case 3:
                System.out.println("INGRESE EL NOMBRE del autor QUE DESEA BUSCAR");
                String autor=escaner.nextLine();
                cliente.buscarPorAutor(autor, lista);                
                break;             
            case 4:
                
                break;
            case 5:
                
                break;
            case 6:
                
                break;
            case 7:
                
                break;
            case 8:
                
                break;                        
            default:
                break;
        }
    }
    */


    }
    
