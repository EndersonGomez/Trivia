package com.example.trivia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trivia.databinding.FragmentFifthBinding;


public class FifthFragment extends Fragment {

    //Instanciamos los objetos a utilizar.
    private FragmentFifthBinding Binding;
    private String nombre;
    private String respuesta_usuario;
    private boolean pasado;


    //Validamos que no este vacio el campo y luego guardamos el dato recibido en una variable.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString("clave1");
            respuesta_usuario = getArguments().getString("clave2");
        }
    }

    //Configuramos el objeto viewBinding y enlazamos el diseño con el codigo.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Binding = FragmentFifthBinding.inflate(inflater,container,false);
        return Binding.getRoot();
    }

    //Realizamos toda la logica de programacion del fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Verificamos primero que la respuesta no sea erronea.
        if (respuesta_usuario.equals("VSC") || respuesta_usuario.equals("TH") || respuesta_usuario.equals("SP")){

            //Seteamos el valor del texto para mostrar que se equivoco el usario.
            Binding.textView6.setText("Buen intento " + nombre + " intentalo de nuevo.");
            //Seteamos el texto del boton para concordar con el intento fallido.
            Binding.boton5.setText("VOLVER A INTENTAR");
            //le damos un valor negativo a la variable para indicar que el intento fue fallido.
            pasado = false;
        }else {
            //Seteamos el valor del texto para mostrar que fue exitoso el intento.
            Binding.textView6.setText("¡¡Genial, lo lograste! " + nombre +
                    " ¡Felicidades!");
            //Seteamos el valor del texto del boton para cerrar la aplicacion.
            Binding.boton5.setText("CERRAR");
            //Le damos un valor positivo a la variable para indicar que el intento fue exitoso.
            pasado = true;
        }

        //Creamos el evento escuhador para movernos entre fragmentos y pasar los datos.
        Binding.boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el objeto Bundle para pasar el dato en caso de fallar el intento y no perderlo.
                Bundle bundle = new Bundle();
                bundle.putString("clave1",nombre);
                //Verificamos primero que la variable sea verdadera para cerrar el programa.
                if (pasado){
                    getActivity().finish();
                }else {
                    //De ser falsa volvemos al fragmento anterior.
                    Navigation.findNavController(Binding.getRoot())
                            .navigate(R.id.action_fifthFragment_to_fourthFragment,bundle);
                }
            }
        });
    }
}