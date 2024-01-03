package hw_oop

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Test
    fun addId() {
        val result = WallService.add(post = Post(1))
        assertEquals(Post(id=1), result)
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

}