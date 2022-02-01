package mx.com.rlr.countersdevchallenge.presentation.home.crud_counter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mx.com.rlr.base_use_case.Status
import mx.com.rlr.counters.presentation.add_counter.AddCounterStatus
import mx.com.rlr.counters.presentation.dec_counter.DecCounterStatus
import mx.com.rlr.counters.presentation.delete_counter.DeleteCounterStatus
import mx.com.rlr.counters.presentation.inc_counter.IncCounterStatus
import mx.com.rlr.countersdevchallenge.databinding.CrudFragmentBinding
import mx.com.rlr.countersdevchallenge.presentation.common.enums.CounterOptions
import mx.com.rlr.countersdevchallenge.presentation.common.extension.android.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CrudFragment : Fragment() {

    private val binding: CrudFragmentBinding by lazy {
        CrudFragmentBinding.inflate(layoutInflater)
    }

    private val args: CrudFragmentArgs by navArgs()

    private val viewModel: CrudViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpRecycler()
        setUpAction()
    }

    private fun setUpView() {
        val counterOption = CounterOptions.toCounterOptions(args.option).toString()
        val title = "$counterOption counter"

        binding.apply {
            crudFragmentTv.text = title
            crudFragmentBtn.text = counterOption
        }
    }

    private fun setUpRecycler() {
        //TODO: IMPLEMENTAR RECYLER Y AGREGAR AL XML UN EDIT_TEXT PARA QUE INGRESEN EL ID O TITLE A ELIMINAR

    }

    private fun setUpAction() {
        binding.crudFragmentBtn.setOnClickListener(::optionClickListener)
    }

    private fun optionClickListener(view: View) {
        when(CounterOptions.toCounterOptions(args.option)) {
            CounterOptions.ADD -> executeAddCounter(view = view)
            CounterOptions.DELETE -> executeDeleteCounter(view = view)
            CounterOptions.INCREASE -> executeIncCounter(view = view)
            CounterOptions.DECREASE -> executeDecCounter(view = view)
            else -> Timber.e("Error counter options")
        }
    }

    private fun executeAddCounter(view: View) {
        viewModel.addCounterAsLiveData(title = "")
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

    private fun executeDeleteCounter(view: View) {
        viewModel.deleteCounterAsLiveData(id = "")
            .observe(viewLifecycleOwner, deleteCounter(view = view))
    }

    private fun deleteCounter(view: View) = Observer<DeleteCounterStatus> {
        view.isEnabled = true
        when (it) {
            is Status.Loading -> view.isEnabled = false
            is Status.Failed -> showSnackbar(it.failure.toString())
            is Status.Done -> findNavController().popBackStack()
        }
    }

    private fun executeIncCounter(view: View) {
        viewModel.incCounterAsLiveData(id = "")
            .observe(viewLifecycleOwner, incCounter(view = view))
    }

    private fun incCounter(view: View) = Observer<IncCounterStatus> {
        view.isEnabled = true
        when (it) {
            is Status.Loading -> view.isEnabled = false
            is Status.Failed -> showSnackbar(it.failure.toString())
            is Status.Done -> findNavController().popBackStack()
        }
    }

    private fun executeDecCounter(view: View) {
        viewModel.decCounterAsLiveData(id = "")
            .observe(viewLifecycleOwner, decCounter(view = view))
    }

    private fun decCounter(view: View) = Observer<DecCounterStatus> {
        view.isEnabled = true
        when (it) {
            is Status.Loading -> view.isEnabled = false
            is Status.Failed -> showSnackbar(it.failure.toString())
            is Status.Done -> findNavController().popBackStack()
        }
    }

}