package com.oleg.mynotes.notedetail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.oleg.mynotes.R
import kotlinx.android.synthetic.main.activity_note_detail.*

@Suppress("UNUSED_EXPRESSION")
class NoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val noteId = intent.getLongExtra(EXTRA_NOTE_ID, 0)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


        if (savedInstanceState == null)
            initFragment(NoteDetailFragment.newInstance(noteId))
    }

    fun initFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val EXTRA_NOTE_ID  = "NOTE ID"
        fun newIntent(packageContext: Context, noteId: Long): Intent {
            val intent = Intent(packageContext, NoteDetailActivity::class.java)
            intent.putExtra(EXTRA_NOTE_ID, noteId)
            return intent
        }
    }
}
