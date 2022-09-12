package my.project.currenciestestapp.presentation.fragments.favoritesFragment//package my.project.currenciestestapp.presentation.fragments.favoritesFragment
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import my.project.currenciestestapp.databinding.CurrencyItemBinding
//import my.project.currenciestestapp.presentation.models.RatesUiModel
//
//class FavoritesAdapter :
//    ListAdapter<RatesUiModel, FavoritesAdapter.FavoritesViewHolder>(DIFF_CALLBACK) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
//        val binding = CurrencyItemBinding
//            .inflate(LayoutInflater.from(parent.context), parent, false)
//        return FavoritesViewHolder(binding)
//
//    }
//
//    //    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
////        holder.bind(listCurrency[position])
////    }
//    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//
//    override fun getItemCount(): Int {
//        return currentList.size
//    }
//
//    inner class FavoritesViewHolder(
//        private val binding: CurrencyItemBinding,
//    ) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(ratesUi: RatesUiModel) {
//            val rateDoubleValue = ratesUi.rates
//            binding.currencyName.text = ratesUi.currencyName
//            binding.currencyRate.text = String.format("%.2f", rateDoubleValue)
//        }
//    }
//
//    companion object {
//        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RatesUiModel>() {
//            override fun areItemsTheSame(oldItem: RatesUiModel, newItem: RatesUiModel): Boolean =
//                oldItem.id == newItem.id
//
//            @SuppressLint("DiffUtilEquals")
//            override fun areContentsTheSame(oldItem: RatesUiModel, newItem: RatesUiModel): Boolean =
//                oldItem == newItem
//        }
//    }
//
//
//}