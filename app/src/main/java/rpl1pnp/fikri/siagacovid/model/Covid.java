package rpl1pnp.fikri.siagacovid.model;

import com.google.gson.annotations.SerializedName;

public class Covid {

    @SerializedName("covid_meninggal")
    private String covidMeninggal;

    @SerializedName("odp_dalam_pemantauan")
    private String odpDalamPemantauan;

    @SerializedName("pdp_masih_dirawat")
    private String pdpMasihDirawat;

    @SerializedName("covid_dirawat")
    private String covidDirawat;

    @SerializedName("covid_isolasi_dirumah")
    private String covidIsolasiDirumah;

    @SerializedName("odp_selesai_pemantauan")
    private String odpSelesaiPemantauan;

    @SerializedName("positif")
    private String positif;

    @SerializedName("kode")
    private String kode;

    @SerializedName("pdp_isolasidirumah")
    private String pdpIsolasidirumah;

    @SerializedName("waktu")
    private String waktu;

    @SerializedName("covid_sembuh")
    private String covidSembuh;

    @SerializedName("pdp_meninggal")
    private String pdpMeninggal;

    @SerializedName("id")
    private String id;

    @SerializedName("total_odp")
    private String totalOdp;

    @SerializedName("pdp")
    private String pdp;

    @SerializedName("pdp_pulangdan_sehat")
    private String pdpPulangdanSehat;

    public String getCovidMeninggal() {
        return covidMeninggal;
    }

    public void setCovidMeninggal(String covidMeninggal) {
        this.covidMeninggal = covidMeninggal;
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

    public String getOdpSelesaiPemantauan() {
        return odpSelesaiPemantauan;
    }

    public void setOdpSelesaiPemantauan(String odpSelesaiPemantauan) {
        this.odpSelesaiPemantauan = odpSelesaiPemantauan;
    }

    public String getPositif() {
        return positif;
    }

    public void setPositif(String positif) {
        this.positif = positif;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPdpIsolasidirumah() {
        return pdpIsolasidirumah;
    }

    public void setPdpIsolasidirumah(String pdpIsolasidirumah) {
        this.pdpIsolasidirumah = pdpIsolasidirumah;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getCovidSembuh() {
        return covidSembuh;
    }

    public void setCovidSembuh(String covidSembuh) {
        this.covidSembuh = covidSembuh;
    }

    public String getPdpMeninggal() {
        return pdpMeninggal;
    }

    public void setPdpMeninggal(String pdpMeninggal) {
        this.pdpMeninggal = pdpMeninggal;
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

    @Override
    public String toString() {
        return
                "Covid{" +
                        "covid_meninggal = '" + covidMeninggal + '\'' +
                        ",odp_dalam_pemantauan = '" + odpDalamPemantauan + '\'' +
                        ",pdp_masih_dirawat = '" + pdpMasihDirawat + '\'' +
                        ",covid_dirawat = '" + covidDirawat + '\'' +
                        ",covid_isolasi_dirumah = '" + covidIsolasiDirumah + '\'' +
                        ",odp_selesai_pemantauan = '" + odpSelesaiPemantauan + '\'' +
                        ",positif = '" + positif + '\'' +
                        ",kode = '" + kode + '\'' +
                        ",pdp_isolasidirumah = '" + pdpIsolasidirumah + '\'' +
                        ",waktu = '" + waktu + '\'' +
                        ",covid_sembuh = '" + covidSembuh + '\'' +
                        ",pdp_meninggal = '" + pdpMeninggal + '\'' +
                        ",id = '" + id + '\'' +
                        ",total_odp = '" + totalOdp + '\'' +
                        ",pdp = '" + pdp + '\'' +
                        ",pdp_pulangdan_sehat = '" + pdpPulangdanSehat + '\'' +
                        "}";
    }
}