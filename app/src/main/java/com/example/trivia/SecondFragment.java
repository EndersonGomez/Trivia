package com.example.trivia;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.trivia.databinding.FragmentSecondBinding;


public class SecondFragment extends Fragment {

    //Instanciamos los objetos a utilizar.
    private FragmentSecondBinding Binding;
    private String nombre;
    private String respuesta_usuario;


    //Validamos que no este vacio el campo y luego guardamos el dato recibido en una variable.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString("clave1");
        }
    }

    //Configuramos el objeto viewBinding y enlazamos el diseño con el codigo.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Binding = FragmentSecondBinding.inflate(inflater,container,false);
        return Binding.getRoot();
    }

    //Realizamos toda la logica de programacion del fragmento.
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Seteamos el valor del textview con el valor recibido del fragmento anterior
        Binding.textview2.setText("Hola " + nombre);

        //Creamos el evento esuchador del radiogroup para obtener lo escogido por el usuario,
        //con un switch otorgarle un valor a la variable y asi poder pasar ese valor al siguiente fragmento.
        Binding.radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int index = Binding.radiogroup.indexOfChild(Binding.radiogroup.findViewById(checkedId));

                switch (index){
                    case 0:
                        respuesta_usuario = "VSC";
                        break;
                    case 1:
                        respuesta_usuario = "NB";
                        break;
                    case 2:
                        respuesta_usuario = "VS";
                        break;
                    case 3:
                        respuesta_usuario = "PC";
                        break;
                }
            }
        });

        //Creamos el evento escuchador del boton para pasar al siguiente fragmento
        // y llevarnos los datos necesarios.
        Binding.boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verificamos si no es vacio para ejecutar el codigo.
                if (respuesta_usuario != null){
                    //Creamos un objeto bundle para enviar los datos.
                    Bundle bundle = new Bundle();
                    bundle.putString("clave1",nombre);
                    bundle.putString("clave2",respuesta_usuario);
                    //Utilizamos el metodo Navigation para pasar al siguiente fragmento.
                    Navigation.findNavController(Binding.getRoot())
                            .navigate(R.id.action_secondFragment_to_thirdFragment,bundle);
                }else {
                    //De no contener nada la variable enviamos un mensaje de error.
                    Toast.makeText(getContext(), "Debes escoger una opción", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}