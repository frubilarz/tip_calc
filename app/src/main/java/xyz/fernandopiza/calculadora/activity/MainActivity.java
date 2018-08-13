package xyz.fernandopiza.calculadora.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.fernandopiza.calculadora.R;
import xyz.fernandopiza.calculadora.fragmentos.HistoriaPropinaFragmentoListener;
import xyz.fernandopiza.calculadora.fragmentos.HistoricoPropinasListFragment;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.inputBill)
    EditText inputPropina;
    @BindView(R.id.btnSubmit)
    Button btnCalcular;
    @BindView(R.id.inputPorcentaje)
    EditText inputPorcentaje;
    @BindView(R.id.btnDecremento)
    Button btnDecremento;
    @BindView(R.id.btnIncremento)
    Button btnIncremento;
    @BindView(R.id.btnClear)
    Button btnClear;
    @BindView(R.id.textPropina)
    TextView textPropina;

    private HistoriaPropinaFragmentoListener fragmentoListener;

    private final static int PROPINA_CAMBIO = 1;
    private final static int DEFAULT_PORCENTAJE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        HistoricoPropinasListFragment fragment = (HistoricoPropinasListFragment) getSupportFragmentManager().
                findFragmentById(R.id.fragmentoLista);
        fragment.setRetainInstance(true);
        fragmentoListener = (HistoriaPropinaFragmentoListener) fragment;
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickAceptar() {
//        Log.e(getLocalClassName(), "hubo un click");
        ocultarTeclado();
        String inputTotalString = inputPropina.getText().toString().trim();
        if (!inputTotalString.isEmpty()) {
            double total = Double.parseDouble(inputTotalString);
            int porcentaje = getPorcentaje();
            Double propina = total * (porcentaje / 100d);

            String propinaString = String.format(getString(R.string.global_message_tip), propina);
            fragmentoListener.action(propinaString);
            textPropina.setVisibility(View.VISIBLE);
            textPropina.setText(propinaString);

        }
    }

    public int getPorcentaje() {
        int porcentaje = DEFAULT_PORCENTAJE;
        String porcentajeTeclado = inputPorcentaje.getText().toString().trim();
        if (!porcentajeTeclado.isEmpty()) {
            porcentaje = Integer.parseInt(porcentajeTeclado);
        } else {
            inputPorcentaje.setText(String.valueOf(DEFAULT_PORCENTAJE));
        }
        return porcentaje;
    }

    @OnClick(R.id.btnIncremento)
    public void manejoClickIncremeto() {
        ocultarTeclado();
        manejoCambioPropina(PROPINA_CAMBIO);
    }

    @OnClick(R.id.btnDecremento)
    public void manejoClickDecremento() {
        ocultarTeclado();
        manejoCambioPropina(-PROPINA_CAMBIO);
    }

    private void manejoCambioPropina(int i) {
        int porcentaje = getPorcentaje();
        porcentaje += i;
        if (porcentaje > 0) {
            inputPorcentaje.setText(String.valueOf(porcentaje));
        }
    }

    private void ocultarTeclado() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {
            Log.e(getLocalClassName(), Log.getStackTraceString(e));
        }
    }


}

