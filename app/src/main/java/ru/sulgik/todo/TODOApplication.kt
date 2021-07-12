package ru.sulgik.todo

import android.app.Application
import ru.sulgik.todo.di.startDI

class TODOApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startDI()
    }
}