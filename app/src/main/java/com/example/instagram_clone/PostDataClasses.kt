package com.example.instagram_clone

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Photo(
    val data: String?,
    val id: Int,
    val post_id: Int
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
        parcel.writeInt(id)
        parcel.writeInt(post_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}

data class Like(
    val id: Int,
    val post_id: Int,
    val user_id: UserId?
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(UserId::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(post_id)
        parcel.writeParcelable(user_id, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Like> {
        override fun createFromParcel(parcel: Parcel): Like {
            return Like(parcel)
        }

        override fun newArray(size: Int): Array<Like?> {
            return arrayOfNulls(size)
        }
    }
}

data class AutherId(
    val first_name: String?,
    val last_name: String?,
    val profile_pic: String?,
    val username: String?
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(first_name)
        parcel.writeString(last_name)
        parcel.writeString(profile_pic)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AutherId> {
        override fun createFromParcel(parcel: Parcel): AutherId {
            return AutherId(parcel)
        }

        override fun newArray(size: Int): Array<AutherId?> {
            return arrayOfNulls(size)
        }
    }
}

data class UserId(
    val profile_pic: String?,
    val username: String?
)
    : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(profile_pic)
        parcel.writeString(username)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserId> {
        override fun createFromParcel(parcel: Parcel): UserId {
            return UserId(parcel)
        }

        override fun newArray(size: Int): Array<UserId?> {
            return arrayOfNulls(size)
        }
    }
}

data class Comment(
    val content: String?,
    val id: Int,
    val user_id: UserId?
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(UserId::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
        parcel.writeInt(id)
        parcel.writeParcelable(user_id, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comment> {
        override fun createFromParcel(parcel: Parcel): Comment {
            return Comment(parcel)
        }

        override fun newArray(size: Int): Array<Comment?> {
            return arrayOfNulls(size)
        }
    }
}


data class Post(
    @SerializedName("Auther_id")
    val autherId: AutherId? = null,
    val blog_id: Int? = null,
    val comments: List<Comment>? = null,
    val content: String? = "filler content",
    val id: Int? = null,
    val likes: List<Like>? = null,
    val photo: List<Photo>? = null,
    val title: String? = "filler title"
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(AutherId::class.java.classLoader),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createTypedArrayList(Comment),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createTypedArrayList(Like),
        parcel.createTypedArrayList(Photo),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(autherId, flags)
        parcel.writeValue(blog_id)
        parcel.writeTypedList(comments)
        parcel.writeString(content)
        parcel.writeValue(id)
        parcel.writeTypedList(likes)
        parcel.writeTypedList(photo)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }
}