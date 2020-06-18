package rpl1pnp.fikri.siagacovid.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.MultiplePulseRing;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rpl1pnp.fikri.siagacovid.R;
import rpl1pnp.fikri.siagacovid.model.Covid;
import rpl1pnp.fikri.siagacovid.network.ApiInterface;
import rpl1pnp.fikri.siagacovid.network.ApiService;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    NavController navController;
    private List<Covid> covidList = new ArrayList<>();
    private LineChart lineChart;
    private ProgressBar progressBar;
    private TableLayout tableLayout;
    private TextView odp, pdp, positif, sembuh, meninggal, tanggal;
    private TableRow row;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        showChart();
        navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.container);
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    private void showChart() {
        ApiInterface server = ApiService.getRetrofit().create(ApiInterface.class);
        final Call<List<Covid>> listCall = server.getAllData();
        listCall.enqueue(new Callback<List<Covid>>() {
            @Override
            public void onResponse(@NotNull Call<List<Covid>> call, @NotNull Response<List<Covid>> response) {
                if (response.isSuccessful()) {
                    covidList = response.body();
                    chartList(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Covid>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chartList(List<Covid> covid) {
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);
        lineChart.setPinchZoom(false);
        lineChart.setBackgroundColor(Color.WHITE);
        lineChart.getDescription().setEnabled(false);
        lineChart.animateX(1500);
        List<Entry> positifValues = new ArrayList<>();
        List<Entry> deathValues = new ArrayList<>();
        List<Entry> recoveryValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();
        int time = 0;

        for (int i = covidList.size() - 7; i < covidList.size(); i++) {
            String[] parts = covidList.get(i).getWaktu().split(" ");
            String[] parts1 = parts[0].split("-");
            positifValues.add(new Entry(time, Float.parseFloat(covid.get(i).getPositif())));
            recoveryValues.add(new Entry(time, Float.parseFloat(covid.get(i).getCovidSembuh())));
            deathValues.add(new Entry(time, Float.parseFloat(covid.get(i).getCovidMeninggal())));
            xValues.add(parts1[2]);
            time++;

            LineDataSet set1 = new LineDataSet(positifValues, "Positif");
            LineDataSet set2 = new LineDataSet(recoveryValues, "Sembuh");
            LineDataSet set3 = new LineDataSet(deathValues, "Meninggal");
            set1.setLineWidth(2.5f);
            set1.setCircleRadius(4.5f);
            set1.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
            set1.setHighLightColor(Color.rgb(244, 177, 117));
            set1.setFillColor(Color.GREEN);
            set1.setColor(Color.GREEN);
            set1.setDrawFilled(true);
            set1.setDrawValues(true);
            set1.setFillAlpha(110);

            set2.setLineWidth(2.5f);
            set2.setCircleRadius(4.5f);
            set2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[3]);
            set2.setHighLightColor(Color.rgb(244, 117, 177));
            set2.setFillColor(Color.BLUE);
            set2.setColor(Color.BLUE);
            set2.setDrawFilled(true);
            set2.setDrawValues(true);
            set2.setFillAlpha(110);

            set3.setLineWidth(2.5f);
            set3.setCircleRadius(4.5f);
            set3.setColor(Color.RED);
            set3.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[4]);
            set3.setHighLightColor(Color.rgb(244, 117, 117));
            set3.setFillColor(Color.RED);
            set3.setDrawFilled(true);
            set3.setDrawValues(true);
            set3.setFillAlpha(110);

            ArrayList<ILineDataSet> sets = new ArrayList<>();
            sets.add(set1);
            sets.add(set2);
            sets.add(set3);
            LineData data = new LineData(sets);
            lineChart.setData(data);

            XAxis xAxis = lineChart.getXAxis();
            xAxis.setValueFormatter(new MyAxisFormat(xValues));
            xAxis.setPosition(XAxis.XAxisPosition.TOP);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lineChart = view.findViewById(R.id.chart_history);
        progressBar = view.findViewById(R.id.progress_bar_history);
        tableLayout = view.findViewById(R.id.tableLayoutid);

        Sprite sprite = new MultiplePulseRing();
        showLoading(true);
        ApiInterface server = ApiService.getRetrofit().create(ApiInterface.class);
        Call<List<Covid>> response = server.getAllData();
        response.enqueue(new Callback<List<Covid>>() {
            @Override
            public void onResponse(@NotNull Call<List<Covid>> call, @NotNull Response<List<Covid>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    loadData(response.body());
                    showLoading(false);
                } else {
                    Toast.makeText(getActivity(), "No Response!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Covid>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData(List<Covid> covid) {
        for (int i = 0; i < covid.size(); i++) {
            row = (TableRow) getLayoutInflater().inflate(R.layout.layout_row, null);
            odp = row.findViewById(R.id.odp_table);
            pdp = row.findViewById(R.id.pdp_table);
            positif = row.findViewById(R.id.positif_table);
            sembuh = row.findViewById(R.id.sembuh_table);
            meninggal = row.findViewById(R.id.meninggal_table);
            tanggal = row.findViewById(R.id.tgl_table);
            String[] tTanggal = covid.get(i).getWaktu().split(" ");
            tanggal.setText(tTanggal[0]);
            odp.setText(covid.get(i).getTotalOdp());
            pdp.setText(covid.get(i).getPdp());
            positif.setText(covid.get(i).getPositif());
            sembuh.setText(covid.get(i).getCovidSembuh());
            meninggal.setText(covid.get(i).getCovidMeninggal());
            tableLayout.addView(row);
        }
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private static class MyAxisFormat extends ValueFormatter {
        ArrayList<String> mValues;

        public MyAxisFormat(ArrayList<String> mValues) {
            this.mValues = mValues;
        }

        @Override
        public String getFormattedValue(float value) {
            return mValues.get((int) value);
        }
    }
}
