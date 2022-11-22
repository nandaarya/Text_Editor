package com.dicoding.text_editor

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvTextEditors: RecyclerView
    private var list: ArrayList<TextEditor> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Text_Editor)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvTextEditors = findViewById(R.id.rv_texteditors)
        rvTextEditors.setHasFixedSize(true)

        list.addAll(TextEditorData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvTextEditors.layoutManager = LinearLayoutManager(this)
        val listTextEditorAdapter = ListTextEditorAdapter(list)
        rvTextEditors.adapter = listTextEditorAdapter

        listTextEditorAdapter.setOnItemClickCallback(object :
            ListTextEditorAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TextEditor) {
                showSelectedTextEditor(data)
            }
        })
    }

    private fun showRecyclerGrid() {
        rvTextEditors.layoutManager = GridLayoutManager(this, 2)
        val gridTextEditorAdapter = GridTextEditorAdapter(list)
        rvTextEditors.adapter = gridTextEditorAdapter

        gridTextEditorAdapter.setOnItemClickCallback(object :
            GridTextEditorAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TextEditor) {
                showSelectedTextEditor(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        rvTextEditors.layoutManager = LinearLayoutManager(this)
        val cardViewTextEditorAdapter = CardViewTextEditorAdapter(list)
        rvTextEditors.adapter = cardViewTextEditorAdapter

        cardViewTextEditorAdapter.setOnItemClickCallback(object :
            CardViewTextEditorAdapter.OnItemClickCallback {
            override fun onItemClicked(data: TextEditor) {
                showSelectedTextEditor(data)
            }
        })
    }

    private fun showSelectedTextEditor(textEditor: TextEditor) {
        Toast.makeText(this, "Kamu memilih " + textEditor.name, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("text_editor_name", textEditor.name)
        intent.putExtra("text_editor_img", textEditor.photo)
        intent.putExtra("text_editor_detail", textEditor.detail)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }

            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }

            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecyclerCardView()
            }

            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        setActionBarTitle(title)
    }
}