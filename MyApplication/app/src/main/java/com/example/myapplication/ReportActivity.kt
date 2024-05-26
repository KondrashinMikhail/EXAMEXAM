package com.example.myapplication

import android.content.Intent
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Table
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.database.Client
import com.example.myapplication.database.Database
import com.google.gson.Gson
import java.io.File

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_report)

        val buttonCancel: Button = findViewById(R.id.buttonCancel)
        val buttonReport: Button = findViewById(R.id.buttonReport)
        val dataList: ListView = findViewById(R.id.listView)

        val dbHelper = Database(this)
        dbHelper.writableDatabase
        val data: List<Client> =
            dbHelper.getAllClients()
                .filter { it.name.length == 5 }

        val dataListAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, data.map { it.name })
        dataList.adapter = dataListAdapter

        buttonCancel.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        buttonReport.setOnClickListener {
            createPdfWithTable(data)
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show()
        }
    }

    private fun createPdfWithTable(data: List<Client>) {
        val writer = PdfWriter("/storage/emulated/0/Download/report.pdf")
        val document = PdfDocument(writer)
        val doc = Document(document)

        val table = Table(3)
        table.startNewRow()
        table.addCell("ID")
        table.addCell("Name")

        data.forEach {
            table.startNewRow()
            table.addCell(it.id.toString())
            table.addCell(it.name)
        }

        doc.add(table)
        document.close()
    }

    private fun jsonExport(dbHelper: Database) {
        dbHelper.readableDatabase
        val clients: List<Client> = dbHelper.getAllClients()
        val file = File("/storage/emulated/0/Download/clients.json")
        val gson = Gson()
        val jsonString = gson.toJson(clients)
        file.writeText(jsonString)
    }

    private fun jsonImport(dbHelper: Database) {
        dbHelper.writableDatabase
        val gson = Gson()
        val readJson = File("/storage/emulated/0/Download/clients.json").readText()
        gson.fromJson(readJson, Array<Client>::class.java).forEach { dbHelper.addClient(it.name) }
    }
}