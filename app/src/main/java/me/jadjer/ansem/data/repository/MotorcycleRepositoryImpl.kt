package me.jadjer.ansem.data.repository

import me.jadjer.ansem.data.model.entity.Motorcycle

class MotorcycleRepositoryImpl : MotorcycleRepository {

    override fun createMotorcycle(motorcycle: Motorcycle) {
        TODO("Not yet implemented")
    }

    override fun getMotorcycles(): List<Motorcycle> {
        TODO("Not yet implemented")
    }

    override fun getMotorcycle(motorcycleId: Int): Motorcycle {
        TODO("Not yet implemented")
    }

    override fun updateMotorcycle(motorcycle: Motorcycle) {
        TODO("Not yet implemented")
    }

    override fun deleteMotorcycle(motorcycle: Motorcycle) {
        TODO("Not yet implemented")
    }

//    private var _appDatabase = appDatabase
//    private var _observableMotorcycles = MediatorLiveData<List<MotorcycleEntity>>()
//
//    init {
//        _observableMotorcycles.addSource(
//            _appDatabase.motorcycleDao().getAllMotorcycles()
//        ) { motorcycleEntities ->
//            if (_appDatabase.getDatabaseCreated().value != null) {
//                _observableMotorcycles.postValue(motorcycleEntities);
//            }
//        }
//    }
//
//    /**
//     * Get the list of products from the database and get notified when the data changes.
//     */
//    fun getMotorcycles(): LiveData<List<MotorcycleEntity>> {
//        return _observableMotorcycles
//    }
//
//    fun loadMotorcycle(motorcycleId: Int): LiveData<MotorcycleEntity> {
//        return _appDatabase.motorcycleDao().getMotorcycle(motorcycleId)
//    }
//
//    fun searchMotorcycles(query: String): LiveData<List<MotorcycleEntity>> {
//        return _appDatabase.motorcycleDao().searchMotorcycles(query)
//    }
}