package hw_oop

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

class WallServiceTest {

    @Test
    fun addId() {
        val result = WallService.add(post = Post(1))
        assertEquals(1, result.id)
    }

    @Before
    fun clearBeforeTestTrue() {
        WallService.clear()
    }
    @Test
    fun updateExistingTrue(){
        WallService.add(Post(1, "1"))
        WallService.add(Post(2, "2"))
        WallService.add(Post(3, "3"))

        val result = WallService.update(Post(3, "NewText"))

        assertTrue(result)

    }

    @Before
    fun clearBeforeTestFalse() {
        WallService.clear()
    }
    @Test
    fun updateExistingFalse(){
        WallService.add(Post(1, "1"))
        WallService.add(Post(2, "2"))
        WallService.add(Post(3, "3"))

        val result = WallService.update(Post(4, "NewText"))

        assertFalse(result)

    }

    @Test
    fun addCommentToExistingPost() {
        val wallService = WallService
        val post = Post(id = 1, text = "Test post")
        wallService.add(post)

        val comment = Comments(id = 1, fromId = 2, date = System.currentTimeMillis(), text = "Nice comment")
        val createdComment = wallService.createComment(post.id, comment)

        assertEquals(comment, createdComment)

    }


    @Test(expected = WallService.PostNotFoundException::class)
    fun throwPostNotFoundException() {
        val wallService = WallService
        val comment = Comments(id = 1, fromId = 2, date = System.currentTimeMillis(), text = "Nice comment")

        wallService.createComment(2, comment)
    }

}