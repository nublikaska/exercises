package com.nublikaska.exercises.base.ui

import androidx.lifecycle.ViewModel

interface ViewModelFactory<T : ViewModel> : (() -> T)