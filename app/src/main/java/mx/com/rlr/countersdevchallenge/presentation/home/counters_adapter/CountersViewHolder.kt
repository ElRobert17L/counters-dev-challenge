package mx.com.rlr.countersdevchallenge.presentation.home.counters_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.rlr.counters.domain.entity.Counter
import mx.com.rlr.countersdevchallenge.databinding.ItemCounterBinding

class CountersViewHolder(
    private val binding: ItemCounterBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(counter: Counter, onCounterClickListener: (Counter) -> Unit) {
        binding.apply {
            itemCounterTvId.text = counter.id
            itemCounterTvTitle.text = counter.title
            itemCounterTvCount.text = counter.count.toString()
            root.setOnClickListener { onCounterClickListener(counter) }
        }
    }

    companion object {
        fun from(parent: ViewGroup): CountersViewHolder {
            val binding = ItemCounterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return CountersViewHolder(binding)
        }
    }

}