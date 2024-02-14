package me.vaimon.rickandmortywiki.ui.character_info.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.vaimon.rickandmortywiki.databinding.ItemInfoBinding
import me.vaimon.rickandmortywiki.models.SeriesCharacter

class KeyValuePairRvAdapter(
    private val onItemClickListener: OnItemClickListener? = null
) : RecyclerView.Adapter<KeyValuePairRvAdapter.ViewHolder>() {

    private val keyValuePairsData: MutableList<Pair<String, String>> = mutableListOf()

    private val onClickListener = View.OnClickListener{
        val item = it.tag as SeriesCharacter
        onItemClickListener?.onPairClick(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = keyValuePairsData[position]

        holder.binding.tvKey.text = item.first
        holder.binding.tvValue.text = item.second

        onClickListener.also {
            with(holder.itemView) {
                tag = item
                setOnClickListener(it)
            }
        }
    }

    override fun getItemCount(): Int = keyValuePairsData.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceAllEntries(newCharacterList: List<Pair<String, String>>){
        keyValuePairsData.clear()
        keyValuePairsData.addAll(newCharacterList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemInfoBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onPairClick(character: SeriesCharacter)
    }

}