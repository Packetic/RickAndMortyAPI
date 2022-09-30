package com.example.rickandmortyapi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RickAndMortyAPI: Application() {

    companion object {
        private lateinit var instance: RickAndMortyAPI
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}