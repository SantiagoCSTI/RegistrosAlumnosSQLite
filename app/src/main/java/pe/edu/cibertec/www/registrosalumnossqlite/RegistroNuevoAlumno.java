package pe.edu.cibertec.www.registrosalumnossqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroNuevoAlumno extends Activity {

    EditText oedtCodigo=null;
    EditText oedtNombres=null;
    EditText oedtapellidos=null;
    EditText oedtfechanacimiento=null;
    Button oBtnEliminar=null;
    String TIPOTRACCION="N";
    int CodigoAlumno=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_nuevo_alumno);

        Bundle oParametro=getIntent().getExtras();
        TIPOTRACCION=oParametro.getString("TIPOTRANSACCION");
        CodigoAlumno=Integer.valueOf(oParametro.getString("CodigoAlumno"));
        oedtCodigo=(EditText)findViewById(R.id.edtCodigo);
        oedtNombres=(EditText)findViewById(R.id.edtNombres);
        oedtapellidos=(EditText)findViewById(R.id.edtapellidos);
        oedtfechanacimiento=(EditText)findViewById(R.id.edtfechanacimiento);
        oBtnEliminar=(Button)findViewById(R.id.btnEliminar);
        oBtnEliminar.setVisibility(View.GONE);
        MostrarDatos();
    }

    public void MostrarDatos()
    {
        Toast.makeText(getBaseContext(), " tipo transacción: " + TIPOTRACCION, Toast.LENGTH_LONG).show();
        if (TIPOTRACCION.equals("A"))
        {
            oBtnEliminar.setVisibility(View.VISIBLE);
            Alumno_BE oAl=new Alumno_SD().Listarkey(CodigoAlumno);
            oedtCodigo.setText(String.valueOf(oAl.getCodigo()));
            oedtNombres.setText(oAl.getNombres());
        }
    }

    public void GrabarDatos(View v) {
        Alumno_SD oAlumno_SD = new Alumno_SD();
        Alumno_BE oAlumno_BE = new Alumno_BE();
        int Codigo = 0;
        if (!oedtCodigo.getText().toString().trim().equals("")) {
            Codigo = Integer.valueOf(oedtCodigo.getText().toString());
        }
        oAlumno_BE.setCodigo(Codigo);
        oAlumno_BE.setNombres(oedtNombres.getText().toString());
        oAlumno_BE.setApellidos(oedtapellidos.getText().toString());
        oAlumno_BE.setFechaNacimiento(oedtfechanacimiento.getText().toString());
        Codigo = (int) oAlumno_SD.RegistraModifica(oAlumno_BE, TIPOTRACCION);
        Toast.makeText(getBaseContext(), "Se grabo correctamente", Toast.LENGTH_LONG).show();
    }

    public void EliminarRegistro(View v)
    {
       Alumno_SD oAlumno= new  Alumno_SD();
        int Codigo = 0;
        if (!oedtCodigo.getText().toString().trim().equals("")) {
            Codigo = Integer.valueOf(oedtCodigo.getText().toString());
        }
        oAlumno.Elimnar(Codigo);
        Toast.makeText(getBaseContext(), "Eliminado correctamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro_nuevo_alumno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
