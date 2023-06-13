package com.example.trivia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trivia.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    //Declaramos los objetos a utilizar.
    private FragmentFirstBinding Binding;
    public String nombre;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Configuramos el objeto viewBinding y enlazamos el diseño con el codigo.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Binding = FragmentFirstBinding.inflate(inflater,container,false);
        return Binding.getRoot();
    }

    //Realizamos toda la logica de programacion del fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Creamos el evento escuchador para pasar al siguiente fragmento y llevarnos el nombre ingresado.
        Binding.boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtenemos el valor ingresado y lo guardamos en una variable.
                nombre = Binding.editText1.getText().toString();

                //Verificamos si no es vacio para ejecutar el codigo.
                if (!nombre.isEmpty()){
                    //Intanciamos un objeto bundle para pasar el dato al siguiente fragmento.
                    Bundle bundle = new Bundle();
                    bundle.putString("clave1" , nombre);
                    //Utilizamos el metodo Navigation para pasar al siguiente fragmento.
                    Navigation.findNavController(Binding.getRoot())
                            .navigate(R.id.action_firstFragment_to_secondFragment,bundle);
                }else {
                    //De no contener nada la variable enviamos un mensaje de error.
                    Toast.makeText(getContext(), "Debes escribir tu nombre", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}