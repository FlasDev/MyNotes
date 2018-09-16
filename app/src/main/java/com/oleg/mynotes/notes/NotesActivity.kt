package com.oleg.mynotes.notes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.oleg.mynotes.R
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Список заметок"
        if (savedInstanceState == null)
            initFragment(NotesFragment.newInstance())
    }

    @SuppressLint("CommitTransaction")
    fun initFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit()
    }

    companion object {
        fun newIntent(packageContext: Context): Intent{
            val intent = Intent(packageContext, NotesActivity::class.java)
            return intent
        }
    }
}
