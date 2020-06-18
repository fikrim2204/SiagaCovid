package rpl1pnp.fikri.siagacovid.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.MultiplePulseRing;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.TextSliderView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rpl1pnp.fikri.siagacovid.R;
import rpl1pnp.fikri.siagacovid.adapter.NewsAdapter;
import rpl1pnp.fikri.siagacovid.model.Berita;
import rpl1pnp.fikri.siagacovid.model.Covid;
import rpl1pnp.fikri.siagacovid.network.ApiInterface;
import rpl1pnp.fikri.siagacovid.network.ApiService;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    SliderLayout sliderLayout;
    RecyclerView recyclerViewBerita;
    NavController navController;
    TextView txtOdp, txtPdp, txtPositif, txtSembuh, txtMeninggal, txtWaktu, txtBerita,
            txtLinkSelengkapnya;
    Button historyButton, districtsButton, hotlineButton, hospitalButton;
    private ArrayList<Berita> dataBerita;
    private ProgressBar progressBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        navController = Navigation.findNavController(getActivity(), R.id.container);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progress_bar);
        txtOdp = view.findViewById(R.id.txt_odp_var);
        txtPdp = view.findViewById(R.id.txt_pdp_var);
        txtPositif = view.findViewById(R.id.txt_positif_var);
        txtSembuh = view.findViewById(R.id.txt_sembuh_var);
        txtMeninggal = view.findViewById(R.id.txt_meninggal_var);
        txtWaktu = view.findViewById(R.id.txt_var_date);
        txtBerita = view.findViewById(R.id.txtBerita);
        sliderLayout = view.findViewById(R.id.slider);
        displayImage();
        hospitalButton = view.findViewById(R.id.btn_map_hospital);
        hospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callHospital();
            }
        });
        txtLinkSelengkapnya = view.findViewById(R.id.txtLinkSelengkapnya);
        txtLinkSelengkapnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_homeFragment_to_newsFragment);
            }
        });
        historyButton = view.findViewById(R.id.btn_history);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_homeFragment_to_historyFragment);
            }
        });
        districtsButton = view.findViewById(R.id.btn_districts);
        districtsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_homeFragment_to_districtsFragment);
            }
        });
        hotlineButton = view.findViewById(R.id.btn_hotline);
        hotlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callHotline();
            }
        });

        Sprite sprite = new MultiplePulseRing();
        progressBar.setIndeterminateDrawable(sprite);
        showLoading(true);
        updateData();
        readAndWriteFirebase();
        recyclerViewBerita = view.findViewById(R.id.rv_news);

    }

    private void readAndWriteFirebase() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("berita");

        // Read from the database
        myRef.orderByKey().limitToFirst(3).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataBerita = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Berita berita = snapshot.getValue(Berita.class);
                    dataBerita.add(berita);
                    Log.d("List", "Data " + dataBerita.get(0).getJudul());
                }
                showRecyclerCardView(dataBerita);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Firebase", "Failed to read value.", error.toException());
                Toast.makeText(getActivity(), "Data Gagal Dimuat", Toast.LENGTH_LONG).show();
                Log.e("MyListActivity", error.getDetails() + " " + error.getMessage());
            }
        });
    }

    private void updateData() {
        ApiInterface server = ApiService.getRetrofit().create(ApiInterface.class);
        Call<Covid> response = server.getData();
        response.enqueue(new Callback<Covid>() {
            @Override
            public void onResponse(Call<Covid> call, Response<Covid> response) {
                if (response.isSuccessful()) {
                    Covid covid = new Covid();
                    covid.setOdpDalamPemantauan(response.body().getOdpDalamPemantauan());
                    covid.setPdpIsolasidirumah(response.body().getPdpIsolasidirumah());
                    covid.setPdpMasihDirawat(response.body().getPdpMasihDirawat());
                    covid.setPdp(response.body().getPdp());
                    covid.setPositif(response.body().getPositif());
                    covid.setCovidSembuh(response.body().getCovidSembuh());
                    covid.setCovidMeninggal(response.body().getCovidMeninggal());
                    covid.setWaktu(response.body().getWaktu());
                    Log.d("Message", "Success" + covid.getPositif());
                    loadData(covid);
                    showLoading(false);
                } else {
                    Toast.makeText(getActivity(), "No Response, " +
                            response.errorBody(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Covid> call, Throwable t) {
                Toast.makeText(getActivity(), "Error, " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showRecyclerCardView(ArrayList<Berita> dataBerita) {
        recyclerViewBerita.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), dataBerita);
        recyclerViewBerita.setAdapter(newsAdapter);
        Log.d("Cek", "Cek masuk : " + dataBerita.get(0).getJudul());
    }

    private void loadData(Covid covid) {
        txtOdp.setText(covid.getOdpDalamPemantauan());
        txtPdp.setText(covid.getPdp());
        txtPositif.setText(covid.getPositif());
        txtSembuh.setText(covid.getCovidSembuh());
        txtMeninggal.setText(covid.getCovidMeninggal());
        txtWaktu.setText(covid.getWaktu());
    }

    private void displayImage() {
        List<Integer> list = new ArrayList<>();

        list.add(R.drawable.ss1);
        list.add(R.drawable.ss2);

        for (int i = 0; i < list.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description("")
                    .image(list.get(i));
            sliderLayout.addSlider(textSliderView);

            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);

            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setDuration(4000);
            sliderLayout.stopCyclingWhenTouch(false);
        }
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void callHotline() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builderSingle.setIcon(R.drawable.ic_baseline_local_hospital_24);
        builderSingle.setTitle(R.string.select_hotline);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item);
        arrayAdapter.add("Pusat");
        arrayAdapter.add("Khusus Kesehatan");
        arrayAdapter.add("Dinsos");
        arrayAdapter.add("Pusdalop");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    String number = "tel:199";
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(number)));
                } else if (which == 1) {
                    String number = "tel:081363478384";
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(number)));
                } else if (which == 2) {
                    String number = "tel:081363144050";
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(number)));
                } else if (which == 3) {
                    String number = "tel:075171394";
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(number)));
                }
            }
        });
        builderSingle.show();
    }

    private void callHospital() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builderSingle.setIcon(R.drawable.ic_baseline_local_hospital_24);
        builderSingle.setTitle(R.string.select_hospital);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item);
        arrayAdapter.add("RSUP Dr. M. Djamil");
        arrayAdapter.add("RS. Unand");
        arrayAdapter.add("RSUD Dr. Achmad Mochtar Kota Bukittinggi");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    Uri location = Uri.parse("geo:0,0?q=Jl.+Perintis+Kemerdekaan+No.22, Sawahan+Tim., Kec.+Padang+Tim., Kota+Padang, Sumatera+Barat+25000");

                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                    startActivity(mapIntent);

                } else if (which == 1) {
                    Uri location = Uri.parse("geo:-0.920956,100.4569799,21z?q=Komp+Kampus+Unand+Jalan+Universitas+Andalas, Limau+Manis, Kec.+Pauh, Kota+Padang, Sumatera+Barat+25176");

                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                    startActivity(mapIntent);
                } else if (which == 2) {
                    Uri location = Uri.parse("geo:0,0?q=Jl.+Dr.+Abdul+Rivai+No.1, Bukit+Apit+Puhun, Kec.+Guguk+Panjang, Kota+Bukittinggi, Sumatera+Barat+26136");

                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                    startActivity(mapIntent);
                }
            }
        });
        builderSingle.show();
    }
}
