package mx.com.rlr.countersdevchallenge.presentation.home.add_counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.com.rlr.counters.presentation.add_counter.AddCounter
import mx.com.rlr.countersdevchallenge.presentation.common.extension.valueOrEmpty

class AddCounterViewModel(
    addCounter: AddCounter
) : ViewModel(),
    AddCounter by addCounter {

    val titleLiveData: MutableLiveData<String> = MutableLiveData()
    val title: String get() = titleLiveData.valueOrEmpty

    fun isNotBlankTitle(): Boolean = title.isNotBlank()

}