package kr.ac.hallym.smart_portfolio.portfolio

import android.os.Parcel
import android.os.Parcelable


data class PortfolioModel (
    var title : String?="",
    var contents: String?=""
):Parcelable{
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

    companion object CREATOR : Parcelable.Creator<PortfolioModel> {
        override fun createFromParcel(parcel: Parcel): PortfolioModel {
            return PortfolioModel(parcel)
        }

        override fun newArray(size: Int): Array<PortfolioModel?> {
            return arrayOfNulls(size)
        }
    }

}