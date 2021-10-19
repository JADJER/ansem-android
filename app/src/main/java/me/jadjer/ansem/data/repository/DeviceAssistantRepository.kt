package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.model.repository.Device

interface DeviceAssistantRepository {
    fun getAllAvailiblesDevices() : List<Device>
}