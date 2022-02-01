package mx.com.rlr.countersdevchallenge.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.counters.domain.entity.Counter
import mx.com.rlr.counters.domain.use_case.get_counters.GetCountersParams
import mx.com.rlr.counters.presentation.get_counters.GetCountersStatus
import mx.com.rlr.countersdevchallenge.R
import mx.com.rlr.countersdevchallenge.databinding.HomeFragmentBinding
import mx.com.rlr.countersdevchallenge.presentation.common.extension.android.gone
import mx.com.rlr.countersdevchallenge.presentation.common.extension.android.showSnackbar
import mx.com.rlr.countersdevchallenge.presentation.common.extension.android.visible
import mx.com.rlr.countersdevchallenge.presentation.home.counters_adapter.CountersAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment() {

    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()

    private val countersAdapter: CountersAdapter by lazy {
        CountersAdapter(onCounterOnClickListener = onCounterRowClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        executeGetCounters()
        setUpSwipeRefresh()
        setUpActions()
    }

    private fun setUpSwipeRefresh() {
        binding.homeFragmentSwipe.apply {
            setOnRefreshListener {
                executeGetCounters()
                isRefreshing = false
            }
        }
    }

    private fun executeGetCounters() {
        viewModel.getCountersAsLiveData(params = GetCountersParams)
            .observe(viewLifecycleOwner, getCounters())
    }

    private fun getCounters() = Observer<GetCountersStatus> {
        when (it) {
            is Status.Loading -> {/* PASS */}
            is Status.Failed -> {
                showSnackbar(it.failure.toString())
                setUpView(counters = emptyList())
            }
            is Status.Done -> setUpView(counters = it.value.counters)
        }
    }

    private fun setUpView(counters: List<Counter>) {
        if (counters.isEmpty()) {
            binding.apply {
                homeFragmentTvEmptyList.visible()
                homeFragmentLlTitles.gone()
                homeFragmentRvCounters.gone()
                homeFragmentGl.gone()
                homeFragmentBtnAddEmpty.visible()
            }
        } else {
            binding.apply {
                homeFragmentTvEmptyList.gone()
                homeFragmentLlTitles.visible()
                homeFragmentRvCounters.visible()
                homeFragmentGl.visible()
                homeFragmentBtnAddEmpty.gone()
            }
            setUpRecycler(counters = counters)
        }
    }

    private fun setUpRecycler(counters: List<Counter>) {
        binding.homeFragmentRvCounters.adapter = countersAdapter
        countersAdapter.submitList(counters)
    }

    private fun setUpActions() {
        binding.apply {
            homeFragmentBtnAdd.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addCounterFragment)
            }
            homeFragmentBtnDelete.setOnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToCrudFragment(
                    option = "Delete"
                )
                findNavController().navigate(direction)
            }
            homeFragmentBtnInc.setOnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToCrudFragment(
                    option = "Increase"
                )
                findNavController().navigate(direction)
            }
            homeFragmentBtnDec.setOnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToCrudFragment(
                    option = "Decrease"
                )
                findNavController().navigate(direction)
            }
            homeFragmentBtnAddEmpty.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addCounterFragment)
            }
        }
    }

    private val onCounterRowClick: (Counter) -> Unit = {
        Timber.d("onCounterRowClick: $it")
    }

}