package mx.com.rlr.base_use_case

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
abstract class FlowUseCase<T> {

    /**
     * Trigger for the action which can be done in this request
     */
    private val _trigger = MutableStateFlow(true)

    /**
     * Exposes result of this use case
     */
    val resultFlow: Flow<T> = _trigger.flatMapLatest {
        performAction()
    }

    protected abstract fun performAction() : Flow<T>

    /**
     * Triggers the execution of this use case
     */
    suspend fun launch() {
        _trigger.emit(!(_trigger.value))
    }
}