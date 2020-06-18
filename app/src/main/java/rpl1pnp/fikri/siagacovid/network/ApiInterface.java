package rpl1pnp.fikri.siagacovid.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rpl1pnp.fikri.siagacovid.config.Constant;
import rpl1pnp.fikri.siagacovid.model.Covid;
import rpl1pnp.fikri.siagacovid.model.LastKab;

public interface ApiInterface {

    @GET(Constant.HOME + Constant.LAST_DATA + Constant.LOCATION)
    Call<Covid> getData();

    @GET(Constant.HOME + Constant.ALL_DATA + Constant.LOCATION)
    Call<List<Covid>> getAllData();

    @GET(Constant.HOME + Constant.LAST_DATA_KAB + "/{path}")
    Call<LastKab> getLastKab(@Path("path") String path);
}
