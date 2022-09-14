package my.project.currenciestestapp.presentation.fragments.favoritesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import my.project.currenciestestapp.databinding.FragmentFavoritesBinding


@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val favoritesAdapter by lazy { FavoritesAdapter() }
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        initRecyclerView()
        getFavoritesFromDb()
        setDataToRecyclerView()
        initRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setDataToRecyclerView()
//        initRecyclerView()
    }

    private fun getFavoritesFromDb() {
        favoritesViewModel.getFavoritesFromDb()
    }

    private fun setDataToRecyclerView() {
        favoritesViewModel.favorites.observe(viewLifecycleOwner, Observer {
            favoritesAdapter.setList(it)
            favoritesAdapter.notifyDataSetChanged()
        })
    }

    private fun initRecyclerView() {
        binding.recyclerViewFavorites.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        }
    }
}















