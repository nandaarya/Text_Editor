package com.dicoding.text_editor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvTextEditors: RecyclerView
    private var list: ArrayList<TextEditor> = arrayListOf()
    private  var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvTextEditors = findViewById(R.id.rv_heroes)
        rvTextEditors.setHasFixedSize(true)

        list.addAll(TextEditorData.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvTextEditors.layoutManager = LinearLayoutManager(this)
        val listTextEditorAdapter = ListTextEditorAdapter(list)
        rvTextEditors.adapter = listTextEditorAdapter
    }

    private fun showRecyclerGrid() {
        rvTextEditors.layoutManager = GridLayoutManager (this, 2)
        val gridTextEditorAdapter = GridTextEditorAdapter(list)
        rvTextEditors.adapter = gridTextEditorAdapter
    }

    private  fun showRecyclerCardView() {
        rvTextEditors.layoutManager = LinearLayoutManager(this)
        val cardViewTextEditorAdapter = CardViewTextEditorAdapter(list)
        rvTextEditors.adapter = cardViewTextEditorAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private  fun setActionBarTitle(title: String) {
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
        }
        setActionBarTitle(title)
    }
}