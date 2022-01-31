package mx.com.rlr.counters.domain.use_case.inc_counter

import mx.com.rlr.counters.domain.entity.Counter

data class IncCounterResponse(
    val counters: List<Counter>
)