package pe.edu.cibertec.www.registrosalumnossqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    AdaptadorAlumnos oAdaptadorAlumnos=null;
    ArrayList<Alumno_BE> oListaAlumnos=null;
    ListView olstAlumnos=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //oListaAlumnos=new ArrayList<Alumno_BE>();
        oListaAlumnos=new Alumno_SD().Listar(new Alumno_BE());
        oAdaptadorAlumnos=new AdaptadorAlumnos( getBaseContext(),
                R.layout.registroalumno, oListaAlumnos  );
        olstAlumnos=(ListView)findViewById(R.id.lstAlumnos);
        olstAlumnos.setAdapter(oAdaptadorAlumnos);

        olstAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(getBaseContext(), " Código: " + String.valueOf(oListaAlumnos.get(position).getCodigo()), Toast.LENGTH_LONG).show();


                Intent oREgistroNuevo = new Intent(getBaseContext(),
                        RegistroNuevoAlumno.class);
                oREgistroNuevo.putExtra("TIPOTRANSACCION", "A");
                oREgistroNuevo.putExtra("CodigoAlumno",
                        String.valueOf(oListaAlumnos.get(position).getCodigo())
                        );
                startActivity(oREgistroNuevo);

            }
        });
    }

    public void NuevoRegistroAlumno(View v)
    {
        Intent oREgistroNuevo = new Intent(getBaseContext(),
                RegistroNuevoAlumno.class);
        oREgistroNuevo.putExtra("TIPOTRANSACCION", "N");
        oREgistroNuevo.putExtra("CodigoAlumno", "0");
        startActivity(oREgistroNuevo);
    }

    public void BuscarAlumnos(View v)
    {
        Alumno_BE alumno_be = new Alumno_BE();
        EditText oedtBuscarPoNombres = (EditText)
                                        findViewById(R.id.edtBuscarPoNombres);
        alumno_be.setNombres(oedtBuscarPoNombres.getText().toString());
        oListaAlumnos=null;
        oListaAlumnos = new Alumno_SD().Listar(alumno_be);
        oAdaptadorAlumnos=new AdaptadorAlumnos( getBaseContext(),
                R.layout.registroalumno, oListaAlumnos  );
        olstAlumnos.setAdapter(oAdaptadorAlumnos);

    }

    public class AdaptadorAlumnos extends ArrayAdapter<Alumno_BE>
    {
        private ArrayList<Alumno_BE> ListaInterna;
        public AdaptadorAlumnos(Context context,
                                int textViewResourceId,
                                ArrayList<Alumno_BE> items)
        {
            super(context, textViewResourceId, items);
            this.ListaInterna = items;
        }

        @Override
        public View getView(int position,
                            View convertView,
                            ViewGroup parent)
        {
            View oLayoutRegistro = convertView;
            if (oLayoutRegistro == null)
            {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                oLayoutRegistro = vi.inflate(R.layout.registroalumno,
                        null);
            }
            Alumno_BE oAlumno = ListaInterna.get(position);
            if (oAlumno != null) {

TextView oNombresAlumno= (TextView)oLayoutRegistro.findViewById(
        R.id.tvnombres_ROW);
TextView oFechaNacimiento=(TextView)oLayoutRegistro.findViewById(
        R.id.tvFechaNacimiento_ROW);
oNombresAlumno.setText(oAlumno.getNombres());
oFechaNacimiento.setText(oAlumno.getFechaNacimiento());
            }
            return oLayoutRegistro;
        }
    }

    ////fin adaptador

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
