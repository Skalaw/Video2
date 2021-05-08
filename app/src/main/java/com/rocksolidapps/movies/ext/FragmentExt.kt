package com.rocksolidapps.movies.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope

val Fragment.viewCoroutineScope: LifecycleCoroutineScope
    get() = viewLifecycleOwner.lifecycleScope