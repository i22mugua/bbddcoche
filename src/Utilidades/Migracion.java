package Utilidades;



/**
* Clase de utilidad para copiar tablas de una base de datos origen a una destino
*
* @author jdelgado
* @version 0.0.0.1 - 21 Feb 2007 (a partir de codigo de 2004)
*
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.StringTokenizer;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
//-- version sin log4J para poder compilarlo con gcj.
// si quieres usar log4j descomenta las lineas comentadas a continuacion y en los metodos debug y error.
//import org.apache.log4j.Logger;
public class Migracion
{
/**
* Activar / desactivar trazas
*/
static public boolean verbose=false;
/**
* Propiedades de conexion
*/
public String odriver,ouser,opass,ourl,oschema,otables,ddriver,duser,dpass,durl,dschema;
/**
* Trazas
*/
// descomenta esta linea si quieres usar log4j
//private static final Logger logger = Logger.getLogger(Migracion.class);
/**
* Constructror
* @param odriver nombre driver jdbc para origen
* @param ouser usuario de base de datos origen
* @param opass password de base de datos origen
* @param ourl url jdbc origen
* @param oschema schema origen (opcional)
* @param otables una lista separada por ',' de las tablas a migrar
* @param ddriver nombre driver jdbc para destino
* @param duser usuario de base de datos destino
* @param dpass password de base de datos destino
* @param durl url jdbc destino
* @param dschema schema destino (opcional)
*
* Nota: el modelo de datos debe existir en ambas bases de datos, el proceso de migracion no crea
* las tablas, y los nombres de tablas y columnas deben coincidir.
*/
public Migracion(String odriver,String ouser,String opass,String ourl,String oschema,String otables,String ddriver,String duser,String dpass,String durl,String dschema)
{
this.odriver = odriver;
this.ouser = ouser;
this.opass = opass;
this.ourl = ourl;
this.oschema = oschema;
this.otables = otables;
this.ddriver = ddriver;
this.duser = duser;
this.dpass = dpass;
this.durl = durl;
this.dschema = dschema;
}
//----
/**
* Constructor
* @param pathname ruta del fichero con las propiedades
* @throws Excepcion excepcion levantada en caso de error
*/
public Migracion(String pathname) throws Exception
{
this.leerProperties(pathname);
}
//----
public void imprimirParametros()
{
Migracion.debug(Migracion.class.getName(), "imprimirParametros", "SOURCE_DRIVER: "+this.odriver, 6, null, null);
Migracion.debug(Migracion.class.getName(), "imprimirParametros", "SOURCE_URL: "+this.ourl, 6, null, null);
Migracion.debug(Migracion.class.getName(), "imprimirParametros", "SOURCE_TABLES: "+this.otables, 6, null, null);
Migracion.debug(Migracion.class.getName(), "imprimirParametros", "TARGET_DRIVER: "+this.ddriver, 6, null, null);
Migracion.debug(Migracion.class.getName(), "imprimirParametros", "TARGET_URL: "+this.durl, 6, null, null);
}
/**
* Inicia el proceso de volcado de tablas.
* @return un pequeño informe con los tiempos de ejecucion.
* @throws Exception excepcion levantada en caso de error.
*/
public String migrar() throws Exception
{
Connection ORIGEN=null;
Connection DESTINO=null;
DatabaseUtil util = null;
try
{
imprimirParametros();
Migracion.debug(Migracion.class.getName(), "migrar", "@obtener conexiones", 6, null, null);
Class.forName(this.odriver);
ORIGEN=DriverManager.getConnection(this.ourl,this.ouser,this.opass);
Migracion.debug(Migracion.class.getName(), "migrar", "conexion origen:"+ORIGEN, 6, null, null);
Class.forName(this.ddriver);
DESTINO=DriverManager.getConnection(this.durl,this.duser,this.dpass);
Migracion.debug(Migracion.class.getName(), "migrar", "conexion destino:"+DESTINO, 6, null, null);
// Iniciar transaccion
Migracion.debug(Migracion.class.getName(), "migrar", "@Procesar tablas", 6, null, null);
util = new DatabaseUtil();
util.setVerbose(verbose);
// obtener tablas
StringTokenizer st = new StringTokenizer(this.otables,",");
while (st.hasMoreTokens())
{
String TABLENAME = st.nextToken();
Migracion.debug(Migracion.class.getName(), "migrar", "Procesando tabla:"+TABLENAME+"...", 6, null, null);
// Copiar el contenido de la tabla desde la base de datos origen a destino, borrando la tabla en el destino previamente
util.copyTable(TABLENAME, ORIGEN, DESTINO, true, null);
}
return util.getSb().toString();
}
catch(Exception e)
{
throw e;
}
finally
{
try
{
if (ORIGEN!=null) ORIGEN.close();
}
catch(Exception f)
{
;
}
try
{
if (DESTINO!=null) DESTINO.close();
}
catch(Exception f)
{
;
}
}
}
//----
/**
* Para mostrar un error si falta algun properties
* @param key valor de la clave
* @param name nombre de la clave
* @throws Exception excepcion levantada en caso de error
*/
public void existe(String key,String name) throws Exception
{
if (key==null) throw new Exception("clave ["+name+"] no encontrada en el fichero de propiedades");
}
//----
/**
* Leer las propiedades de conexion desde fichero
* @throws Excepcion excepcion levantada en caso de error
*/
public void leerProperties(String pathname) throws Exception
{
Properties p = null;
FileInputStream fin = null;
try
{
// leemos el contenido del fichero y lo cargamos en el properties
String FILENAME=pathname;
File f = new File(FILENAME);
if (!f.exists()) throw new Exception ("El fichero \'"+pathname+"\' no existe");
//InputStream is = getClass().getResourceAsStream(FILENAME);
fin = new FileInputStream(f);
p = new Properties();
p.load(fin);
// recuperamos las propiedades y las asignamos a la clase
String SOURCE_DRIVER = p.getProperty("SOURCE_DRIVER");
String SOURCE_URL = p.getProperty("SOURCE_URL");
String SOURCE_USER = p.getProperty("SOURCE_USER");
String SOURCE_PASSWORD = p.getProperty("SOURCE_PASSWORD");
String SOURCE_SCHEMA = p.getProperty("SOURCE_SCHEMA");
String SOURCE_TABLES = p.getProperty("SOURCE_TABLES");
String TARGET_DRIVER = p.getProperty("TARGET_DRIVER");
String TARGET_URL = p.getProperty("TARGET_URL");
String TARGET_USER = p.getProperty("TARGET_USER");
String TARGET_PASSWORD = p.getProperty("TARGET_PASSWORD");
String TARGET_SCHEMA = p.getProperty("TARGET_SCHEMA");
// chequeos de valores nulos
existe(SOURCE_DRIVER,"SOURCE_DRIVER");
existe(SOURCE_URL,"SOURCE_URL");
existe(SOURCE_USER,"SOURCE_USER");
existe(SOURCE_PASSWORD,"SOURCE_PASSWORD");
existe(SOURCE_TABLES,"SOURCE_TABLES");
existe(TARGET_DRIVER,"TARGET_DRIVER");
existe(TARGET_URL,"TARGET_URL");
existe(TARGET_USER,"TARGET_USER");
existe(TARGET_PASSWORD,"TARGET_PASSWORD");
// asignamos las propiedades leidas a los atributos de la clase
this.odriver = SOURCE_DRIVER;
this.ourl = SOURCE_URL;
this.ouser = SOURCE_USER;
this.opass = SOURCE_PASSWORD;
this.oschema = SOURCE_SCHEMA;
this.otables = SOURCE_TABLES;
this.ddriver = TARGET_DRIVER;
this.durl = TARGET_URL;
this.duser = TARGET_USER;
this.dpass = TARGET_PASSWORD;
}
catch(Exception e)
{
throw e;
}
finally
{
try
{
if (fin!=null) fin.close();
}
catch(Exception f)
{
;
}
}
}
//----
/**
* Para probar el correcto funcionamiento de la clase de ejemplo
* @param args argumentos de entrada
*/
public static void main(String[] args) {
try
{
String TABLAS="TABLA1,TABLA2,TABLA3";
/*
Migracion m = new Migracion(
"oracle.jdbc.driver.OracleDriver",
"tu_user",
"tu_password",
"jdbc:oracle:thin:@tu_hostname:1521:tu_basedatos",
null,
TABLAS,
"oracle.jdbc.driver.OracleDriver",
"tu_user",
"tu_password",
"jdbc:oracle:thin:@tu_hostname.hi.inet:1521:tu_basedatos",
null);
m.migrar();
*/
// comprobar parametros
int num_argumentos = args.length;
if (num_argumentos < 2)
{
System.out.println("migrar: copia las tablas especificadas desde una base de datos origen a una base de datos destino.");
System.out.println("sintaxis: migrar -file <fichero de configuracion> [-verbose]");
}
else
{
String argumento1 = args[0];
String argumento2 = args[1];
String argumento3 = "";
if (num_argumentos>2) argumento3 = args[2];
if (!argumento1.equalsIgnoreCase("-file"))
{
System.out.println("Parametro incorrecto");
System.out.println("sintaxis: migrar -file <fichero de configuracion> [-verbose]");
}
else
{
if (argumento3.equalsIgnoreCase("-verbose"))
{
//System.out.println("activado trazas del proceso (verbose)");
Migracion.verbose = true;
Migracion.debug(Migracion.class.getName(), "main", "activado trazas del proceso (verbose)", 6, null, null);
}
long time = System.currentTimeMillis();
Migracion m = new Migracion(argumento2);
String report = m.migrar();
long time_end = System.currentTimeMillis();
long total = time_end-time;
System.out.println("\r\nMigracion terminada\r\n-------------------\r\n");
System.out.println(report);
System.out.println("Process Total Time: "+total+" ms");
}
}
}
catch(Exception e)
{
//e.printStackTrace();
String ERROR = e.toString();
System.out.println(ERROR);
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
System.out.println(MENSAJE);
//logger.debug(MENSAJE);
//}
}
}
//--------------------------------------------
}
//end of class Migracion.java
