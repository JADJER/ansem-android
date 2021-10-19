package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.model.entity.Request

interface RequestRepository {
    fun getAll() : List<Request>
}