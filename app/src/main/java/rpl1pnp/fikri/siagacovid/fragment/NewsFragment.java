package rpl1pnp.fikri.siagacovid.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import rpl1pnp.fikri.siagacovid.R;
import rpl1pnp.fikri.siagacovid.adapter.NewsFragmentAdapter;
import rpl1pnp.fikri.siagacovid.model.Berita;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private static final String DETAIL_NEWS = "NEWS";
    RecyclerView recyclerView;
    private ArrayList<Berita> dataBerita;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_news);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("berita");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
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

    private void showRecyclerCardView(ArrayList<Berita> dataBerita) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsFragmentAdapter newsAdapter = new NewsFragmentAdapter(getActivity(), dataBerita);
        recyclerView.setAdapter(newsAdapter);
        Log.d("Cek", "Cek masuk : " + dataBerita.get(0).getJudul());
    }
}
