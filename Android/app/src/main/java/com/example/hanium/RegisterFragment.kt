package com.example.hanium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hanium.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance().reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnRegister.setOnClickListener {
                val email = etId.text.toString()
                val password = etPass.text.toString()

                // Register the user with Firebase Authentication
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val userId = auth.currentUser?.uid ?: ""
                            val userReference = database.child("users").child(userId)
                            userReference.child("email").setValue(email)
                            userReference.child("password").setValue(password)

                            activity?.supportFragmentManager?.popBackStack()
                            Toast.makeText(requireContext(), "회원 가입 완료!", Toast.LENGTH_LONG).show()
                        } else {
                            // User registration failed
                            Toast.makeText(requireContext(), "회원 가입 실패", Toast.LENGTH_LONG).show()
                        }
                    }
            }

            btnValidate.setOnClickListener {
                Toast.makeText(requireContext(), "사용할 수 있는 아이디 입니다.", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}