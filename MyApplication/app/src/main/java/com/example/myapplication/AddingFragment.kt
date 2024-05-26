package com.example.myapplication

import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.database.Client
import com.example.myapplication.database.Database

class AddingFragment : Fragment() {
    private val data: MutableList<String> = mutableListOf()
    private var clients: List<Client> = mutableListOf()

    private fun refreshTable(dbHelper: Database, dataListAdapter: ArrayAdapter<String>) {
        clients = dbHelper.getAllClients()
        dataListAdapter.clear()
        dataListAdapter.addAll(clients.map { it.name })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dbHelper = Database(requireContext())
        dbHelper.writableDatabase

        val editText: EditText = view.findViewById(R.id.editText)
        val addButton: Button = view.findViewById(R.id.addButton)
        val dataList: ListView = view.findViewById(R.id.dataList)
        val buttonReport: Button = view.findViewById(R.id.buttonPdf)

        val dataListAdapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, data)
        dataList.adapter = dataListAdapter

        refreshTable(dbHelper, dataListAdapter)

        dataList.setOnItemClickListener { _, _, i, _ ->
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.addingFragment, UpdateFragment(clients[i].id!!))
                .addToBackStack(null)
                .commit()
        }

        buttonReport.setOnClickListener {
            startActivity(Intent(activity, ReportActivity::class.java))
        }

        addButton.setOnClickListener {
            val text: String = editText.text.toString().trim()

            if (text != "") {
                dbHelper.addClient(text)
                refreshTable(dbHelper, dataListAdapter)

                Toast.makeText(requireActivity(), "Added $text", Toast.LENGTH_LONG).show()
                editText.setText("")
            }
        }
    }
}