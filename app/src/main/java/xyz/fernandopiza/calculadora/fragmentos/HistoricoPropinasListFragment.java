package xyz.fernandopiza.calculadora.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import xyz.fernandopiza.calculadora.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricoPropinasListFragment extends Fragment implements HistoriaPropinaFragmentoListener {


    public HistoricoPropinasListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historico_propinas_list, container, false);
    }

    @Override
    public void action(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }
}
