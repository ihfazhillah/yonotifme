package com.ihfazh.yonotifme.feeds.ui.feeddetail

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ihfazh.yonotifme.databinding.FragmentFeedDetailBinding
import com.ihfazh.yonotifme.databinding.FragmentFeedListBinding
import com.ihfazh.yonotifme.feeds.ui.feedlist.FeedListViewModel
import com.ihfazh.yonotifme.feeds.ui.feedlist.ItemController
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern


@AndroidEntryPoint
class FeedDetailFragment: Fragment() {
    private var _binding: FragmentFeedDetailBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: FeedDetailViewModel by viewModels()
    private val args: FeedDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.feedId.postValue(args.id)
        viewModel.feedDetail.observe(viewLifecycleOwner){
            binding.title.text = it.title
            binding.description.text = HtmlCompat.fromHtml(it.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            binding.description.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}