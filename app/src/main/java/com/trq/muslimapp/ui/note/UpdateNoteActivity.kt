package com.trq.muslimapp.ui.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.databinding.ActivityUpdateNoteBinding
import com.trq.muslimapp.helpers.SharedPreference
import com.trq.muslimapp.ui.note.model.ResponseNote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_update_note)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Update Mutabaah"

        sharedPreference = SharedPreference(this)
        val user = sharedPreference.getUser()
        val userId = user?.id

        //get data from passed intent
        val noteId = intent.getIntExtra("id", 0)
        val noteTitle = intent.getStringExtra("catatan")
        val noteContent = intent.getStringExtra("deskripsi")

        val idd = noteId

        //set data to views
        binding.noteId.setText(noteId.toString())
        binding.userId.setText(userId.toString())
        binding.noteTitle.setText(noteTitle)
        binding.noteContent.setText(noteContent)

        binding.btnUpdate.setOnClickListener {
            ApiConfigRt.instanceRetrofit.updateNote(
                binding.noteId.text.toString(),
                binding.userId.text.toString(),
                binding.noteTitle.text.toString(),
                binding.noteContent.text.toString()
            ).enqueue(object : Callback<ResponseNote> {
                override fun onFailure(call: retrofit2.Call<ResponseNote>, t: Throwable) {
                    Toast.makeText(this@UpdateNoteActivity, "Error", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: retrofit2.Call<ResponseNote>,
                    response: retrofit2.Response<ResponseNote>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@UpdateNoteActivity, "Success", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    } else {
                        Toast.makeText(this@UpdateNoteActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        binding.btnDelete.setOnClickListener {
            ApiConfigRt.instanceRetrofit.deleteNote(idd)
                .enqueue(object : Callback<ResponseNote> {
                    override fun onResponse(
                        call: Call<ResponseNote>,
                        response: Response<ResponseNote>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@UpdateNoteActivity, "Success", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        } else {
                            Toast.makeText(this@UpdateNoteActivity, "Error", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseNote>, t: Throwable) {
                        Toast.makeText(
                            this@UpdateNoteActivity,
                            t.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}