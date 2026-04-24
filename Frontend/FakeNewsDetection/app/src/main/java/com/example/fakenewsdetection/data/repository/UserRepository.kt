package com.example.fakenewsdetection.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.fakenewsdetection.data.models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken // <-- REQUIRED for List deserialization

class UserRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("NewsAppPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveUser(user: User): Boolean {
        val users = getAllUsers().toMutableList()
        if (users.any { it.email == user.email }) {
            return false // User already exists
        }
        users.add(user)

        // Serialize the updated list of users and save it
        prefs.edit().putString("users", gson.toJson(users)).apply()
        return true
    }

    fun loginUser(email: String, password: String): Boolean {
        val users = getAllUsers()
        val user = users.find { it.email == email && it.password == password }
        if (user != null) {
            // Save the logged-in user session
            prefs.edit().putString("current_user", gson.toJson(user)).apply()
            return true
        }
        return false
    }

    fun getCurrentUser(): User? {
        val json = prefs.getString("current_user", null) ?: return null
        // Simple deserialization for a single User object
        return gson.fromJson(json, User::class.java)
    }

    fun logout() {
        prefs.edit().remove("current_user").apply()
    }

    private fun getAllUsers(): List<User> {
        val json = prefs.getString("users", "[]") ?: "[]"

        // Use TypeToken to correctly inform Gson about the generic List<User> type
        val type = TypeToken.getParameterized(List::class.java, User::class.java).type

        // Return the deserialized list, or an empty list if deserialization fails
        return gson.fromJson(json, type) ?: emptyList()
    }
}