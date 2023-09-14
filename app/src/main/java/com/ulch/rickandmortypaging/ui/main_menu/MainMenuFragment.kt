package com.ulch.rickandmortypaging.ui.main_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ulch.rickandmortypaging.R
import com.ulch.rickandmortypaging.databinding.FragmentMainMenuBinding
import com.ulch.rickandmortypaging.ui.main_menu.adapter.MenuAdapter

class MainMenuFragment : Fragment() {
    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = resources.getString(R.string.app_name)
        setupAdapter()
        setupListeners()
    }

    private fun setupAdapter() {
        adapter = MenuAdapter(getNames<Title>())
        binding.menuRv.adapter = adapter
    }

    private fun setupListeners() {
        adapter.onItemClick = { position ->
            requireActivity().title = getNames<Title>()[position]
            val action = MainMenuFragmentDirections.toDataListFragment()
            action.type = position
            findNavController().navigate(action)
        }
    }

    private inline fun <reified T : Enum<T>> getNames() = enumValues<T>().map { it.name }
}