package com.inmy.products.ui.address

import androidx.lifecycle.*
import com.inmy.products.Products
import com.inmy.products.data.model.HomeRepository
import com.inmy.products.data.room.data.Address
import com.inmy.products.data.room.data.AddressRepository
import kotlinx.coroutines.launch

class AddressViewModel(private val addressRepository: AddressRepository): ViewModel() {

    val allAddress: LiveData<List<Address>> = addressRepository.allAddress.asLiveData()

    fun insert(address: Address) = viewModelScope.launch {
        addressRepository.insert(address)
    }
}

class AddressViewModelFactory(private val addressRepository: AddressRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddressViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AddressViewModel(addressRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}