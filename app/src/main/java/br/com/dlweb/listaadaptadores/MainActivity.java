package br.com.dlweb.listaadaptadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerSeries;
    private RecyclerView recyclerViewTimes;
    private Button btnGetSelected;
    private TimeAdapter adapterTimes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerSeries = findViewById(R.id.spinnerSeries);
        btnGetSelected = findViewById(R.id.buttonVisualizar);

        //Criar o array de dados estáticos
        String[] series = new String[] {
                "Série A",
                "Série B",
                "Série C"
        };

        //Criar adaptador
        ArrayAdapter<String> spinnerAdapter = new
                ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                series);

        //Setar o adaptador
        spinnerSeries.setAdapter(spinnerAdapter);

        recyclerViewTimes = findViewById(R.id.recyclerViewTimes);
        //Ao selecionar um spinner
        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                recyclerViewTimes.setLayoutManager(manager);
                recyclerViewTimes.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
                recyclerViewTimes.setHasFixedSize(true);
                switch (position) {
                    case 0: //Série A
                        //Criar o array de dados estáticos
                        String[] timesSerieA = new String[] {
                                "Botafogo",
                                "Corinthians",
                                "Flamengo",
                                "Palmeiras"
                        };
                        adapterTimes = new TimeAdapter(timesSerieA);
                        recyclerViewTimes.setAdapter(adapterTimes);
                        break;
                    case 1: //Série B
                        //Criar o array de dados estáticos
                        String[] timesSerieB = new String[] {
                                "Brasil-RS",
                                "Chapecoense",
                                "Sport",
                                "Vitória"
                        };
                        adapterTimes = new TimeAdapter(timesSerieB);
                        recyclerViewTimes.setAdapter(adapterTimes);
                        break;
                    default: //Série C
                        //Criar o array de dados estáticos
                        String[] timesSerieC = new String[] {
                                "Brusque",
                                "Figueirense",
                                "Manaus",
                                "Vitória"
                        };
                        adapterTimes = new TimeAdapter(timesSerieC);
                        recyclerViewTimes.setAdapter(adapterTimes);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeSerie = spinnerSeries.getSelectedItem().toString();
                String nomeTime = "nenhum";
                if (adapterTimes.getSelected() != null) {
                    nomeTime = adapterTimes.getSelected();
                }
                Toast.makeText(getApplicationContext(), "Série selecionada: "  + nomeSerie + " - Time selecionado: " + nomeTime, Toast.LENGTH_LONG).show();
            }
        });
    }
}