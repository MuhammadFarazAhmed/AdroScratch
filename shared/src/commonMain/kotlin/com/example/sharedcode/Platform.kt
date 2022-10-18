package com.example.sharedcode

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform