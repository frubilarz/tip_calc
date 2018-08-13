package xyz.fernandopiza.calculadora;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.inputBill)
    EditText inputPropina;
    @BindView(R.id.btnSubmit)
    Button btnAceptar;
    @BindView(R.id.inputPorcentaje)
    EditText inputPorcentaje;
    @BindView(R.id.btnDecremento)
    Button btnDecremento;
    @BindView(R.id.btnIncremento)
    Button btnIncremento;
    @BindView(R.id.btnClear)
    Button btnClear;

    private final static int PROPINA_CAMBIO = 1;
    private final static int DEFAULT_PORCENTAJE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickAceptar() {
        Log.e(getLocalClassName(), "hubo un click");
        ocultarTeclado();
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

