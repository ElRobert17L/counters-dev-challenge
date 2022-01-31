package mx.com.rlr.counters.domain.use_case.dec_counter

import mx.com.rlr.counters.domain.entity.Counter

data class DecCounterResponse(
    val counters: List<Counter>
)