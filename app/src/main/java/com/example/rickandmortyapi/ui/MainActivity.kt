package com.example.rickandmortyapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.domain.room.CharacterDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), ProvideDataBase {

    private lateinit var navController: NavController
    private lateinit var characterDb: CharacterDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterDb = CharacterDatabase.getDatabase(this)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)
    }

    override fun provideDataBase(): CharacterDatabase {
        characterDb = CharacterDatabase.getDatabase(this)
        return characterDb
    }

//    companion object {
//        val characterDb = CharacterDatabase.getDatabase(this)
//    }
}