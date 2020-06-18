package rpl1pnp.fikri.siagacovid.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import rpl1pnp.fikri.siagacovid.R;
import rpl1pnp.fikri.siagacovid.model.Berita;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String NEWS_PARAM = "BERITA";
    private Context context;
    private ArrayList<Berita> beritaArrayList;

    public NewsAdapter(Context context, ArrayList<Berita> beritaArrayList) {
        this.context = context;
        this.beritaArrayList = beritaArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Berita berita = beritaArrayList.get(position);

        Glide.with(holder.itemView.getContext()).load(berita.getGambar()).apply(new RequestOptions()).override(426, 240).into(holder.ivNews);
        holder.txtJudul.setText(berita.getJudul());
        Log.d("LOG", "isi holder " + beritaArrayList.size());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("BERITA", berita);
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailNewsFragment, bundle).onClick(holder.cardView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beritaArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivNews;
        TextView txtJudul;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNews = itemView.findViewById(R.id.iv_news);
            txtJudul = itemView.findViewById(R.id.txt_news_title);
            cardView = itemView.findViewById(R.id.cv_news);
        }
    }
}
