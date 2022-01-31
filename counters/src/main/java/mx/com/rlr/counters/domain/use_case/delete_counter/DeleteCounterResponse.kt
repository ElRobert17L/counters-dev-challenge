package mx.com.rlr.counters.domain.use_case.delete_counter

import mx.com.rlr.counters.domain.entity.Counter

data class DeleteCounterResponse(
    val counters: List<Counter>
)