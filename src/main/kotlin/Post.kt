package hw_oop

data class Post(
    val id: Int,
    val text: String = "Text",
    val ownerId: Int = 1,
    val fromId: Int = 1,
    val createdBy: Int = 1,
    val date: Long = 1,
    val replyOwnerId: Int = 1,
    val replyPostId: Int = 1,
    var friendsOnly: Boolean = true,
) {
    data class Comments(
        val count: Int,
        val canPost: Boolean,
        val groupsCanPost: Boolean,
        val canClose: Boolean,
        val canOpen: Boolean
    )
}

object WallService {
    private var posts = emptyArray<Post>()
    private var currentId = 0

fun clear () {
    posts = emptyArray<Post>()
    var currentId = 0
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
                posts[index] = newPost
                return true
            }
        }
        return false
    }
}

fun main() {
    WallService.add(Post(1, "Hi"))
    WallService.add(Post(1, "Hello"))
    WallService.printPosts()
    println(WallService.update(Post(2, "Update")))
    WallService.printPosts()
}

