package my.project.currenciestestapp.presentation.fragments.currencyFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.databinding.FragmentCurrencyListBinding
import my.project.currenciestestapp.presentation.fragments.favoritesFragment.FavoritesViewModel

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val currencyViewModel: CurrencyListViewModel by viewModels()
    private lateinit var binding: FragmentCurrencyListBinding

    //    private val currencyAdapter = CurrencyAdapter {
//        currencyViewModel.addToFavor(it)
//    }
//    private val currencyAdapter by lazy { CurrencyAdapter() }
    private var currencyAdapter: CurrencyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        initRecyclerView()
        selectedCurrencyItemListener()
        return binding.root
    }

    private fun getRatesFromApi(base: String) {
        currencyViewModel.getRatesFromApi(base)
    }

    private fun getRatesFromDb() {
        currencyViewModel.getRatesFromDb()
    }

    private fun selectedCurrencyItemListener() {
        binding.currencyListSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long,
                ) {
                    if (pos >= 0) {
                        val baseCurrency = binding.currencyListSpinner.selectedItem.toString()
                        getRatesFromApi(baseCurrency)
                        getRatesFromDb()
                        setDataToRecyclerView()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setDataToRecyclerView() {
        currencyViewModel.currencies.observe(viewLifecycleOwner) {
            currencyAdapter?.submitList(it)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewCurrency.apply {

            currencyAdapter = CurrencyAdapter { currencyEntity: CurrencyEntity ->
                addToFavorites(
                    currencyEntity
                )
            }

            adapter = currencyAdapter


            layoutManager = GridLayoutManager(
                context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        }
    }

    private fun addToFavorites(currencyEntity: CurrencyEntity) {
        favoritesViewModel.addToFavor(
            currencyName = currencyEntity.currencyName,
            rate = currencyEntity.rate
        )
    }
}
