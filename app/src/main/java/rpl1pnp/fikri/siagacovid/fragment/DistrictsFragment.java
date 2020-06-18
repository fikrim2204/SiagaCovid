package rpl1pnp.fikri.siagacovid.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.MultiplePulseRing;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rpl1pnp.fikri.siagacovid.R;
import rpl1pnp.fikri.siagacovid.model.Kabupaten;
import rpl1pnp.fikri.siagacovid.model.LastKab;
import rpl1pnp.fikri.siagacovid.network.ApiInterface;
import rpl1pnp.fikri.siagacovid.network.ApiService;


/**
 * A simple {@link Fragment} subclass.
 */
public class DistrictsFragment extends Fragment {
    private static Retrofit retrofit = null;
    TextView txtOdp, txtPdp, txtPositif, txtSembuh, txtMeninggal, txtWaktu;
    NiceSpinner niceSpinner;
    private ProgressBar progressBar;

    public DistrictsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_districts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress_bar_kab);
        txtOdp = view.findViewById(R.id.txt_odp_var_kab);
        txtPdp = view.findViewById(R.id.txt_pdp_var_kab);
        txtPositif = view.findViewById(R.id.txt_positif_var_kab);
        txtSembuh = view.findViewById(R.id.txt_sembuh_var_kab);
        txtMeninggal = view.findViewById(R.id.txt_meninggal_var_kab);
        txtWaktu = view.findViewById(R.id.txt_var_date_kab);
        niceSpinner = view.findViewById(R.id.nice_spinner);
        Sprite sprite = new MultiplePulseRing();
        progressBar.setIndeterminateDrawable(sprite);
        api("bukittinggi");
        showLoading(true);
        niceSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                api(parent.getSelectedItem().toString());
            }
        });
    }

    private void api(String path) {
        ApiInterface server = ApiService.getRetrofit().create(ApiInterface.class);
        Call<LastKab> response = server.getLastKab(path);
        response.enqueue(new Callback<LastKab>() {
            @Override
            public void onResponse(Call<LastKab> call, Response<LastKab> response) {
                if (response.isSuccessful()) {
                    Kabupaten respon = response.body().getKabupaten();
                    Log.d("Message", "Success, Covid positif : " + respon.getPdpSuspec());
                    loadData(respon);
                    showLoading(false);
                } else {
                    Log.e("Message", "Failed!, " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<LastKab> call, Throwable t) {
                Toast.makeText(getActivity(), "Error, " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadData(Kabupaten kab) {
        txtOdp.setText(kab.getOdpDalamPemantauan());
        txtPdp.setText(kab.getPdp());
        txtPositif.setText(kab.getPositif());
        txtSembuh.setText(kab.getCovidSembuh());
        txtMeninggal.setText(kab.getCovidMeninggal());
        txtWaktu.setText(kab.getTglUpdate());
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
