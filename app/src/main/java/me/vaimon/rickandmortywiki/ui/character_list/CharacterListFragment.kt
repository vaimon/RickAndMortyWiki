package me.vaimon.rickandmortywiki.ui.character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.vaimon.rickandmortywiki.databinding.FragmentCharacterListBinding
import me.vaimon.rickandmortywiki.models.SeriesCharacter
import me.vaimon.rickandmortywiki.ui.character_list.adapters.CharacterRecyclerViewAdapter


@AndroidEntryPoint
class CharacterListFragment: Fragment() {

    private lateinit var binding: FragmentCharacterListBinding
    private val characterListAdapter: CharacterRecyclerViewAdapter by lazy {
        CharacterRecyclerViewAdapter(
            object: CharacterRecyclerViewAdapter.OnItemClickListener{
                override fun onCharacterClick(character: SeriesCharacter) {
                    viewModel.onCharacterClick(character)
                }
            }
        )
    }

    private val viewModel: CharacterListViewModel by lazy {
        ViewModelProvider(this)[CharacterListViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView(){
        binding.rvCharacterList.apply {
            adapter = characterListAdapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val visibleItemCount: Int = (layoutManager as LinearLayoutManager).childCount
                    val totalItemCount: Int = (layoutManager as LinearLayoutManager).itemCount
                    val firstVisibleItemPosition: Int = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        viewModel.onListBottomReached(characterListAdapter.lastCharacterId)
                    }
                }
            })
        }
    }

    private fun setupObservers(){
        viewModel.characterList.observe(viewLifecycleOwner){
            characterListAdapter.replaceWithNewCharacters(it)
        }

        viewModel.uiState.observe(viewLifecycleOwner){
            it.errorMessage?.let { message ->
                Toast.makeText(context, message,Toast.LENGTH_SHORT).show()
                viewModel.onErrorMessageShown()
            }
            binding.pbLoadingIndicator.visibility = if(it.isDataLoading) View.VISIBLE else View.GONE
        }

        viewModel.detailsEndpoint.observe(viewLifecycleOwner){
            it?.let {navigateToDetailsScreen(it) }
        }
    }

    private fun navigateToDetailsScreen(character: SeriesCharacter){
        val action = CharacterListFragmentDirections.actionOpenCharacterInfo(character)
        findNavController().navigate(action)
        viewModel.onCharacterDetailsShown()
    }
}