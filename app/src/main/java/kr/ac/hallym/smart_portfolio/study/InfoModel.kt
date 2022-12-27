package kr.ac.hallym.smart_portfolio.study

import android.os.Parcel
import android.os.Parcelable


data class InfoModel(
    var title: String?="",
    var contents :String?=""
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(contents)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InfoModel> {
        override fun createFromParcel(parcel: Parcel): InfoModel {
            return InfoModel(parcel)
        }

        override fun newArray(size: Int): Array<InfoModel?> {
            return arrayOfNulls(size)
        }
    }

}

