package pe.edu.cibertec.www.registrosalumnossqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pssolis on 10/05/2015.
 */
public class ClaseBase {

    public String CadenaConexion()
    {
        return "/data/data/pe.edu.cibertec.www.registrosalumnossqlite/alumnos.db";
    }

    public void CrearTabla()
    {
        SQLiteDatabase oDb=SQLiteDatabase.openDatabase(CadenaConexion(),
                null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        oDb.execSQL("CREATE TABLE IF NOT EXISTS Alumno (Codigo integer primery key,Nombres varchar(50),Apellidos varchar(50),FechaNacimiento varchar(10));");
    }
}







