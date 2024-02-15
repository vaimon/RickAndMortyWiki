package me.vaimon.rickandmortywiki.ui.character_list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.vaimon.rickandmortywiki.databinding.ItemCharacterBinding
import me.vaimon.rickandmortywiki.models.SeriesCharacter

class CharacterRecyclerViewAdapter(
    private val onItemClickListener: OnItemClickListener? = null
) : RecyclerView.Adapter<CharacterRecyclerViewAdapter.ViewHolder>() {

    private val characterList: MutableList<SeriesCharacter> = mutableListOf()

    private val onClickListener = View.OnClickListener {
        val item = it.tag as SeriesCharacter
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
        val item = characterList[position]

        holder.binding.tvName.text = item.name

        onClickListener.also {
            with(holder.itemView) {
                tag = item
                setOnClickListener(it)
            }
        }
    }

    override fun getItemCount(): Int = characterList.size

    fun replaceWithNewCharacters(newCharacterList: List<SeriesCharacter>) {
        val listDiff = DiffUtil.calculateDiff(CharactersDiffUtil(characterList, newCharacterList))
        characterList.clear()
        characterList.addAll(newCharacterList)
        listDiff.dispatchUpdatesTo(this)
    }

    val lastCharacterId: Int?
        get() = characterList.lastOrNull()?.id

    inner class ViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onCharacterClick(character: SeriesCharacter)
    }

    class CharactersDiffUtil(
        private val oldList: List<SeriesCharacter>,
        private val newList: List<SeriesCharacter>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }
    }
}