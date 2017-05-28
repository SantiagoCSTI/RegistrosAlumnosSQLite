package pe.edu.cibertec.www.registrosalumnossqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Cibertec on 18/10/2015.
 */
public class Alumno_SD extends ClaseBase {

    public Alumno_SD()
    {
        this.CrearTabla();
    }

    public ArrayList<Alumno_BE> Listar(Alumno_BE pAlumno_BE)
    {
        ArrayList<Alumno_BE> oListaAlumnos=new ArrayList<Alumno_BE>();
        SQLiteDatabase odb=SQLiteDatabase.openDatabase(
                this.CadenaConexion(),
                null,
                SQLiteDatabase.CREATE_IF_NECESSARY );
        Cursor oTabla=odb.rawQuery("select * from Alumno where Nombres like '%"
                + pAlumno_BE.getNombres().toString() + "%'",null);
        if (oTabla.moveToFirst() )
        {
          do
          {
              //Codigo integer ,Nombres varchar(50),Apellidos varchar(50),FechaNacimiento varchar(10)
              Alumno_BE oAlumno_BE=new Alumno_BE();
              oAlumno_BE.setCodigo(oTabla.getInt(0));
              oAlumno_BE.setNombres(oTabla.getString(1));
              oAlumno_BE.setApellidos(oTabla.getString(2));
              oAlumno_BE.setFechaNacimiento(oTabla.getString(3));
              oListaAlumnos.add(oAlumno_BE);
          } while (oTabla.moveToNext());
        }
        oTabla.close();
        odb.close();
        return oListaAlumnos;
    }

    public Alumno_BE Listarkey(int pCodigoAlumno)
    {
        Alumno_BE  oAlumno_BE=new Alumno_BE();
        SQLiteDatabase odb=SQLiteDatabase.openDatabase(
                this.CadenaConexion(),
                null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor oTabla=odb.rawQuery("select * from Alumno where Codigo="
                + String.valueOf(pCodigoAlumno),null);
        if (oTabla.moveToFirst() )
        {
            do
            {   //Codigo integer ,Nombres varchar(50),Apellidos varchar(50),FechaNacimiento varchar(10)
                oAlumno_BE.setCodigo(oTabla.getInt(0));
                oAlumno_BE.setNombres(oTabla.getString(1));
                oAlumno_BE.setApellidos(oTabla.getString(2));
                oAlumno_BE.setFechaNacimiento(oTabla.getString(3));
            } while (oTabla.moveToNext());
        }
        oTabla.close();
        odb.close();
        return oAlumno_BE;
    }

    public long RegistraModifica(Alumno_BE pAlumno_BE, String pTipoTraccion)
    {

        SQLiteDatabase odb=SQLiteDatabase.openDatabase(
                this.CadenaConexion(),
                null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues oValores=new ContentValues();

        //Codigo integer ,Nombres varchar(50),Apellidos varchar(50),FechaNacimiento varchar(10)
        oValores.put("Codigo",pAlumno_BE.getCodigo());
        oValores.put("Nombres",pAlumno_BE.getNombres());
        oValores.put("Apellidos",pAlumno_BE.getApellidos());
        oValores.put("FechaNacimiento",pAlumno_BE.getFechaNacimiento());
        long NumeroRegistro=0;
        if (pTipoTraccion.equals("N")) {
            NumeroRegistro = odb.insert("Alumno", null, oValores);
        }
        else if (pTipoTraccion.equals("A"))
        {
          String[] ValoresCamposCondicion={
                  String.valueOf(pAlumno_BE.getCodigo())
          };
          NumeroRegistro = odb.update("Alumno",
                    oValores,
                    "codigo=?",
                    ValoresCamposCondicion);
        }
        odb.close();
return NumeroRegistro;
    }

    public  int Elimnar(int pCodigoAlumno)
    {
        SQLiteDatabase odb=SQLiteDatabase.openDatabase(
                this.CadenaConexion(),
                null,
                SQLiteDatabase.CREATE_IF_NECESSARY);

        String[] ValoresCamposCondicion={
                String.valueOf(pCodigoAlumno)
        };
        int NumeroRegistro = odb.delete("Alumno",
                "codigo=?",
                ValoresCamposCondicion);
        odb.close();
        return 1;
    }

}
