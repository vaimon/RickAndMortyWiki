package me.vaimon.rickandmortywiki.models

import android.os.Parcel
import android.os.Parcelable

data class SeriesCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val imageUrl: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(status)
        parcel.writeString(species)
        parcel.writeString(type)
        parcel.writeString(gender)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeriesCharacter> {
        override fun createFromParcel(parcel: Parcel): SeriesCharacter {
            return SeriesCharacter(parcel)
        }

        override fun newArray(size: Int): Array<SeriesCharacter?> {
            return arrayOfNulls(size)
        }
    }
}