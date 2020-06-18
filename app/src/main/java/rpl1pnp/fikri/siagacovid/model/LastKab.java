package rpl1pnp.fikri.siagacovid.model;

import com.google.gson.annotations.SerializedName;

public class LastKab {

    @SerializedName("results")
    private Kabupaten kabupaten;

    public Kabupaten getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(Kabupaten kabupaten) {
        this.kabupaten = kabupaten;
    }

    @Override
    public String toString() {
        return
                "LastKab{" +
                        "results = '" + kabupaten + '\'' +
                        "}";
    }
}