package com.ulch.rickandmortypaging.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ulch.rickandmortypaging.Injection
import com.ulch.rickandmortypaging.databinding.FragmentDataListBinding
import com.ulch.rickandmortypaging.model.UiModel
import com.ulch.rickandmortypaging.ui.character.adapter.character.CharacterAdapter
import com.ulch.rickandmortypaging.ui.character.adapter.episode.EpisodeAdapter
import com.ulch.rickandmortypaging.ui.character.adapter.location.LocationAdapter
import com.ulch.rickandmortypaging.ui.character.load_state_adapter.DataLoadStateAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class DataListFragment : Fragment() {
    private val args: DataListFragmentArgs by navArgs()
    private var _binding: FragmentDataListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listType = args.type

        val viewModel = ViewModelProvider(
            this, Injection.provideViewModelFactory(
                context = requireContext(),
                owner = this
            )
        )[DataListViewModel::class.java]
        viewModel.initDataFlow(listType)

        val adapter = when (listType) {
            0 -> CharacterAdapter()
            1 -> LocationAdapter()
            2 -> EpisodeAdapter()
            else -> {
                throw Exception("Unknown type of data list")
            }
        }
        binding.list.adapter = adapter
        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            header = DataLoadStateAdapter { adapter.retry() },
            footer = DataLoadStateAdapter { adapter.retry() }
        )

        bindList(
            adapter as PagingDataAdapter<UiModel, RecyclerView.ViewHolder>,
            viewModel.pagingDataFlow
        )
    }

    private fun bindList(
        adapter: PagingDataAdapter<UiModel, RecyclerView.ViewHolder>,
        pagingData: Flow<PagingData<UiModel>>,
    ) {


        binding.list.adapter = adapter
        lifecycleScope.launch {
            pagingData.collectLatest(adapter::submitData)
        }

        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { loadState ->
                    val isListEmpty =
                        loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                    binding.emptyList.isVisible = isListEmpty
                    binding.list.isVisible = !isListEmpty
                    binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                    binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}