package soko.ekibun.videoplayer.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.android.exoplayer2.offline.StreamKey
import soko.ekibun.videoplayer.model.VideoProvider

data class VideoCache(
    val episode: VideoEpisode,
    val type: String,
    val streamKeys: List<StreamKey>,
    val video: VideoProvider.VideoRequest,
    var contentLength: Long = 0L,
    var bytesDownloaded: Long = 0L,
    var percentDownloaded: Float = 0f
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readParcelable<VideoEpisode>(VideoEpisode::class.java.classLoader),
        source.readString(),
        ArrayList(),
        VideoProvider.VideoRequest(""),
        source.readLong(),
        source.readLong(),
        source.readFloat()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(episode, 0)
        writeString(type)
        writeLong(contentLength)
        writeLong(bytesDownloaded)
        writeFloat(percentDownloaded)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<VideoCache> = object : Parcelable.Creator<VideoCache> {
            override fun createFromParcel(source: Parcel): VideoCache = VideoCache(source)
            override fun newArray(size: Int): Array<VideoCache?> = arrayOfNulls(size)
        }
    }
}