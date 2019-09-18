package com.example.proyectodam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class AdaptadorTests extends RecyclerView.Adapter<AdaptadorTests.ViewHolder> {

    private static final String TAG = "XYZ";
    private final OnItemClickListener listener;
    private ArrayList<Test> listaTests;


    public AdaptadorTests(ArrayList<Test> lista, OnItemClickListener listener) {
        this.listaTests = lista;
        this.listener = listener;
    }


    public void setArray(ArrayList<Test> lista) {
        this.listaTests = lista;
    }


    @NonNull
    @Override
    public AdaptadorTests.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_lista, viewGroup, false);
        return new AdaptadorTests.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTests.ViewHolder viewHolder, int i) {

            Test test = (Test) listaTests.get(i);

            viewHolder.txNumTest.setText(test.getId());
            if (test.getAprobado()){
                viewHolder.txAprobado.setText("Si"); }
            else{   viewHolder.txAprobado.setText("No"); }

            viewHolder.txNumAciertos.setText(test.getAciertos());
            viewHolder.txNumFallos.setText(test.getFallos());



     /*   if(lectura.getImagen() != null){
            Log.v(TAG, "En adaptador: uri no es null");
            viewHolder.imagenLectura.setImageURI(lectura.getImagen());
        }
    */
            viewHolder.bind(listaTests.get(i), (OnItemClickListener) listener);
        }



    public interface OnItemClickListener {
        void onItemClick(Test l);
    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        //  ImageView imagenLectura;
        TextView txNumTest;
        TextView txRealizado;
        TextView txAprobado;
        TextView txNumAciertos;
        TextView txNumFallos;
        TextView txFecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //   imagenLectura=itemView.findViewById(R.id.imagenLecturaRecycler);
            txNumTest=itemView.findViewById(R.id.txNumTest);
            txRealizado=itemView.findViewById(R.id.txTestRealizado);
            txAprobado=itemView.findViewById(R.id.txTestAprobado);
            txNumAciertos= (TextView) itemView.findViewById(R.id.txNumAciertos);
            txNumFallos=itemView.findViewById(R.id.txNumFallos);

        }

        public void bind(final Test t, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(t);
                }
            });
        }
    }


}
