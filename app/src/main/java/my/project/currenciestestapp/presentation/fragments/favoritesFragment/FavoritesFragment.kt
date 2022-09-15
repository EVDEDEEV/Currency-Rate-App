package my.project.currenciestestapp.presentation.fragments.favoritesFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import my.project.currenciestestapp.R
import my.project.currenciestestapp.data.models.roomDataBase.favoritesEntity.FavoritesEntity
import my.project.currenciestestapp.databinding.FragmentFavoritesBinding


@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var favoritesAdapter: FavoritesAdapter? = null
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataToRecyclerView()
        initRecyclerView()
        deleteAllFavorites()
    }

    private fun setDataToRecyclerView() {
        lifecycleScope.launch {
            favoritesViewModel.favorites.collect { data ->
                favoritesAdapter?.submitList(data)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewFavorites.apply {

            favoritesAdapter = FavoritesAdapter { favoritesEntity: FavoritesEntity ->
                deleteItemFromFavorites(favoritesEntity)
            }

            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        }
    }

    private fun deleteItemFromFavorites(favoritesEntity: FavoritesEntity) {
        favoritesViewModel.removeFromFavorites(favoritesEntity.favoritesCurrencyName)
    }

    private fun deleteAllFavorites() {
        binding.clearFavoritesFAB.setOnClickListener {
            favoritesViewModel.deleteAllFavoritesItems()
        }
    }
}















