package hdtuan.com.person.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import hdtuan.com.person.models.Person

class PersonViewModel : ViewModel() {
    private lateinit var persons: MutableLiveData<ArrayList<Person>>
    private var mIndex = 0

    fun getPersons(): LiveData<ArrayList<Person>> {
        if (!::persons.isInitialized) {
            persons = MutableLiveData()
            loadPersons()
        }
        return persons
    }

    private fun loadPersons() {
        val person = Person()
        person.name = "Hoàng Đức Tuấn"
        person.age = 31
        persons.value = ArrayList()
        persons.value!!.add(person)
    }

    fun addPerson(person: Person) {
        persons.value!!.add(person)
    }

    fun nextPerson(): Person {
        mIndex++

        if (mIndex >= persons.value!!.size) mIndex = 0

        return persons.value!![mIndex]
    }

    fun backPerson(): Person {
        mIndex--
        if (mIndex < 0) mIndex = persons.value!!.size - 1

        return persons.value!![mIndex]
    }

    fun currentPerson(): Person {
        return persons.value!![mIndex]
    }
}