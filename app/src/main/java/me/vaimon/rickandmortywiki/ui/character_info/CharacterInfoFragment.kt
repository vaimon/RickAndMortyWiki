package me.vaimon.rickandmortywiki.ui.character_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import me.vaimon.rickandmortywiki.databinding.FragmentCharacterInfoBinding
import me.vaimon.rickandmortywiki.ui.MainActivity


class CharacterInfoFragment : Fragment() {
    private lateinit var binding: FragmentCharacterInfoBinding

    val navigationArgs: CharacterInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(inflater,container, false)

        (requireActivity() as MainActivity).setTitle(navigationArgs.characterInfo.name)
        return binding.root
    }
}