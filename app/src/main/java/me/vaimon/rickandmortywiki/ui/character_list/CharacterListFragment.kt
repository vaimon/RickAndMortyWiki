package me.vaimon.rickandmortywiki.ui.character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.vaimon.rickandmortywiki.databinding.FragmentCharacterListBinding
import me.vaimon.rickandmortywiki.ui.character_list.adapters.CharactersRecyclerViewAdapter
import me.vaimon.rickandmortywiki.models.CharacterBaseInfo


@AndroidEntryPoint
class CharacterListFragment: Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val characterListAdapter: CharactersRecyclerViewAdapter by lazy {
        CharactersRecyclerViewAdapter(
            object: CharactersRecyclerViewAdapter.OnItemClickListener{
                override fun onCharacterClick(character: CharacterBaseInfo) {
                    val action = CharacterListFragmentDirections.actionOpenCharacterInfo(character)
                    findNavController().navigate(action)
                }
            }
        )
    }

    private val viewModel: CharacterListViewModel by viewModels<CharacterListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
//        viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCharacterList.apply {
            adapter = characterListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel?.characterList?.observe(viewLifecycleOwner){
            characterListAdapter.replaceAllCharacters(it)
        }

        viewModel?.fetchCharacters()
    }
}