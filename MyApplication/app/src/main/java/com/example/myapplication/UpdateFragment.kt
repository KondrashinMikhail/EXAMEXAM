package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.Client
import com.example.myapplication.database.Database

class UpdateFragment(private var clientId: Long? = null) : Fragment() {

    private fun backTransition() =
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.addingFragment, AddingFragment())
            .addToBackStack(null)
            .commit()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("clientId", clientId!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedInstanceState?.let { clientId = it.getLong("clientId") }
        return inflater.inflate(R.layout.fragment_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbHelper = Database(requireContext())
        dbHelper.writableDatabase

        val idTextView: TextView = view.findViewById(R.id.idTextView)
        val nameEditText: EditText = view.findViewById(R.id.nameEditText)
        val updateButton: Button = view.findViewById(R.id.updateButton)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
        val cancelButton: Button = view.findViewById(R.id.cancelButton)
        val client: Client? = dbHelper.getById(clientId!!)

        idTextView.text = getString(R.string.id_description, clientId.toString())
        nameEditText.setText(client!!.name)

        val sourceText: String = client.name

        updateButton.setOnClickListener {
            val newText: String = nameEditText.text.toString()
            dbHelper.updateClient(clientId!!, newText)
            Toast.makeText(requireActivity(), "Updated $sourceText -> $newText", Toast.LENGTH_LONG)
                .show()

            backTransition()
        }

        deleteButton.setOnClickListener {
            dbHelper.deleteUser(clientId!!)
            Toast.makeText(requireActivity(), "Deleted $sourceText", Toast.LENGTH_LONG).show()
            backTransition()
        }

        cancelButton.setOnClickListener {
            backTransition()
        }
    }
}