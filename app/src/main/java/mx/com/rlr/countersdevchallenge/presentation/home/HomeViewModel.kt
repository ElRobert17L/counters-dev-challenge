package mx.com.rlr.countersdevchallenge.presentation.home

import androidx.lifecycle.ViewModel
import mx.com.rlr.counters.presentation.get_counters.GetCounters

class HomeViewModel(
    getCounters: GetCounters
) : ViewModel(),
    GetCounters by getCounters