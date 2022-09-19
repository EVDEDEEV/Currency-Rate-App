package my.project.currenciestestapp.presentation.fragments.favoritesFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.project.currenciestestapp.Constants.RATES_VALUES_FORMAT
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import my.project.currenciestestapp.databinding.FavoritesItemBinding
import my.project.currenciestestapp.databinding.FragmentFavoritesBinding
import kotlin.coroutines.coroutineContext

class FavoritesAdapter(private val deleteItemFromFavorites: (FavoritesEntity) -> Unit) :
    ListAdapter<FavoritesEntity, FavoritesAdapter.FavoritesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = FavoritesItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(currentList[position], deleteItemFromFavorites)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class FavoritesViewHolder(
        private val binding: FavoritesItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            favoritesEntity: FavoritesEntity,
            deleteItemFromFavorites: (FavoritesEntity) -> Unit,
        ) {
            val rateDoubleValue = favoritesEntity.favoriteRate
            with(binding) {
                favoriteCurrencyName.text = favoritesEntity.favoritesCurrencyName
                favoriteCurrencyRate.text = String.format(RATES_VALUES_FORMAT, rateDoubleValue)
                favoritesCurrencyDescription.text = favoritesEntity.favoriteDescription
                removeFromFavorites.setOnClickListener {
                    deleteItemFromFavorites(favoritesEntity)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoritesEntity>() {
            override fun areItemsTheSame(
                oldItem: FavoritesEntity,
                newItem: FavoritesEntity,
            ): Boolean = oldItem.favoritesCurrencyName == newItem.favoritesCurrencyName

            override fun areContentsTheSame(
                oldItem: FavoritesEntity,
                newItem: FavoritesEntity,
            ): Boolean = oldItem == newItem
        }
    }
}