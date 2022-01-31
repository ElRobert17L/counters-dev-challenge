package mx.com.rlr.counters.domain.use_case.get_counters

import mx.com.rlr.counters.domain.entity.Counter

data class GetCountersResponse(
    val counters: List<Counter>
)