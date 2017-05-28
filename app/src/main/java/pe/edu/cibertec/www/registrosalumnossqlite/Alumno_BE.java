package pe.edu.cibertec.www.registrosalumnossqlite;

/**
 * Created by pssolis on 10/05/2015.
 */
public class Alumno_BE {

    private int Codigo;
    private String Nombres;
    private String Apellidos;
    private String FechaNacimiento;

    private int CAMPOS_Codigo=0;
    private int CAMPOS_Nombres=1;
    private int CAMPOS_Apellidos=2;
    private int CAMPOS_FechaNacimiento=3;


    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public Alumno_BE()
    {
        this.Codigo=0;
        this.Nombres="";
        this.Apellidos="";
        this.FechaNacimiento="";

    }

    public Alumno_BE(int pCodigo,
            String pNombres,
            String pApellidos,
            String pFechaNacimiento)
    {
        this.Codigo=pCodigo;
        this.Nombres=pNombres;
        this.Apellidos=pApellidos;
        this.FechaNacimiento=pFechaNacimiento;

    }

}
