package me.vaimon.rickandmortywiki.ui.character_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.vaimon.rickandmortywiki.R
import me.vaimon.rickandmortywiki.databinding.FragmentCharacterInfoBinding


class CharacterInfo : Fragment() {
    private lateinit var binding: FragmentCharacterInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(inflater,container, false)
        return binding.root
    }
}