package com.likeminds.feed.android.core.ui.widgets.user.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.likeminds.feed.android.core.databinding.LmFeedUserViewBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.user.style.LMFeedUserViewStyle
import com.likeminds.feed.android.core.universalfeed.model.LMFeedUserViewData
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.user.LMFeedUserImageUtil

class LMFeedUserView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding: LmFeedUserViewBinding = LmFeedUserViewBinding.inflate(inflater, this, true)

    fun setStyle(userViewStyle: LMFeedUserViewStyle) {

        userViewStyle.apply {
            customizeUserImage(userImageViewStyle)
            customizeUserName(userNameViewStyle)
            customizeCustomTitle(userTitleViewStyle)
        }
    }

    private fun customizeUserImage(userImageViewStyle: LMFeedImageStyle) {
        binding.ivUserImage.setStyle(userImageViewStyle)
    }

    private fun customizeUserName(userNameViewStyle: LMFeedTextStyle) {
        binding.tvUserName.setStyle(userNameViewStyle)
    }

    private fun customizeCustomTitle(userTitleViewStyle: LMFeedTextStyle?) {
        binding.apply {
            if (userTitleViewStyle == null) {
                viewDot.hide()
                tvUserTitle.hide()
            } else {
                tvUserTitle.setStyle(userTitleViewStyle)
            }
        }
    }

    /**
     * Sets user image view.
     *
     * @param user - data of the author.
     */
    fun setUserImage(user: LMFeedUserViewData) {
        var userImageViewStyle =
            LMFeedStyleTransformer.postViewStyle.postHeaderViewStyle.authorImageViewStyle

        if (userImageViewStyle.placeholderSrc == null) {
            userImageViewStyle = userImageViewStyle.toBuilder().placeholderSrc(
                LMFeedUserImageUtil.getNameDrawable(
                    user.sdkClientInfoViewData.uuid,
                    user.name,
                    userImageViewStyle.isCircle,
                ).first
            ).build()
        }
        binding.ivUserImage.setImage(user.imageUrl, userImageViewStyle)
    }

    /**
     * Sets the name of the user
     *
     * @param userName - string to be set for name of user.
     */
    fun setUserName(userName: String) {
        binding.tvUserName.text = userName
    }

    /**
     * Sets the title of the user
     *
     * @param userTitle - string to be set for title of user.
     */
    fun setUserTitle(userTitle: String?) {
        binding.apply {
            if (userTitle.isNullOrEmpty()) {
                tvUserTitle.hide()
                viewDot.hide()
            } else {
                tvUserTitle.show()
                viewDot.show()
                tvUserTitle.text = userTitle
            }
        }
    }
}