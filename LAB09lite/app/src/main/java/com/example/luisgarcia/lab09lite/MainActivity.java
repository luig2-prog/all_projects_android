package com.example.luisgarcia.lab09lite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Fragment datos;
    private EditText nombre, apellido,list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = new AndroidFragment();
        this.setDefaultFragment(datos);


        nombre = (EditText)findViewById(R.id.nombretxt);
        apellido = (EditText)findViewById(R.id.apelltxt);
        list = (EditText)findViewById(R.id.listaTxt);



    }


    // Damos de alta los usuarios en nuestra aplicación
    public void alta(View v) {

        MetodosSQL admin = new MetodosSQL(this,"administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();


        String nom = nombre.getText().toString();
        String apll = apellido.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nombre", nom);
        registro.put("apellido", apll);


        // los inserto en la base de datos
        bd.insert("usuario", null, registro);

        bd.close();

        // ponemos los campos a vacío para insertar el siguiente usuario
        nombre.setText(""); apellido.setText("");

        Toast.makeText(this, "Datos del usuario cargados", Toast.LENGTH_SHORT).show();

    }

    //---

    // Hacemos búsqueda de usuario por DNI
    public void consulta(View v) {

        MetodosSQL admin = new MetodosSQL(this, "administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String nom = nombre.getText().toString();

        Cursor fila = bd.rawQuery("select nombre, apellido from usuario where nombre like '" + nom +"'", null);

        if (fila.moveToFirst()) {

            apellido.setText(fila.getString(1));
            list.setText(fila.getString(0));

            //et3.setText(fila.getString(1));
            //et4.setText(fila.getString(2));

        } else
            Toast.makeText(this, "No existe ningún usuario con ese nombre",Toast.LENGTH_SHORT).show();

        bd.close();

    }


    //--
    /* Método para dar de baja al usuario insertado*/
    public void baja(View v) {

        MetodosSQL admin = new MetodosSQL (this,"administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();

        String nom = nombre.getText().toString();

        // aquí borro la base de datos del usuario por el dni
        int cant = bd.delete("usuario", "nombre like '" + nom +"'", null);

        bd.close();

        nombre.setText(""); apellido.setText("");

        if (cant == 1)

            Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();

        else

            Toast.makeText(this, "No existe usuario", Toast.LENGTH_SHORT).show();
    }

   //-------

    // Método para modificar la información del usuario
    public void modificacion(View v) {

        MetodosSQL admin = new MetodosSQL(this,"administracion", null, 1);

        SQLiteDatabase bd = admin.getWritableDatabase();


        String nom = nombre.getText().toString();
        String appll = apellido.getText().toString();


        ContentValues registro = new ContentValues();

        // actualizamos con los nuevos datos, la información cambiada
        registro.put("nombre", nom);
        registro.put("apellido", appll);


        int cant = bd.update("usuario", registro, "nombre like '" + nom +"'", null);

        bd.close();

        if (cant == 1)

            Toast.makeText(this, "Datos modificados con éxito", Toast.LENGTH_SHORT).show();

        else

            Toast.makeText(this, "No existe usuario", Toast.LENGTH_SHORT).show();

    }




    // This method is used to set the default fragment that will be shown.
    private void setDefaultFragment(Fragment defaultFragment)
    {
        this.replaceFragment(defaultFragment);
    }

    // Replace current Fragment with the destination Fragment.
    public void replaceFragment(Fragment destFragment)
    {
        // First get FragmentManager object.
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the layout holder with the required Fragment object.
        fragmentTransaction.replace(R.id.datos, destFragment);

        // Commit the Fragment replace action.
        fragmentTransaction.commit();
    }


}
