package mx.com.rlr.countersdevchallenge.presentation.home.add_counter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.counters.presentation.add_counter.AddCounterStatus
import mx.com.rlr.countersdevchallenge.databinding.AddCounterFragmentBinding
import mx.com.rlr.countersdevchallenge.presentation.common.extension.android.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCounterFragment : Fragment() {

    private val binding: AddCounterFragmentBinding by lazy {
        AddCounterFragmentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = addCounterViewModel
        }
    }

    private val addCounterViewModel: AddCounterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTextChangeListener()
        setUpAction()
    }

    private fun setUpTextChangeListener() {
        binding.apply {
            addCounterFragmentEt.addTextChangedListener {
                if (addCounterViewModel.isNotBlankTitle()) {
                    addCounterFragmentTil.error = null
                    addCounterFragmentBtn.isEnabled = true
                } else {
                    addCounterFragmentTil.error = "Error. Title is empty"
                    addCounterFragmentBtn.isEnabled = false
                }
            }
        }
    }

    private fun setUpAction() {
        binding.addCounterFragmentBtn.setOnClickListener {
            executeAddCounter(view = it)
        }
    }

    private fun executeAddCounter(view: View) {
        addCounterViewModel.addCounterAsLiveData(title = addCounterViewModel.title)
            .observe(viewLifecycleOwner, addCounter(view = view))
    }

    private fun addCounter(view: View) = Observer<AddCounterStatus> {
        view.isEnabled = true
        when (it) {
            is Status.Loading -> view.isEnabled = false
            is Status.Failed -> showSnackbar(it.failure.toString())
            is Status.Done -> findNavController().popBackStack()
        }
    }

}