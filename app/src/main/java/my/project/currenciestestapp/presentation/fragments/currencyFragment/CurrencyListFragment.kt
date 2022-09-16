package my.project.currenciestestapp.presentation.fragments.currencyFragment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import my.project.currenciestestapp.Constants.RATE_ASC
import my.project.currenciestestapp.Constants.REQUEST_KEY
import my.project.currenciestestapp.R
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.databinding.FragmentCurrencyListBinding
import my.project.currenciestestapp.presentation.fragments.favoritesFragment.FavoritesViewModel

@AndroidEntryPoint
class CurrencyListFragment : Fragment(R.layout.fragment_currency_list) {

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val currencyViewModel: CurrencyListViewModel by viewModels()
    private val binding by viewBinding(FragmentCurrencyListBinding::bind)
    private var currencyAdapter: CurrencyAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
//        setDataToRecyclerView()
        selectedCurrencyItemListener()
        initFilterButton()
//        filterByNameAscending()
        filterList()
    }

    private fun filterList() {
        setFragmentResultListener(REQUEST_KEY) { key, bundle ->
            val result = bundle.getString(RATE_ASC)
            val sortedCurrency = currencyViewModel.getFilteredList(result)
            lifecycleScope.launch {
                sortedCurrency.collect {
                    currencyAdapter?.submitList(it)
                }
            }
        }
    }

    private fun getRatesFromApi(base: String) {
        currencyViewModel.getRatesFromApi(base)
    }

    private fun selectedCurrencyItemListener() {
        with(binding) {
            currencyListSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        pos: Int,
                        id: Long,
                    ) {
                        if (pos >= 1) {
                            val baseCurrency =
                                currencyListSpinner.selectedItem.toString()
                            getRatesFromApi(baseCurrency)
//                            setDataToRecyclerView()
//                            filterList()
                            //Новый запрос при смене фрагментов
                        } else {
//                            setDataToRecyclerView()
//                            filterList()
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        }
    }

    private fun setDataToRecyclerView() {
        lifecycleScope.launch {
            currencyViewModel.currencies.collect { data ->
                currencyAdapter?.submitList(data)
            }
        }
    }
//    private fun setDataToRecyclerView() {
//        lifecycleScope.launch {
//            val rr = currencyViewModel.aaa()
//            currencyAdapter?.submitList(rr)
//        }
//    }


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

    private fun initFilterButton() {
        binding.filterButton.setOnClickListener {
            val action =
                CurrencyListFragmentDirections.actionCurrencyListFragmentToFilterBottomSheetFragment()
            view?.findNavController()?.navigate(action)
        }
    }
}
