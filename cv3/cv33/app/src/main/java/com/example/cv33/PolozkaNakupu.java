package com.example.cv33;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

public class PolozkaNakupu implements Parcelable {
    String nazev;
    int pocet;
    int cena;
    boolean splneno;

    public PolozkaNakupu(String nazev, int pocet, int cena, boolean splneno){
        this.nazev = nazev;
        this.pocet = pocet;
        this.cena = cena;
        this.splneno = splneno;
    }

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(nazev);
        out.writeInt(pocet);
        out.writeInt(cena);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            out.writeBoolean(splneno);
        }
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<PolozkaNakupu> CREATOR = new Parcelable.Creator<PolozkaNakupu>() {
        public PolozkaNakupu createFromParcel(Parcel in) {
            return new PolozkaNakupu(in);
        }

        public PolozkaNakupu[] newArray(int size) {
            return new PolozkaNakupu[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private PolozkaNakupu(Parcel in) {
        nazev = in.readString();
        pocet = in.readInt();
        cena = in.readInt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            splneno = in.readBoolean();
        }
    }
}
