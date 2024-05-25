package com.likeminds.feed.android.core.utils

import android.animation.AnimatorListenerAdapter
import android.view.*
import android.view.animation.Animation
import android.view.animation.Transformation
import kotlin.math.hypot

object LMFeedAnimationUtils {

    @JvmStatic
    private val ANIMATION_DURATION_SHORT = 250

    @JvmStatic
    @JvmOverloads
    fun circleRevealView(view: View, duration: Int = ANIMATION_DURATION_SHORT) {
        // get the center for the clipping circle
        val cx = view.width
        val cy = view.height / 2

        // get the final radius for the clipping circle
        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        // create the animator for this view (the start radius is zero)
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius)

        anim.duration = if (duration > 0) duration.toLong() else ANIMATION_DURATION_SHORT.toLong()

        // make the view visible and start the animation
        view.visibility = View.VISIBLE
        anim.start()
    }

    @JvmStatic
    fun circleHideView(view: View, listenerAdapter: AnimatorListenerAdapter) {
        circleHideView(view, ANIMATION_DURATION_SHORT, listenerAdapter)
    }

    @JvmStatic
    fun circleHideView(view: View, duration: Int, listenerAdapter: AnimatorListenerAdapter) {
        // get the center for the clipping circle
        val cx = view.width
        val cy = view.height / 2

        // get the initial radius for the clipping circle
        val initialRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        // create the animation (the final radius is zero)
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0f)

        // make the view invisible when the animation is done
        anim.addListener(listenerAdapter)

        anim.duration = if (duration > 0) duration.toLong() else ANIMATION_DURATION_SHORT.toLong()

        // start the animation
        anim.start()
    }

    @JvmStatic
    fun expand(view: View){
        val matchParentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec((view.parent as View).width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight = view.measuredHeight

        view.layoutParams.height = 1
        view.visibility = View.VISIBLE
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                view.layoutParams.height =
                    if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                view.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = ((targetHeight / view.context.resources.displayMetrics.density).toLong())
        view.startAnimation(a)
    }

    @JvmStatic
    fun collapse(view: View){
        val initialHeight = view.measuredHeight
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                if (interpolatedTime == 1f) {
                    view.visibility = View.GONE
                } else {
                    view.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // Collapse speed of 1dp/ms
        a.duration = ((initialHeight / view.context.resources.displayMetrics.density).toLong())
        view.startAnimation(a)
    }
}