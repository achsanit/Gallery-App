package com.achsanit.utils

import android.view.View

// extension for set visibility of View
fun View.makeGone() {
    this.visibility = View.GONE
}
fun View.makeVisible() {
    this.visibility = View.VISIBLE
}