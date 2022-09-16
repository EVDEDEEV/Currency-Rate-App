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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.project.currenciestestapp.Constants.NAME_ASC_ARG
import my.project.currenciestestapp.Constants.NAME_DESC_ARG
import my.project.currenciestestapp.Constants.RATE_ASC_ARG
import my.project.currenciestestapp.Constants.RATE_DESC_ARG
import my.project.currenciestestapp.Constants.REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_ASC
import my.project.currenciestestapp.Constants.REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_DESC
import my.project.currenciestestapp.Constants.REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_ASC
import my.project.currenciestestapp.Constants.REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_DESC
import my.project.currenciestestapp.R
import my.project.currenciestestapp.data.models.roomDataBase.currencyEntity.CurrencyEntity
import my.project.currenciestestapp.databinding.FragmentCurrencyListBinding
import my.project.currenciestestapp.presentation.fragments.favoritesFragment.FavoritesViewModel

@AndroidEntryPoint
class CurrencyListFragment : Fragment(R.layout.fragment_currency_list) {

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private val currencyViewModel: CurrencyListViewModel by viewModels()
    private val binding by viewBinding(FragmentCurrencyListBinding::bind)
    private var currencyAdapter = CurrencyAdapter { currencyEntity: CurrencyEntity ->
        addToFavorites(currencyEntity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setupCurrencyItemSelectedListener()
        checkInternetConnectionAndRefreshData()
        setDataToRecyclerView()
        initFiltersByCurrencyNameAndRate()
        initFilterButton()
    }

    private fun setupCurrencyItemSelectedListener() {
        binding.currencyListSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long,
                ) {
                    if (pos >= 0) {
                        checkInternetConnectionAndRefreshData()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    fun checkInternetConnectionAndRefreshData() {
        val baseCurrency = binding.currencyListSpinner.selectedItem.toString()
        getCurrenciesFromApi(baseCurrency)
        with(binding) {
            if (currencyViewModel.connectionError.value?.isNotEmpty() == true) {
                mainFragmentContainer.visibility = View.INVISIBLE
                refreshConnectionButton.visibility = View.VISIBLE
                checkInternetConnectionText.visibility = View.VISIBLE
                setupDataLoading()
            } else {
                checkInternetConnectionText.visibility = View.INVISIBLE
                mainFragmentContainer.visibility = View.VISIBLE
                refreshConnectionButton.visibility = View.INVISIBLE
                refreshConnectionButton.setOnClickListener {
                    if (currencyViewModel.isHasInternetConnection()) {
                        lifecycleScope.launch(Dispatchers.Main) {
                            currencyViewModel.connectionError.value = null
                            mainFragmentContainer.visibility = View.VISIBLE
                            refreshConnectionButton.visibility = View.INVISIBLE
                            checkInternetConnectionText.visibility = View.INVISIBLE
                            setupDataLoading()
                        }
                    }
                }
            }
        }
    }

    private fun setupDataLoading() {
        val baseCurrency = binding.currencyListSpinner.selectedItem.toString()
        getCurrenciesFromApi(baseCurrency)
        initRecyclerView()
        setupCurrencyItemSelectedListener()
        setDataToRecyclerView()
    }

    private fun getCurrenciesFromApi(baseCurrency: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            currencyViewModel.getRatesFromApi(baseCurrency)
        }

    }

    private fun setDataToRecyclerView() {
        lifecycleScope.launch(Dispatchers.IO) {
            currencyViewModel.currencies.collect { data ->
                currencyAdapter.submitList(data)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewCurrency.apply {
            adapter = currencyAdapter
            layoutManager = GridLayoutManager(
                context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        }
    }

    private fun addToFavorites(currencyEntity: CurrencyEntity) {
        favoritesViewModel.addToFavorites(
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

    private fun initFiltersByCurrencyNameAndRate() {
        filterListByNameAscending()
        filterListByNameDescending()
        filterListByRateAscending()
        filterListByRateDescending()
    }

    private fun filterListByNameAscending() {
        setFragmentResultListener(REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_ASC) { key, bundle ->
            val result = bundle.getString(NAME_ASC_ARG)
            val sortedCurrency = currencyViewModel.getFilteredList(result)
            lifecycleScope.launch(Dispatchers.IO) {
                sortedCurrency.collect {
                    currencyAdapter.submitList(it)
                }
            }
        }
    }

    private fun filterListByNameDescending() {
        setFragmentResultListener(REQUEST_KEY_FOR_CURRENCY_NAME_FILTER_DESC) { key, bundle ->
            val result = bundle.getString(NAME_DESC_ARG)
            val sortedCurrency = currencyViewModel.getFilteredList(result)
            lifecycleScope.launch(Dispatchers.IO) {
                sortedCurrency.collect {
                    currencyAdapter.submitList(it)
                }
            }
        }
    }

    private fun filterListByRateAscending() {
        setFragmentResultListener(REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_ASC) { key, bundle ->
            val result = bundle.getString(RATE_ASC_ARG)
            val sortedCurrency = currencyViewModel.getFilteredList(result)
            lifecycleScope.launch(Dispatchers.IO) {
                sortedCurrency.collect {
                    currencyAdapter.submitList(it)
                }
            }
        }
    }

    private fun filterListByRateDescending() {
        setFragmentResultListener(REQUEST_KEY_FOR_CURRENCY_RATE_FILTER_DESC) { key, bundle ->
            val result = bundle.getString(RATE_DESC_ARG)
            val sortedCurrency = currencyViewModel.getFilteredList(result)
            lifecycleScope.launch(Dispatchers.IO) {
                sortedCurrency.collect {
                    currencyAdapter.submitList(it)
                }
            }
        }
    }
}