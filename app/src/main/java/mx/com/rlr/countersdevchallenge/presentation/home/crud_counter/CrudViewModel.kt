package mx.com.rlr.countersdevchallenge.presentation.home.crud_counter

import androidx.lifecycle.ViewModel
import mx.com.rlr.counters.presentation.add_counter.AddCounter
import mx.com.rlr.counters.presentation.dec_counter.DecCounter
import mx.com.rlr.counters.presentation.delete_counter.DeleteCounter
import mx.com.rlr.counters.presentation.get_counters.GetCounters
import mx.com.rlr.counters.presentation.inc_counter.IncCounter

class CrudViewModel(
    getCounters: GetCounters,
    addCounter: AddCounter,
    deleteCounter: DeleteCounter,
    incCounter: IncCounter,
    decCounter: DecCounter
) : ViewModel(),
    GetCounters by getCounters,
    AddCounter by addCounter,
    DeleteCounter by deleteCounter,
    IncCounter by incCounter,
    DecCounter by decCounter