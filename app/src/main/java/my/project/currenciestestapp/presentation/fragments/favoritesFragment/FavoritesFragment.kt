package my.project.currenciestestapp.presentation.fragments.favoritesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import my.project.currenciestestapp.databinding.FragmentFavoritesBinding
import my.project.currenciestestapp.presentation.fragments.currencyFragment.CurrencyListViewModel


@AndroidEntryPoint
class FavoritesFragment : Fragment() {

//    private val favoritesAdapter by lazy { FavoritesAdapter() }
//    private val viewModel: CurrencyListViewModel by viewModels()
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
//        setDataFromApiToLocalUiModel()
//        initRecyclerView()
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setDataToRecyclerView()
//    }
//
//    private fun setDataFromApiToLocalUiModel() {
//        viewModel.getRates()
//    }
////    private fun setDataFromApiToLocalUiModel() {
////        viewModel.getPost()
////    }
////    private fun setDataFromApiToLocalUiModel2() {
//////        viewModel.getCurrencies()
////        val aa = viewModel.getCurrencies()
////        val dd = aa
////    }
//
//    private fun setDataToRecyclerView() {
//        viewModel.currencies.observe(viewLifecycleOwner) {
//            favoritesAdapter.submitList(it)
//        }
//    }
////    private fun setDataToRecyclerView() {
////        viewModel.currencies.observe(viewLifecycleOwner) {
////            currencyAdapter.submitList(it)
////        }
////    }
//
//    private fun initRecyclerView() {
//        binding.recyclerViewFavorites.apply {
//            adapter = favoritesAdapter
//            layoutManager = GridLayoutManager(
//                context, 2, GridLayoutManager.VERTICAL, false)
//            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
//        }
//    }
}















