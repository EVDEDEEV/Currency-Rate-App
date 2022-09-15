package my.project.currenciestestapp.presentation.fragments.favoritesFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import my.project.currenciestestapp.databinding.FavoritesItemBinding

class FavoritesAdapter(private val deleteFromFavorites: (FavoritesEntity) -> Unit) :
    ListAdapter<FavoritesEntity, FavoritesAdapter.FavoritesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = FavoritesItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(currentList[position], deleteFromFavorites)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class FavoritesViewHolder(
        private val binding: FavoritesItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            favoritesEntity: FavoritesEntity,
            deleteFromFavorites: (FavoritesEntity) -> Unit,
        ) {
            val rateDoubleValue = favoritesEntity.favoriteRate
            binding.favoriteCurrencyName.text = favoritesEntity.favoritesCurrencyName
//            binding.favoriteCurrencyRate.text = favoritesEntity.favoriteRate.toString()
            binding.favoriteCurrencyRate.text = String.format("%.2f", rateDoubleValue)
            binding.removeFromFavorites.setOnClickListener {
                deleteFromFavorites(favoritesEntity)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoritesEntity>() {
            override fun areItemsTheSame(
                oldItem: FavoritesEntity,
                newItem: FavoritesEntity,
            ): Boolean =
                oldItem.favoritesCurrencyName == newItem.favoritesCurrencyName

            override fun areContentsTheSame(
                oldItem: FavoritesEntity,
                newItem: FavoritesEntity,
            ): Boolean =
                oldItem == newItem
        }
    }
}