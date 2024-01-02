package com.likeminds.feed.android.integration.universalfeed.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.likeminds.feed.android.integration.databinding.LmFeedFragmentUniversalFeedBinding
import com.likeminds.feed.android.integration.util.StyleTransformer
import com.likeminds.feed.android.integration.util.extensions.findListener
import com.likeminds.feed.android.ui.base.styles.setStyle

open class LMFeedUniversalFeedFragment : Fragment() {

    protected var createNewPostClickListener: CreateNewPostClickListener? = null
    private lateinit var binding: LmFeedFragmentUniversalFeedBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        createNewPostClickListener = findListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LmFeedFragmentUniversalFeedBinding.inflate(layoutInflater)
        setCreateNewPostButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        createNewPostClickListener = null
        super.onDetach()
    }

    protected open fun setCreateNewPostButton() {
        binding.fabNewPost.apply {
            val style = StyleTransformer.universalFeedFragmentViewStyle.createNewPostButtonViewStyle

            Log.d(
                "PUI", """
                style: $style
            """.trimIndent()
            )

            setStyle(style)
            setOnClickListener {
                if (createNewPostClickListener != null) {
                    createNewPostClickListener?.onCreateNewPostClick()
                } else {
                    Log.d("PUI", "default createNewPostClickListener")
                }
            }
        }
    }

    public fun interface CreateNewPostClickListener {
        public fun onCreateNewPostClick()
    }
}