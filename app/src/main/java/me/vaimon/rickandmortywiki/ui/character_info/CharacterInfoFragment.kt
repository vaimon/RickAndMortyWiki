package me.vaimon.rickandmortywiki.ui.character_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import me.vaimon.rickandmortywiki.R
import me.vaimon.rickandmortywiki.databinding.FragmentCharacterInfoBinding
import me.vaimon.rickandmortywiki.ui.MainActivity
import me.vaimon.rickandmortywiki.ui.character_info.adapters.KeyValuePairRvAdapter
import org.w3c.dom.Entity


class CharacterInfoFragment : Fragment() {
    private lateinit var binding: FragmentCharacterInfoBinding

    private val navigationArgs: CharacterInfoFragmentArgs by navArgs()

    private val viewModel by lazy { ViewModelProvider(this)[CharacterInfoViewModel::class.java] }
    private val infoRvAdapter by lazy { KeyValuePairRvAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(inflater, container, false)

        binding.rvInfoTiles.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = infoRvAdapter
        }

        viewModel.setCharacterInfo(navigationArgs.characterInfo)
        viewModel.characterInfo.value?.name?.let {
            (requireActivity() as MainActivity).setTitle(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.characterInfo.observe(viewLifecycleOwner) {
            val keyValueList = listOf(
                getString(R.string.type) to it.type,
                getString(R.string.gender) to it.gender,
                getString(R.string.species) to it.species,
                getString(R.string.status) to it.status,
            )

            infoRvAdapter.replaceAllEntries(keyValueList)

            binding.ivCharacterImage.load(it.imageUrl) {
                transformations(CircleCropTransformation())
                crossfade(true)
                placeholder(R.drawable.placeholder)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}