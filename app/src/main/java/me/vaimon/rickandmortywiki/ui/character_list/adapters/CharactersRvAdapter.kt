package me.vaimon.rickandmortywiki.ui.character_list.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.vaimon.rickandmortywiki.databinding.ItemCharacterBinding
import me.vaimon.rickandmortywiki.models.CharacterBaseInfo

class CharactersRecyclerViewAdapter(
    private val onItemClickListener: OnItemClickListener? = null
) : RecyclerView.Adapter<CharactersRecyclerViewAdapter.ViewHolder>() {

    val characters: MutableList<CharacterBaseInfo> = mutableListOf()

    private val onClickListener = View.OnClickListener{
        val item = it.tag as CharacterBaseInfo
        onItemClickListener?.onCharacterClick(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = characters[position]

        holder.binding.tvName.text = item.name

        onClickListener.also {
            with(holder.itemView) {
                tag = item
                setOnClickListener(it)
            }
        }
    }

    override fun getItemCount(): Int = characters.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAllCharacters(newCharacterList: List<CharacterBaseInfo>){
        characters.clear()
        characters.addAll(newCharacterList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onCharacterClick(character: CharacterBaseInfo)
    }

}