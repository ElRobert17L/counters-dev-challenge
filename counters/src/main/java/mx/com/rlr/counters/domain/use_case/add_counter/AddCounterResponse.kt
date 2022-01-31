package mx.com.rlr.counters.domain.use_case.add_counter

import mx.com.rlr.counters.domain.entity.Counter

data class AddCounterResponse(
    val counters: List<Counter>
)