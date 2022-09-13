package my.project.currenciestestapp.presentation.fragments.currencyFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.databinding.CurrencyItemBinding

class CurrencyAdapter :
    ListAdapter<CurrencyEntity, CurrencyAdapter.CurrencyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CurrencyItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class CurrencyViewHolder(
        private val binding: CurrencyItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyEntity: CurrencyEntity) {
            val rateDoubleValue = currencyEntity.rate
            binding.currencyName.text = currencyEntity.currencyName
            binding.currencyRate.text = String.format("%.2f", rateDoubleValue)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CurrencyEntity>() {
            override fun areItemsTheSame(oldItem: CurrencyEntity, newItem: CurrencyEntity): Boolean =
                oldItem.currencyName == newItem.currencyName

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: CurrencyEntity, newItem: CurrencyEntity): Boolean =
                oldItem == newItem
        }
    }
}
