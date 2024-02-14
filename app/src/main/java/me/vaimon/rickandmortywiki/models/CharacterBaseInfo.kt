package me.vaimon.rickandmortywiki.models

import android.os.Parcel
import android.os.Parcelable

data class CharacterBaseInfo(val id: Int, val name: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterBaseInfo> {
        override fun createFromParcel(parcel: Parcel): CharacterBaseInfo {
            return CharacterBaseInfo(parcel)
        }

        override fun newArray(size: Int): Array<CharacterBaseInfo?> {
            return arrayOfNulls(size)
        }
    }
}