package rpl1pnp.fikri.siagacovid.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rpl1pnp.fikri.siagacovid.config.Constant;

public class ApiService {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
