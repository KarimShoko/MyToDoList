package com.example.mytodolist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoteListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteListFragment : Fragment() {
    private lateinit var vievModel: NoteListViewModel
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[NoteListViewModel::class.java]
        setupRecyclerView()
        viewModel.noteList.observe(viewLifecycleOwner) {
            noteListAdapter.noteList = it
        }
    }

    private fun setupRecyclerView() {
        val rvNoteList = view?.findViewById<RecyclerView>(R.id.rv_note_list)//recyclerView
        noteListAdapter = NoteListAdapter()
        rvNoteList?.adapter = noteListAdapter//устанавливаем в RV адаптер
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
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NoteListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NoteListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}