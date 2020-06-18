package rpl1pnp.fikri.siagacovid.model;

import com.google.gson.annotations.SerializedName;

public class Kabupaten {

    @SerializedName("kabupaten_kota")
    private String kabupatenKota;

    @SerializedName("covid_meninggal")
    private String covidMeninggal;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("odp_dalam_pemantauan")
    private String odpDalamPemantauan;

    @SerializedName("pdp_masih_dirawat")
    private String pdpMasihDirawat;

    @SerializedName("covid_dirawat")
    private String covidDirawat;

    @SerializedName("covid_isolasi_dirumah")
    private String covidIsolasiDirumah;

    @SerializedName("odp_selesai")
    private String odpSelesai;

    @SerializedName("tgl_update")
    private String tglUpdate;

    @SerializedName("pdp_suspec")
    private String pdpSuspec;

    @SerializedName("positif")
    private String positif;

    @SerializedName("kode_kota")
    private String kodeKota;

    @SerializedName("covid_sembuh")
    private String covidSembuh;

    @SerializedName("id")
    private String id;

    @SerializedName("total_odp")
    private String totalOdp;

    @SerializedName("pdp")
    private String pdp;

    @SerializedName("pdp_pulangdan_sehat")
    private String pdpPulangdanSehat;

    @SerializedName("longitude")
    private String longitude;

    public String getKabupatenKota() {
        return kabupatenKota;
    }

    public void setKabupatenKota(String kabupatenKota) {
        this.kabupatenKota = kabupatenKota;
    }

    public String getCovidMeninggal() {
        return covidMeninggal;
    }

    public void setCovidMeninggal(String covidMeninggal) {
        this.covidMeninggal = covidMeninggal;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getOdpDalamPemantauan() {
        return odpDalamPemantauan;
    }

    public void setOdpDalamPemantauan(String odpDalamPemantauan) {
        this.odpDalamPemantauan = odpDalamPemantauan;
    }

    public String getPdpMasihDirawat() {
        return pdpMasihDirawat;
    }

    public void setPdpMasihDirawat(String pdpMasihDirawat) {
        this.pdpMasihDirawat = pdpMasihDirawat;
    }

    public String getCovidDirawat() {
        return covidDirawat;
    }

    public void setCovidDirawat(String covidDirawat) {
        this.covidDirawat = covidDirawat;
    }

    public String getCovidIsolasiDirumah() {
        return covidIsolasiDirumah;
    }

    public void setCovidIsolasiDirumah(String covidIsolasiDirumah) {
        this.covidIsolasiDirumah = covidIsolasiDirumah;
    }

    public String getOdpSelesai() {
        return odpSelesai;
    }

    public void setOdpSelesai(String odpSelesai) {
        this.odpSelesai = odpSelesai;
    }

    public String getTglUpdate() {
        return tglUpdate;
    }

    public void setTglUpdate(String tglUpdate) {
        this.tglUpdate = tglUpdate;
    }

    public String getPdpSuspec() {
        return pdpSuspec;
    }

    public void setPdpSuspec(String pdpSuspec) {
        this.pdpSuspec = pdpSuspec;
    }

    public String getPositif() {
        return positif;
    }

    public void setPositif(String positif) {
        this.positif = positif;
    }

    public String getKodeKota() {
        return kodeKota;
    }

    public void setKodeKota(String kodeKota) {
        this.kodeKota = kodeKota;
    }

    public String getCovidSembuh() {
        return covidSembuh;
    }

    public void setCovidSembuh(String covidSembuh) {
        this.covidSembuh = covidSembuh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotalOdp() {
        return totalOdp;
    }

    public void setTotalOdp(String totalOdp) {
        this.totalOdp = totalOdp;
    }

    public String getPdp() {
        return pdp;
    }

    public void setPdp(String pdp) {
        this.pdp = pdp;
    }

    public String getPdpPulangdanSehat() {
        return pdpPulangdanSehat;
    }

    public void setPdpPulangdanSehat(String pdpPulangdanSehat) {
        this.pdpPulangdanSehat = pdpPulangdanSehat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return
                "Results{" +
                        "kabupaten_kota = '" + kabupatenKota + '\'' +
                        ",covid_meninggal = '" + covidMeninggal + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",odp_dalam_pemantauan = '" + odpDalamPemantauan + '\'' +
                        ",pdp_masih_dirawat = '" + pdpMasihDirawat + '\'' +
                        ",covid_dirawat = '" + covidDirawat + '\'' +
                        ",covid_isolasi_dirumah = '" + covidIsolasiDirumah + '\'' +
                        ",odp_selesai = '" + odpSelesai + '\'' +
                        ",tgl_update = '" + tglUpdate + '\'' +
                        ",pdp_suspec = '" + pdpSuspec + '\'' +
                        ",positif = '" + positif + '\'' +
                        ",kode_kota = '" + kodeKota + '\'' +
                        ",covid_sembuh = '" + covidSembuh + '\'' +
                        ",id = '" + id + '\'' +
                        ",total_odp = '" + totalOdp + '\'' +
                        ",pdp = '" + pdp + '\'' +
                        ",pdp_pulangdan_sehat = '" + pdpPulangdanSehat + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        "}";
    }
}