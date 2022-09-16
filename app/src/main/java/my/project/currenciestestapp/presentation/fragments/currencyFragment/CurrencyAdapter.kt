package my.project.currenciestestapp.presentation.fragments.currencyFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.databinding.CurrencyItemBinding

class CurrencyAdapter(
    private val addToFavorites: (CurrencyEntity) -> Unit,
) : ListAdapter<CurrencyEntity, CurrencyAdapter.CurrencyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CurrencyItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currentList[position], addToFavorites)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class CurrencyViewHolder(
        private val binding: CurrencyItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            currencyEntity: CurrencyEntity,
            addToFavorites: (CurrencyEntity) -> Unit,
        ) {
            val rateDoubleValue = currencyEntity.rate
            binding.currencyName.text = currencyEntity.currencyName
            binding.currencyRate.text = String.format("%.2f", rateDoubleValue)
            binding.addToFavorites.setOnClickListener {
                addToFavorites(currencyEntity)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CurrencyEntity>() {
            override fun areItemsTheSame(
                oldItem: CurrencyEntity,
                newItem: CurrencyEntity,
            ): Boolean = oldItem.currencyName == newItem.currencyName

            override fun areContentsTheSame(
                oldItem: CurrencyEntity,
                newItem: CurrencyEntity,
            ): Boolean = oldItem == newItem
        }
    }

}
