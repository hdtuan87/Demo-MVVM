package hdtuan.com.person

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hdtuan.com.person.models.Person
import hdtuan.com.person.viewModel.PersonViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        mViewModel.getPersons().observe(this, Observer { _ ->
            updateUI(mViewModel.currentPerson())
        })

        btnAdd.setOnClickListener { _ ->
            addPerson()
        }

        btnNext.setOnClickListener {
            updateUI(mViewModel.nextPerson())
        }

        btnBack.setOnClickListener {
            updateUI(mViewModel.backPerson())
        }
    }

    private fun addPerson() {
        val person = Person()
        person.name = edtName.text.toString().trim()
        val age = edtAge.text.toString().trim()
        if (person.name.isEmpty()) {
            edtName.error = "Chưa nhập tên"
            return
        }

        if (age.isEmpty()) {
            edtAge.error = "Chưa nhập tuổi"
            return
        } else {
            person.age = age.toInt()
        }
        edtName.setText("")
        edtAge.setText("")
        mViewModel.addPerson(person)
    }

    private fun updateUI(person: Person) {
        txvName.text = person.name
        txvAge.text = person.age.toString()
    }

}
