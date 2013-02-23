package Utilidades;

/**
* DatabaseUtil.java
*
* Utileria para bases de datos
*
* @author jdelgado
* @version 1.0 - 31/05/2004
* @revision 1.1 - 21/02/2007
*
* @since JDK 1.4.1_02 - Eclipse 3 release M8
* @since revision JDK 1.5 - Eclipse 3.2
*
* TODO: sustituir metodo para recuperar nombres de columnas de las tablas no mediante SQL sino introspeccion
* en la base de datos
*
* */
import java.sql.*;
import java.util.Vector;
import java.io.*;
//-- version sin log4J para poder compilarlo con gcj.
// si quieres utilizar log4j descomenta las lineas
//import org.apache.log4j.Logger;
public class DatabaseUtil
{
/**
* Trazas
*/
//private static final Logger logger = Logger.getLogger(DatabaseUtil.class);
/**
* Activar / desactivar trazas
*/
static public boolean verbose=false;
/**
* report
*/
StringBuffer sb = new StringBuffer();
/**
* Copia el contenido de una tabla desde una base de datos origen a una base de datos destino.(NOTA: NO SE CIERRAN LAS CONEXIONES).
*
* @param tablename nombre de la tabla que se quiere copiar.
* @param source conexion origen conexion con la base de datos origen.
* @param target conexion conexion con la base de datos destino.
* @throws Exception excepcion levantada en caso de error.
*/
public void copyTable(String tablename, Connection source, Connection target, boolean deleteTarget,FileOutputStream out)
throws Exception
{
ResultSet rs = null;
Statement stmt = null;
ResultSetMetaData metadata = null;
String[] columnas = null;
Vector valores = null;
Vector SENTENCIAS_A_EJECUTAR = null;
Statement stmt2 = null;
String tablename2 = null;
int[] resultados=null;
try
{
tablename2 = tablename;
// crear conexion con el origen.
DatabaseUtil.debug(DatabaseUtil.class.getName(), "copyTable", "creando conexion con el origen...", 6, null, null);
try
{
stmt = source.createStatement();
}
catch(Exception e)
{
String error = e.getMessage();
DatabaseUtil.error(DatabaseUtil.class.getName(), "copyTable", "No se pudo establecer la conexion con el origen : "+error, 6, null, null);
throw new Exception("No se pudo establecer la conexion origen : "+error);
}
DatabaseUtil.debug(DatabaseUtil.class.getName(), "copyTable", "creando origen creada...", 6, null, null);
// crear conexion con el destino.
DatabaseUtil.debug(DatabaseUtil.class.getName(), "copyTable", "creando conexion con el destino...", 6, null, null);
try
{
stmt2 = target.createStatement();
}
catch(Exception e)
{
String error = e.getMessage();
DatabaseUtil.error(DatabaseUtil.class.getName(), "copyTable", "No se pudo establecer la conexion con el destino : "+error, 6, null, null);
throw new Exception("No se pudo establecer la conexion destino : "+e.getMessage());
}
DatabaseUtil.debug(DatabaseUtil.class.getName(), "copyTable", "creando destino creada...", 6, null, null);
// Contabilizar el numero de registros
String SENTENCIA_SQL = "SELECT COUNT(*) FROM "+tablename;
rs = stmt.executeQuery(SENTENCIA_SQL);
rs.next();
String numero_registros = rs.getString(1);
if (rs!=null) rs.close();
// TODO: cambiar esta parte pq funciona pero no es adecuada, hay una clase que nos devuelve el metadato de columnas de una tabla
// sin necesidad de emplear el SELECT * sino directamente analizando el esquema de la base de datos
// ejecutar la consulta para recuperar los nombres de las columnas.
SENTENCIA_SQL = "SELECT * FROM "+tablename;
rs = stmt.executeQuery(SENTENCIA_SQL);
// recuperamos los metadatos
metadata = rs.getMetaData();
int numero_columnas = metadata.getColumnCount();
DatabaseUtil.debug(DatabaseUtil.class.getName(), "copyTable", "La tabla tiene "+numero_columnas+" columnas", 6, null, null);
// las almacenamos en un Array de Strings.
columnas = new String[numero_columnas];
for (int i=1; i SQL anterior: ["+sentencia_anterior+"]", 6, null, null);
DatabaseUtil.debug(DatabaseUtil.class.getName(), "imprimeSQL", "==> SQL: ["+sentencia+"]", 6, null, null);
}
catch(Exception e)
{
DatabaseUtil.error(DatabaseUtil.class.getName(), "imprimeSQL", "No se ha podido recuperar la informacion de las sentencias SQL", 6, null, null);
}
}
//----
/**
* Imprime un mensaje de traza.
* @param nombre de la clase clase donde se genera el mensaje.
* @param metodo nombre del metodo donde se genera el mensaje.
* @param mensaje texto del mensaje.
* @param prioridad prioridad del mensaje.
* @param user usuario del mensaje.
* @param parameters parametros del mensaje.
*/
static public void debug(String clase,String metodo,String mensaje,int prioridad,String user,Object parameters)
{
if (verbose)
{
//if (logger.isDebugEnabled())
//{
String MENSAJE = "["+clase+"] ["+metodo+"] : "+mensaje;
//logger.debug(MENSAJE);
System.out.println(MENSAJE);
//}
}
}
//--------------------------------------------
/**
* Imprime un mensaje de error.
* @param nombre de la clase clase donde se genera el mensaje.
* @param metodo nombre del metodo donde se genera el mensaje.
* @param mensaje texto del mensaje.
* @param prioridad prioridad del mensaje.
* @param user usuario del mensaje.
* @param parameters parametros del mensaje.
*/
static public void error(String clase,String metodo,String mensaje,int prioridad,String user,Object parameters)
{
if (verbose)
{
//if (logger.isDebugEnabled())
//{
String MENSAJE = "["+clase+"] ["+metodo+"] : "+mensaje;
//logger.error(MENSAJE);
System.out.println(MENSAJE);
//}
}
}
//--------------------------------------------
public static boolean isVerbose() {
return verbose;
}
public static void setVerbose(boolean verbose) {
DatabaseUtil.verbose = verbose;
}
public StringBuffer getSb() {
return sb;
}
}
//end of DatabaseUtil.java