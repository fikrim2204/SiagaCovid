package rpl1pnp.fikri.siagacovid.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Berita implements Parcelable {
    public static final Parcelable.Creator<Berita> CREATOR = new Parcelable.Creator<Berita>() {
        @Override
        public Berita createFromParcel(Parcel source) {
            return new Berita(source);
        }

        @Override
        public Berita[] newArray(int size) {
            return new Berita[size];
        }
    };
    String judul, isi, gambar, createdBy;

    public Berita(String judul, String isi, String gambar, String createdBy) {
        this.judul = judul;
        this.isi = isi;
        this.gambar = gambar;
        this.createdBy = createdBy;
    }

    public Berita() {
    }

    protected Berita(Parcel in) {
        this.judul = in.readString();
        this.isi = in.readString();
        this.gambar = in.readString();
        this.createdBy = in.readString();
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeString(this.isi);
        dest.writeString(this.gambar);
        dest.writeString(this.createdBy);
    }
}
