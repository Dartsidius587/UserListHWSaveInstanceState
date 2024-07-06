package com.example.userlisthwsaveinstancestate

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {



    private lateinit var userViewModel: UserViewModel
    private lateinit var toolBarMainTB: Toolbar
    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
    private lateinit var saveBTN: Button
    private lateinit var listUsersLV: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userViewModel.listUsers)

        userViewModel.userListViewModel.observe(this) {
            adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, userViewModel.listUsers)
        }

        listUsersLV.adapter = adapter

        saveBTN.setOnClickListener {
            if(nameET.text.isEmpty() || ageET.text.isEmpty()) return@setOnClickListener
            userViewModel.listUsers.add(User(nameET.text.toString(), ageET.text.toString()))
            adapter.notifyDataSetChanged()
            nameET.text.clear()
            ageET.text.clear()
        }

        listUsersLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val user = adapter.getItem(position)
                adapter.remove(user)
                Toast.makeText(this, "Запись о пользователе: $user удалена.", Toast.LENGTH_LONG)
                    .show()
            }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_exit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        toolBarMainTB = findViewById(R.id.toolBarMainTB)
        setSupportActionBar(toolBarMainTB)
        title = "Каталог пользователей"
        toolBarMainTB.setLogo(R.drawable.users_ic)

        nameET = findViewById(R.id.nameET)
        ageET = findViewById(R.id.ageET)
        saveBTN = findViewById(R.id.saveBTN)
        listUsersLV = findViewById(R.id.listUsersLV)
    }
}