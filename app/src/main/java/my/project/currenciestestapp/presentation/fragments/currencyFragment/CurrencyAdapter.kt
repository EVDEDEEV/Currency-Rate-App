package my.project.currenciestestapp.presentation.fragments.currencyFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.project.currenciestestapp.databinding.CurrencyItemBinding
import my.project.currenciestestapp.extensions.getFlagResource
import my.project.currenciestestapp.presentation.models.RatesUiModel

//class CurrencyAdapter :
//    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
class CurrencyAdapter :
    ListAdapter<RatesUiModel, CurrencyAdapter.CurrencyViewHolder>(DIFF_CALLBACK) {

//    private var listCurrency = emptyList<RatesUiModel>()
//    private var listCurrency = emptyList<FakeList>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CurrencyItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)

    }

//    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
//        holder.bind(listCurrency[position])
//    }
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class CurrencyViewHolder(
        private val binding: CurrencyItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ratesUi: RatesUiModel) {
            binding.currencyName.text = ratesUi.currencyName
            binding.currencyRate.text = ratesUi.rates.toString()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RatesUiModel>() {
            override fun areItemsTheSame(oldItem: RatesUiModel, newItem: RatesUiModel): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: RatesUiModel, newItem: RatesUiModel): Boolean =
                oldItem == newItem
        }
    }
}
