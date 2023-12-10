package hu.ait.spyloop.ui.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
) : ViewModel() {
    val categoriesList: List<String> =
        listOf("City Locations", "Household Items", "Hobbies", "Professions", "Animals")

    val cityLocationsList: List<String> = listOf(
        "Beach", "Park", "Library", "Airport", "Restaurant",
        "School", "Hospital", "Museum", "Gym", "Movie Theater",
        "Zoo", "Grocery Store", "Stadium", "Mall", "Police Station",
        "Farm", "Desert", "Mountain", "Space Station", "Amusement Park"
    )


    val householdItemsList: List<String> = listOf(
        "Refrigerator", "Microwave", "Vacuum Cleaner", "Blender", "Coffee Maker",
        "Toaster", "Dishwasher", "Washing Machine", "Couch", "Dining Table",
        "Bedside Lamp", "Clock", "Mirror", "Bookshelf", "Sofa", "Cutting Board",
        "Trash Can", "Iron", "Shower Curtain", "Tissue Box"
    )

    val hobbiesList: List<String> = listOf(
        "Painting", "Gardening", "Photography", "Reading", "Hiking", "Cooking",
        "Fishing", "Dancing", "Gaming", "Traveling", "Yoga", "Writing",
        "Playing Instruments", "Cycling", "Drawing", "Sculpting", "Bird Watching",
        "Pottery", "Collecting", "Astronomy"
    )

    val professionsList: List<String> = listOf(
        "Doctor", "Teacher", "Firefighter", "Chef", "Pilot", "Artist", "Engineer",
        "Police Officer", "Scientist", "Athlete", "Musician", "Actor", "Nurse",
        "Farmer", "Astronaut", "Lawyer", "Journalist", "Carpenter", "Veterinarian",
        "Electrician"
    )

    val animalsList: List<String> = listOf(
        "Elephant", "Tiger", "Penguin", "Giraffe", "Dolphin", "Koala", "Cheetah",
        "Octopus", "Kangaroo", "Polar Bear", "Peacock", "Chimpanzee", "Panda",
        "Alligator", "Gorilla", "Ostrich", "Flamingo", "Hedgehog", "Raccoon",
        "Armadillo"
    )

    fun getAllCategories(): List<String> {
        return categoriesList
    }

    fun getWord(category: String): String {
        return when (category) {
            "City Locations" -> cityLocationsList.random()
            "Household Items" -> householdItemsList.random()
            "Hobbies" -> hobbiesList.random()
            "Professions" -> professionsList.random()
            "Animals" -> animalsList.random()
            else -> ""
        }
    }
}



