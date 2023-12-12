package hu.ait.spyloop.ui.screen.setup.categories

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.ait.spyloop.R
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {
    val categoriesList: List<String> = context.resources.getStringArray(R.array.categories).asList()

    val locationsList: List<String> = context.resources.getStringArray(R.array.locations).asList()

    val householdItemsList: List<String> =
        context.resources.getStringArray(R.array.household_items).asList()

    val hobbiesList: List<String> = context.resources.getStringArray(R.array.hobbies).asList()

    val professionsList: List<String> =
        context.resources.getStringArray(R.array.professions).asList()

    val animalsList: List<String> = context.resources.getStringArray(R.array.animals).asList()

    fun getAllCategories(): List<String> {
        return categoriesList
    }

    fun getWord(category: String): String {
        return when (category) {
            "Locations" -> locationsList.random()
            "Household Items" -> householdItemsList.random()
            "Hobbies" -> hobbiesList.random()
            "Professions" -> professionsList.random()
            "Animals" -> animalsList.random()
            else -> ""
        }
    }
}



