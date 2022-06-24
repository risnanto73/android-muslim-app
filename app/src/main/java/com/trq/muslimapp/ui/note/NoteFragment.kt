package com.trq.muslimapp.ui.note

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.trq.muslimapp.R
import com.trq.muslimapp.auth.network.ApiConfigRt
import com.trq.muslimapp.auth.ui.AuthActivity
import com.trq.muslimapp.databinding.NoteFragmentBinding
import com.trq.muslimapp.helpers.SharedPreference
import com.trq.muslimapp.ui.note.adapter.NoteAdapter
import com.trq.muslimapp.ui.note.model.ResponseNoteAll
import com.trq.muslimapp.ui.note.model.ResponseUserId
import kotlinx.android.synthetic.main.note_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteFragment : Fragment() {

    private lateinit var binding: NoteFragmentBinding
    lateinit var sharedPreference: SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NoteFragmentBinding.inflate(inflater, container, false)
        return binding.root

        val swipper = view?.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout.setOnRefreshListener {
            sharedPreference = SharedPreference(requireActivity())
            val user = sharedPreference.getUser()
            if (user != null) {
                binding.btnLogin.visibility = View.GONE
                binding.fabAdd.visibility = View.VISIBLE

                binding.fabAdd.setOnClickListener {
                    val intent = Intent(requireActivity(), AddNoteActivity::class.java)
                    startActivity(intent)
                }

                val user = sharedPreference.getUser()
                val userId = user?.id

                val recNote = view.findViewById<RecyclerView>(R.id.recNote)
                ApiConfigRt.instanceRetrofit.getNoteById(userId.toString())
                    .enqueue(object : Callback<ResponseUserId> {
                        override fun onResponse(
                            call: Call<ResponseUserId>,
                            response: Response<ResponseUserId>
                        ) {
                            if (response.isSuccessful) {
                                val data = response.body()
                                val dataNote = data?.mutabaah
                                val noteAdapter = NoteAdapter(dataNote, context!!)
                                recNote.apply {
                                    setHasFixedSize(true)
                                    layoutManager =
                                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                                    noteAdapter.notifyDataSetChanged()
                                    adapter = noteAdapter
                                }
                            }
                        }

                        override fun onFailure(call: Call<ResponseUserId>, t: Throwable) {
                            Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    })

            } else {
                binding.fabAdd.visibility = View.GONE
                binding.btnLogin.visibility = View.VISIBLE
                binding.btnLogin.setOnClickListener {
                    startActivity(Intent(requireActivity(), AuthActivity::class.java))
                }
            }
            swipeRefreshLayout.isRefreshing = false
        }

    }

    override fun onResume() {
        super.onResume()
        binding
    }
}