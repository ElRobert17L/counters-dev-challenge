package mx.com.rlr.base_use_case

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

@ExperimentalCoroutinesApi
abstract class NullableResultFlowUseCase<T> {

    /**
     * Trigger for the action which can be done in this request
     */
    private val _trigger = MutableStateFlow<Boolean?>(null)

    /**
     * Exposes result of this use case
     */
    val resultFlow: Flow<T?> = _trigger.flatMapLatest { trigger ->
        trigger?.let { performAction() } ?: flowOf(null)
    }

    /**
     * Triggers the execution of this use case
     */
    suspend fun launch() {
        _trigger.emit(!(_trigger.value?:true))
    }

    protected abstract fun performAction() : Flow<T>
}