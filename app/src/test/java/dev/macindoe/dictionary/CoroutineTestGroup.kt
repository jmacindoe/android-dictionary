package dev.macindoe.dictionary

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.spekframework.spek2.dsl.GroupBody
import org.spekframework.spek2.dsl.Skip
import org.spekframework.spek2.style.specification.Suite

inline fun GroupBody.coroutineGroup(
    description: String,
    pending: Skip = Skip.No,
    crossinline body: Suite.() -> Unit) {

    val testThread = newSingleThreadContext("Test thread")

    group(description, pending) {
        beforeEachTest {
            Dispatchers.setMain(testThread)
        }
        body(Suite(this))
        afterEachTest {
            Dispatchers.resetMain()
            testThread.close()
        }
    }
}
