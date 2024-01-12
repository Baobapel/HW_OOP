package hw_oop

data class Post(
    val id: Int?,
    val text: String? = "Text",
    val ownerId: Int? = 1,
    val fromId: Int? = 1,
    val createdBy: Int? = 1,
    val date: Long? = 1,
    val replyOwnerId: Int? = 1,
    val replyPostId: Int? = 1,
    var friendsOnly: Boolean = true,
    val comments: Comments? = Comments(0, false, false, false, false),
    val attachments: List<Attachment>? = emptyList()

) {
    data class Comments(
        val count: Int,
        val canPost: Boolean,
        val groupsCanPost: Boolean,
        val canClose: Boolean,
        val canOpen: Boolean
    )
}

sealed class Attachment (val type: String)


data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int
)

data class AudioAttachment(val audio: Audio) : Attachment("audio")

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String,
    val duration: Int
)

data class VideoAttachment(val video: Video) : Attachment("video")


data class Photo(
    val id: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String
)

data class PhotoAttachment(
    val photo: Photo,

) : Attachment ("photo")

data class Sticker(
    val productId: Int,
    val stickerId: Int,
    val isAllowed: Boolean,
    val animationUrl: String
)

data class StickerAttachment(val sticker: Sticker) : Attachment ("sticker")

data class File(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val ext: String
)

data class FileAttachment(val file: File) : Attachment ("file")

object WallService {
    private var posts = emptyArray<Post>()
    private var currentId = 0

    fun clear() {
        posts = emptyArray<Post>()
        currentId = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = ++currentId)
        return posts.last()
    }

    fun printPosts() {
        for (post in posts) {
            println(post)
        }

    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                val updatedPost = newPost.copy()
                posts[index] = updatedPost
                return true
            }
        }
        return false
    }
}

fun main() {

    val photoAttachment = PhotoAttachment(Photo(
        1,
        1,
        1,
        "фотка"
    ))
    val videoAttachment = VideoAttachment(Video(
        1,
        1,
        "Видео",
        "ДР",
        60
    ))
    val audioAttachment = AudioAttachment(Audio(
        1,
        1,
        "artist",
        "sing",
        30
    ))
    val postWithAttachments = Post(
        1,
        text = "Post with Attachments",
        attachments = listOf(photoAttachment, videoAttachment, audioAttachment)
    )

    println(postWithAttachments)
}

