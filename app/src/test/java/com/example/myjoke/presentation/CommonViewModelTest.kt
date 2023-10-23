package com.example.myjoke.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.myjoke.core.DispatcherList
import com.example.myjoke.domain.ItemDomain
import com.example.myjoke.domain.Interactor
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertNotEquals
import org.junit.Test

/*
class CommonViewModelTest {

    @Test
    fun `test get from cached false success`(): Unit = runBlocking {

        val jokeInteractor = TestInteractor()
        val communication = TestCommunication()
        val viewmodel = CommonViewModel(jokeInteractor, communication, TestCoroutineDispatcherTest())
        jokeInteractor.setSuccess(true)

        viewmodel.changeCachedStatus(false)
        viewmodel.joke()

        val actualText = communication.text
        val actualId = communication.id

        val expectedText = "SuccessSetup\nSuccessPunchline"
        assertEquals(actualText, expectedText)
        assertNotEquals(0, actualId)
    }

    @Test
    fun `test get from cached true success`(): Unit = runBlocking {

        val jokeInteractor = TestInteractor()
        val communication = TestCommunication()
        val viewmodel = CommonViewModel(jokeInteractor, communication, TestCoroutineDispatcherTest())
        jokeInteractor.setSuccess(true)
        jokeInteractor.changeJokeStatus = true
        viewmodel.changeCachedStatus(true)
        viewmodel.joke()

        val actualText = communication.text
        val actualId = communication.id

        val expectedText = "SuccessFavoriteSetup\nSuccessFavoritePunchline"
        assertEquals(actualText, expectedText)
        assertNotEquals(0, actualId)
    }

    @Test
    fun `test get from cached false error`(): Unit = runBlocking {

        val jokeInteractor = TestInteractor()
        val communication = TestCommunication()
        val viewmodel = CommonViewModel(jokeInteractor, communication, TestCoroutineDispatcherTest())
        jokeInteractor.setSuccess(false)
        jokeInteractor.changeJokeStatus = false

        viewmodel.changeCachedStatus(false)
        viewmodel.joke()

        val actualText = communication.text
        val actualId = 0

        val expectedText = "Нет соединения\n"
        assertEquals(actualText, expectedText)
        assertEquals(0, actualId)
    }

    @Test
    fun `test get from cached true error`(): Unit = runBlocking {

        val jokeInteractor = TestInteractor()
        val communication = TestCommunication()
        val viewmodel = CommonViewModel(jokeInteractor, communication, TestCoroutineDispatcherTest())
        jokeInteractor.setSuccess(false)
        jokeInteractor.changeJokeStatus = false

        viewmodel.changeCachedStatus(true)
        viewmodel.joke()

        val actualText = communication.text
        val actualId = 0

        val expectedText = "Нет соединения\n"
        assertEquals(actualText, expectedText)
        assertEquals(0, actualId)
    }

}

class TestInteractor() : Interactor {

    var changeJokeStatus: Boolean = false
    private val successItemDomain = ItemDomain.Base("SuccessSetup", "SuccessPunchline", false)
    private val successItemDomainFavorite = ItemDomain.Base("SuccessFavoriteSetup", "SuccessFavoritePunchline", true)
    private val failItemDomainFavorite = ItemDomain.Fail("Нет соединения")
    private var success = true

    fun setSuccess(state: Boolean) {
        success = state
    }

    override suspend fun getItem(): ItemDomain {
        return if (success) {
            if (changeJokeStatus)
                successItemDomainFavorite
            else {
                successItemDomain
            }
        } else
            failItemDomainFavorite

    }

    override fun changeCachedStatus(cached: Boolean) {
        changeJokeStatus = cached
    }

    override suspend fun changeStateFavorites(): ItemDomain {
        return if (success) {
             if (changeJokeStatus)
                successItemDomainFavorite
            else successItemDomain
        } else
        return failItemDomainFavorite
    }

}

class TestCommunication: Communication<Pair<String, Int>>{

    var text = ""
    var id = -1
    var observedCount = 0

    override fun postValue(data: State.Initial) {
        text = data.first
        id = data.second
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
        observedCount++
    }

}

class TestCoroutineDispatcherTest: DispatcherList{
    override fun io(): CoroutineDispatcher{
        return TestCoroutineDispatcher()
    }
}
*/