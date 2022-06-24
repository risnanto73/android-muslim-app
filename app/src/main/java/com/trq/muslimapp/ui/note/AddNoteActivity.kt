package com.trq.muslimapp.ui.note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.trq.muslimapp.MainActivity
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityAddNoteBinding
import com.trq.muslimapp.helpers.SharedPreference
import com.trq.muslimapp.ui.note.model.ResponseNote

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Add Mutabaah"
        sharedPreference = SharedPreference(this)
        //get id user


        binding.btnAdd.setOnClickListener {
            val title = binding.noteTitle.text.toString()
            val content = binding.noteContent.text.toString()

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Please fill all field", Toast.LENGTH_SHORT).show()
            }

            val user = sharedPreference.getUser()
            val userId = user?.id.toString()

            ApiConfigRt.instanceRetrofit.addNote(userId, title, content)
                .enqueue(object : retrofit2.Callback<ResponseNote> {
                    override fun onFailure(call: retrofit2.Call<ResponseNote>, t: Throwable) {
                        Toast.makeText(this@AddNoteActivity, "Failed to add note", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: retrofit2.Call<ResponseNote>, response: retrofit2.Response<ResponseNote>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@AddNoteActivity, "Success to add note", Toast.LENGTH_SHORT).show()
                            onBackPressed()
                        } else {
                            Toast.makeText(this@AddNoteActivity, "Failed to add note", Toast.LENGTH_SHORT).show()
                        }
                    }
                })

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}