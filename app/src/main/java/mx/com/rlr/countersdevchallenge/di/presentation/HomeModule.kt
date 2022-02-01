package mx.com.rlr.countersdevchallenge.di.presentation

import mx.com.rlr.countersdevchallenge.presentation.home.HomeViewModel
import mx.com.rlr.countersdevchallenge.presentation.home.crud_counter.CrudViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel {
        HomeViewModel(getCounters = get())
    }

    viewModel {
        CrudViewModel(
            getCounters = get(),
            addCounter = get(),
            deleteCounter = get(),
            incCounter = get(),
            decCounter = get()
        )
    }

}