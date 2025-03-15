package com.example.mytodolist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.R
import com.example.mytodolist.presentation.adapters.NoteListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteListFragment : Fragment() {
    private lateinit var viewModel: NoteListViewModel
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonAdd = view.findViewById<FloatingActionButton>(R.id.button_add_note_item)
        buttonAdd.setOnClickListener {
            launchNoteItemAddFragment()
        }
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[NoteListViewModel::class.java]
        viewModel.noteList.observe(viewLifecycleOwner) {
            noteListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val rvNoteList = view?.findViewById<RecyclerView>(R.id.rv_note_list)
        noteListAdapter = NoteListAdapter()
        rvNoteList?.adapter = noteListAdapter
        rvNoteList?.recycledViewPool?.setMaxRecycledViews(
            NoteListAdapter.VIEW_TYPE_LOW,
            NoteListAdapter.MAX_POOL_SIZE
        )
        rvNoteList?.recycledViewPool?.setMaxRecycledViews(
            NoteListAdapter.VIEW_TYPE_MEDIUM, NoteListAdapter.MAX_POOL_SIZE
        )
        rvNoteList?.recycledViewPool?.setMaxRecycledViews(
            NoteListAdapter.VIEW_TYPE_HIGH,
            NoteListAdapter.MAX_POOL_SIZE
        )
        noteListAdapter.onNoteItemClickListener = {
            launchNoteItemEditFragment(it.id)
        }
        setupSwipeToDelete(rvNoteList)
    }

    private fun setupSwipeToDelete(rvShopList: RecyclerView?) {
        val swipeToDelete =
            object :
                SwipeToDelete(requireContext(), 0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val item = noteListAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteNoteItem(item)
                }
            }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun launchNoteItemEditFragment(noteItemId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, NoteItemFragment.newInstanceEditItem(noteItemId))
            .addToBackStack(null)
            .commit()
    }

    private fun launchNoteItemAddFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, NoteItemFragment.newInstanceAddItem())
            .addToBackStack(null)
            .commit()
    }

}