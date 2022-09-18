package com.tazkiyatech.animation.experiments

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val rootView: ViewGroup
        get() = findViewById(R.id.rootView)

    private val textView2: View
        get() = findViewById(R.id.textView2)

    private val textView3: View
        get() = findViewById(R.id.textView3)

    private val hideTextView2AndThenShowTextView3Button: View
        get() = findViewById(R.id.hideTextView2AndThenShowTextView3Button)

    private val hideTextView3AndThenShowTextView2Button: View
        get() = findViewById(R.id.hideTextView3AndThenShowTextView2Button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideTextView2AndThenShowTextView3Button.setOnClickListener { hideTextView2AndThenShowTextView3() }
        hideTextView3AndThenShowTextView2Button.setOnClickListener { hideTextView3AndThenShowTextView2() }
    }

    private fun hideTextView2AndThenShowTextView3() {
        if (textView2.isVisible) {
            hideViewAndThenShowAnother(textView2, textView3)
        }
    }

    private fun hideTextView3AndThenShowTextView2() {
        if (textView3.isVisible) {
            hideViewAndThenShowAnother(textView3, textView2)
        }
    }

    private fun hideViewAndThenShowAnother(viewToHide: View, viewToShow: View) {
        val transitionListener2 = object : LayoutTransition.TransitionListener {

            override fun startTransition(
                transition: LayoutTransition,
                container: ViewGroup,
                view: View,
                transitionType: Int
            ) {
                //nothing do
            }

            override fun endTransition(
                transition: LayoutTransition,
                container: ViewGroup,
                view: View,
                transitionType: Int
            ) {
                transition.removeTransitionListener(this)
            }
        }

        val transitionListener1 = object : LayoutTransition.TransitionListener {

            override fun startTransition(
                transition: LayoutTransition,
                container: ViewGroup,
                view: View,
                transitionType: Int
            ) {
                //nothing do
            }

            override fun endTransition(
                transition: LayoutTransition,
                container: ViewGroup,
                view: View,
                transitionType: Int
            ) {
                transition.removeTransitionListener(this)
                transition.addTransitionListener(transitionListener2)
                viewToShow.visibility = View.VISIBLE
            }
        }

        rootView.layoutTransition.addTransitionListener(transitionListener1)
        viewToHide.visibility = View.GONE
    }
}
