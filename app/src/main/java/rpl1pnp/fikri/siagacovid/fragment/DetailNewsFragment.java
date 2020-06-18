package rpl1pnp.fikri.siagacovid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import rpl1pnp.fikri.siagacovid.R;
import rpl1pnp.fikri.siagacovid.model.Berita;

public class DetailNewsFragment extends Fragment {
    private static final String NEWS_PARAM = "BERITA";
    Berita berita;
    TextView title, content, createdBy;
    ImageView image;

    public DetailNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            berita = getArguments().getParcelable(NEWS_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.txt_news_title_frag);
        image = view.findViewById(R.id.img_news_frag);
        content = view.findViewById(R.id.txt_content_news_frag);
        createdBy = view.findViewById(R.id.txt_news_creator_frag_var);
        title.setText(berita.getJudul());
        Glide.with(view).load(berita.getGambar()).apply(new RequestOptions()).override(426, 240).into(image);
        content.setText(berita.getIsi());
        createdBy.setText(berita.getCreatedBy());
    }
}