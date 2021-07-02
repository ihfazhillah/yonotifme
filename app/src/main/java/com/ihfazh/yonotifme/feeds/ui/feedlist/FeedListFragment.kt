package com.ihfazh.yonotifme.feeds.ui.feedlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.*
import com.ihfazh.yonotifme.R
import com.ihfazh.yonotifme.databinding.FragmentFeedListBinding
import com.ihfazh.yonotifme.feeds.domain.models.Item
import dagger.hilt.android.AndroidEntryPoint


@EpoxyModelClass(layout = R.layout.feed_list_item)
abstract class ItemModel: EpoxyModelWithHolder<ItemModel.Holder>(){

    @EpoxyAttribute lateinit var title: String
    @EpoxyAttribute lateinit var description: String
    @EpoxyAttribute lateinit var doClick: () -> Unit

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.title.text = title
        holder.description.text = description
        holder.parent.setOnClickListener { doClick() }
    }

    class Holder: EpoxyHolder() {
        lateinit var title: TextView
        lateinit var description: TextView
        lateinit var parent: View

        override fun bindView(itemView: View) {
            title = itemView.findViewById(R.id.text_title)
            description = itemView.findViewById(R.id.text_description)
            parent = itemView
        }

    }

}


class ItemController(
        private val onClickListener: ((item: Item) -> Unit)
): Typed2EpoxyController<List<Item>, Boolean>(){

    override fun buildModels(items: List<Item>, loadingMore: Boolean) {
        items.forEach{

            item {
                title(it.title)
                description(it.description)
                doClick { this@ItemController.onClickListener(it) }
                id(it.guid)
            }



        }
    }

}


@AndroidEntryPoint
class FeedListFragment: Fragment() {
    private var _binding: FragmentFeedListBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: FeedListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = ItemController(
                onClickListener = {item ->
                    run {
                        val action = FeedListFragmentDirections.actionFeedListFragmentToFeedDetailFragment(item.guid)
                        findNavController().navigate(action)
                    }
                }
        )
//        binding.rv.setControllerAndBuildModels(controller)
        binding.rv.setController(controller)
//        binding.rv.requestModelBuild()
        viewModel.list().observe(viewLifecycleOwner){
            controller.setData(it, false)
        }

//        binding.rv.withModels {
//            viewModel.list().observe(viewLifecycleOwner){ items ->
//                items.forEach{
//                    ItemModel_()
//                        .title(it.title)
//                        .description(it.description)
//                        .id(it.guid)
//                        .addTo(this)
//                }
//
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}