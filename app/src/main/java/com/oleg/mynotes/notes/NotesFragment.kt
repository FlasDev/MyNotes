package com.oleg.mynotes.notes


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.GridLayout
import android.widget.LinearLayout
import com.oleg.mynotes.R
import com.oleg.mynotes.addnote.AddNoteActivity
import com.oleg.mynotes.data.AppDatabase
import com.oleg.mynotes.data.Note
import com.oleg.mynotes.data.NoteDataDao
import com.oleg.mynotes.data.NoteRepository
import com.oleg.mynotes.notedetail.NoteDetailActivity
import com.oleg.mynotes.utilities.InjectorsUntils
import com.oleg.mynotes.utilities.runOnIoThread
import kotlinx.android.synthetic.main.fragment_notes.*

@SuppressLint("RestrictedApi")
class NotesFragment : Fragment(), NotesContract.View {


    private lateinit var actionListener: NotesContract.UserActionsListener

    private lateinit var adapter: ListNoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionListener = NotesPresenter(InjectorsUntils.provideNoteRepository(context!!), this)
        adapter = ListNoteAdapter(ArrayList(0), itemListener, itemDeleteListener)

        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_notes, container, false)
        val fab = activity?.findViewById<FloatingActionButton>(R.id.fab_add_notes)

        val recyclerView = view.findViewById<RecyclerView>(R.id.notes_list)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(context!!, 2)
        fab?.setOnClickListener {
            actionListener.addNewNote()
        }
        return view
    }

    private val itemListener = object :ListNoteAdapter.NoteItemListener{
        override fun onNoteClick(clickedNote: Note) {
            actionListener.openNoteDetail(clickedNote)
        }
    }

    private val itemDeleteListener = object: ListNoteAdapter.NoteItemDeleteListener{
        override fun onDeleteNoteClick(deleteNote: Note) {
            actionListener.deleteNote(deleteNote)
        }
    }

    override fun showAddNote() {
        startActivityForResult(AddNoteActivity.newIntent(context!!), REQUEST_ADD_NOTE)
    }

    override fun showNotes(note: List<Note>) {
        activity?.runOnUiThread {
            adapter.setNotes(note)
        }
    }

    override fun showDeleteNote() {
        activity?.runOnUiThread {
            Snackbar.make(view!!, getString(R.string.delete_note_message), Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun showNoteDetailUi(noteId: Long) {
        startActivity(NoteDetailActivity.newIntent(context!!, noteId))
    }

    override fun showEmptyNoteListVisibility(visibility: Int) {
        activity?.runOnUiThread {
            note_empty_list.visibility = visibility
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (REQUEST_ADD_NOTE == requestCode && Activity.RESULT_OK == resultCode){
            Snackbar.make(view!!, getString(R.string.successfully_saved_note_message),
                    Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.notes_menu, menu)
        val search = menu?.findItem(R.id.menu_notes_search)
        val searchView: SearchView = search?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                actionListener.findNote(newText!!)
                return true
            }

        })
    }

    override fun onResume() {
        super.onResume()
        actionListener.loadNotes()
    }

    companion object {
        private val REQUEST_ADD_NOTE = 1
        fun newInstance(): NotesFragment = NotesFragment()
    }


}
