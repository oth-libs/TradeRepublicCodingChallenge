package io.traderepublic.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class LinearLayoutManager(context: Context, val animateItems: Boolean) : LinearLayoutManager(context) {
  override fun supportsPredictiveItemAnimations(): Boolean {
    return if (animateItems) true else super.supportsPredictiveItemAnimations()
  }
}