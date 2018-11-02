package hdtuan.com.person.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hdtuan.com.person.R
import hdtuan.com.person.exceptions.AgeWrongException
import hdtuan.com.person.exceptions.NameEmptyException
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
        mViewModel.getPersons().observe(this, Observer {
            updateUI(mViewModel.currentPerson())
        })

        btnAdd.setOnClickListener {
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
        val name = edtName.text.toString().trim()
        val age = edtAge.text.toString().trim()

        try {
            mViewModel.addPerson(name, age = age.toInt())
            edtName.setText("")
            edtAge.setText("")
        } catch (nameError: NameEmptyException) {
            edtName.error = "Chưa nhập tên"
        } catch (ageError: AgeWrongException) {
            edtAge.error = "Chưa nhập tuổi"
        }

    }

    private fun updateUI(person: Person) {
        txvName.text = person.name
        txvAge.text = person.age.toString()
    }

}
