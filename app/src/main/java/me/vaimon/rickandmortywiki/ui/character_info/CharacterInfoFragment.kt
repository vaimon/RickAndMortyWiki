package me.vaimon.rickandmortywiki.ui.character_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import me.vaimon.rickandmortywiki.R
import me.vaimon.rickandmortywiki.databinding.FragmentCharacterInfoBinding
import me.vaimon.rickandmortywiki.ui.MainActivity


class CharacterInfoFragment : Fragment() {
    private lateinit var binding: FragmentCharacterInfoBinding

    private val navigationArgs: CharacterInfoFragmentArgs by navArgs()

    private val viewModel by lazy { ViewModelProvider(this)[CharacterInfoViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(inflater,container, false)

        viewModel.setCharacterInfo(navigationArgs.characterInfo)
        viewModel.characterInfo.value?.name?.let {
            (requireActivity() as MainActivity).setTitle(it)
        }

        setupInfoTitles()

        return binding.root
    }

    private fun setupInfoTitles(){
        binding.infoType.tvKey.text = getString(R.string.type)
        binding.infoGender.tvKey.text = getString(R.string.gender)
        binding.infoSpecies.tvKey.text = getString(R.string.species)
        binding.infoStatus.tvKey.text = getString(R.string.status)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.characterInfo.observe(viewLifecycleOwner){
            binding.infoType.tvValue.text = it.type
            binding.infoGender.tvValue.text = it.gender
            binding.infoSpecies.tvValue.text = it.species
            binding.infoStatus.tvValue.text = it.status
            binding.ivCharacterImage.load(it.imageUrl){
                transformations(CircleCropTransformation())
                crossfade(true)
                placeholder(R.drawable.placeholder)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}