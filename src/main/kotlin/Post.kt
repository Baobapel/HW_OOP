package hw_oop

data class Post(
    val id: Int?,
    val text: String = "Text",
    val ownerId: Int = 1,
    val fromId: Int = 1,
    val createdBy: Int = 1,
    val date: Long = 1,
    val replyOwnerId: Int = 1,
    val replyPostId: Int = 1,
    var friendsOnly: Boolean = true,
    val comment: Comments? = Comments(1, 0, 0, "Text")
)

data class Comments(
    val id: Int,
    val fromId: Int,
    val date: Long,
    val text: String,

    )

object WallService {
    private var posts = emptyArray<Post>()
    private var currentId = 0
    private var comments = emptyArray<Comments>()

    fun createComment(postId: Int?, comment: Comments): Comments? {
        val postIndex = posts.indexOfFirst { it.id == postId }

        if (postIndex != -1) {
            val newComment = comment.copy(
                id = comments.size + 1)
            comments = comments.plus(newComment)

            return newComment
        } else {
            throw PostNotFoundException("Post with ID $postId not found")

        }

    }

    class PostNotFoundException(message: String) : Exception(message)

    fun getPostById(postId: Int?): Post? {
        return posts.find { it.id == postId }
    }

    fun clear() {
        posts = emptyArray<Post>()
        comments = emptyArray<Comments>()
        currentId = 0
    }

    fun add(post: Post): Post {
        posts += post.copy()
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

    val wallService = WallService

    // Создаем новый пост с указанным id
    val newPost = Post(id = 5, text = "New post text")

    wallService.add(newPost)


    println("Post ID: ${newPost.id}")
    println("Post Text: ${newPost.text}")

    val newComment = Comments(id = 1, fromId = 2, date = System.currentTimeMillis(), text = "Nice post!")

    try {
        val createdComment = wallService.createComment(newPost.id, newComment)
        println("Comment added successfully.")

        // Выводим информацию о созданном комментарии
        println("Created Comment ID: ${createdComment?.id}")
        println("Created Comment Text: ${createdComment?.text}")
    } catch (e: WallService.PostNotFoundException) {
        println("Error: ${e.message}")
    }

    val postWithComment = wallService.getPostById(newPost.id)
    postWithComment?.comment?.let {
        println("Comment ID: ${it.id}")
        println("Comment Text: ${it.text}")
    }

    // Выведем обновленный пост
    wallService.printPosts()
}


