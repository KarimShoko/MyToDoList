package com.example.mytodolist.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytodolist.R
import com.example.mytodolist.databinding.FragmentNoteItemBinding
import com.example.mytodolist.domain.NoteItem
import com.example.mytodolist.domain.Priority
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class NoteItemFragment : Fragment() {
    private var _binding: FragmentNoteItemBinding? = null
    private val binding: FragmentNoteItemBinding
        get() = _binding ?: throw RuntimeException("FragmentNoteItemBinding=null")

    private lateinit var viewModel: NoteItemViewModel

    private var screenMode: String = MODE_UNKNOWN
    private var noteItemId: Int = NoteItem.UNDEFINED_ID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NoteItemViewModel::class.java]
        binding.rbLow.isChecked = true
        observeViewModel()
        parseParams()
        addTextChangeListeners()
        when (screenMode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun launchEditMode() {
        viewModel.getNoteItem(noteItemId)
        viewModel.noteItem.observe(viewLifecycleOwner) {
            binding.etName.setText(it.name)
            binding.etText.setText(it.text)
        }
        binding.saveButton.setOnClickListener {
            val noteName = binding.etName.text
            val noteText = binding.etText.text
            val priority = getPriority()
            viewModel.editNoteItem(
                inputName = noteName.toString(),
                inputText = noteText.toString(),
                priority = priority
            )
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            val noteName = binding.etName.text
            val noteText = binding.etText.text
            val priority = getPriority()
            viewModel.addNoteItem(
                inputName = noteName.toString(),
                inputText = noteText.toString(),
                priority = priority
            )
        }

    }

    private fun getPriority(): Priority {
        val priority = if (binding.rbLow.isChecked) {
            Priority.LOW
        } else if (binding.rbMedium.isChecked) {
            Priority.MEDIUM
        } else {
            Priority.HIGH
        }
        return priority
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Режим отображения параметров отсутствует")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Неизвестный режим экрана:$mode")
        }

        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(NOTE_ITEM_ID))
                throw RuntimeException("Параметр shop item id отсутствует")
        }
        noteItemId = args.getInt(NOTE_ITEM_ID, NoteItem.UNDEFINED_ID)
    }

    private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it == true) {
                getString(R.string.error_invalid_name)
            } else {
                null
            }
            binding.tilName.error = message
        }
        viewModel.errorInputText.observe(viewLifecycleOwner) {
            val message = if (it == true) {
                getString(R.string.error_invalid_text)
            } else {
                null
            }
            binding.tilText.error = message
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun addTextChangeListeners() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.resetErrorInputName()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        binding.etText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.resetErrorInputText()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val NOTE_ITEM_ID = "extra_note_item_id"
        private const val MODE_UNKNOWN = ""


        fun newInstanceAddItem() = NoteItemFragment().apply {
            arguments = Bundle().apply {
                putString(SCREEN_MODE, MODE_ADD)
            }
        }

        fun newInstanceEditItem(noteItemId: Int) = NoteItemFragment().apply {
            arguments = Bundle().apply {
                putString(SCREEN_MODE, MODE_EDIT)
                putInt(NOTE_ITEM_ID, noteItemId)
            }
        }
    }
}