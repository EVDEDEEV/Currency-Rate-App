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
import my.project.currenciestestapp.databinding.FragmentCurrencyListBinding

@AndroidEntryPoint
class CurrencyListFragment : Fragment() {

    private val viewModel: CurrencyListViewModel by viewModels()
    private lateinit var binding: FragmentCurrencyListBinding
    private val currencyAdapter by lazy { CurrencyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        initRecyclerView()
        selectedCurrencyItemListener()
//        setDataToRecyclerView()
        return binding.root
    }

    private fun getDataFromApiToRoomEntity() {
        val baseCurrency = binding.currencyListSpinner.selectedItem.toString()
        viewModel.getRates(baseCurrency)
    }

    private fun getRatesFromDb(base: String) {
//        val baseCurrency = binding.currencyListSpinner.selectedItem.toString()
        viewModel.getRatesFromDb(base)
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
                        getRatesFromDb(baseCurrency)
                        setDataToRecyclerView()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setDataToRecyclerView() {
        viewModel.currencies.observe(viewLifecycleOwner) {
            currencyAdapter.submitList(it)
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
}
