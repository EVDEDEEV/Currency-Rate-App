package my.project.currenciestestapp.presentation.fragments.currencyFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import my.project.currenciestestapp.R
import my.project.currenciestestapp.databinding.FragmentCurrencyListBinding
import my.project.currenciestestapp.presentation.models.RatesUiModel
import my.project.currenciestestapp.utils.Constants.API_KEY

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataFromApiToLocalUiModel()
        initRecyclerView()
        setDataToRecyclerView()
        selectedCurrencyItemListener()
    }

    private fun setDataFromApiToLocalUiModel() {
        val baseCurrency = binding.currencyListSpinner.selectedItem.toString()
        viewModel.getRates(baseCurrency)
    }

    private fun selectedCurrencyItemListener() {
        binding.currencyListSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    pos: Int,
                    id: Long,
                ) {
                    val baseCurrency = binding.currencyListSpinner.selectedItem.toString()
                    viewModel.getRates(baseCurrency)
                    viewModel.currencies.observe(viewLifecycleOwner) {
                        currencyAdapter.submitList(it)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun setDataToRecyclerView() {
        binding.recyclerViewCurrency.visibility = View.VISIBLE
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
