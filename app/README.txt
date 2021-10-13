


double check assignment

---- DATABASE ----
This application uses a local database(sound-database ...) and a remote database consisting of
Sound items.

Sound: List the properties

SoundDao: Interacts directly with the database

SoundRepository: Facilitates interactions between fragments and the Dao

SoundDatabase: Hold the RoomDatabase

SoundboardIntentApplication: Initializes the Sound Database from the MainActivity
____________________


---- VIEW MODEL ----
SoundDetailViewModel: View model for the sound detail screens(AddSoundFragment and EditSoundFragments).
It connects the Fragments to the SoundRepository.

SoundboardListViewModel: View model for the local soundboard list screens
(SoundboardListFragment and EditSoundboardListFragment). It connects the Fragments to the SoundRepository.

SoundboardListRemoteViewModel: It connects the Fragments to the SoundRepository. View model for the
remote soundboard list screen. (SoundboardRemoteListFragment)
--------------------


---- FRAGMENTS ----
This application is built from fragments which are managed from the MainActivity.

MainActivity: Switches between fragments using Callbacks in addition. Asks user for permission to
use the phone's microphone and speaker. Starts a SoundboardListFragment when the app is first opened

SoundboardListFragment: This fragment is the app's homescreen. sound_items, populated by the
local database are displayed in a scrollable RecyclerView and there is a toolbar of buttons at the
bottom. When a sound_item is pressed, it's respective sound file is played through the phones
speakers. When the add button is pressed, the AddSoundFragment is opened and when the edit button
is pressed the EditSoundboardList fragment is opened.

AddSoundFragment: This fragment creates a new instance of sound and allows you to change information
about the sound then either save it to the database or return without saving. The EditText field
lets you choose the a name for the sound which will be displayed in the main soundboard list. T
he 'Record a Clip' button activates the microphone
, if permissions are enabled, ... If permissions aren't enabled......The'Remote' If the cancel button
is pressed, it doesn't add the item to the database and returns you to the SoundboardListFragment.
If the add button is pressed, it adds the sound to the database and returns you to the SoundboardListFragment.

EditSoundboardListFragment: This fragment displays the current list of sounds in the local database as
edit_soundboard_items and a done button. The up and down buttons on each fragment allow you
to change the order of the items in the list. When the sound item is pressed, it passes the id of
that sound to a new instance of an EditSoundFragment. The done button closes the fragment and
reopens the SoundboardListFragment.

EditSoundFragment: This fragment loads the sound that was passed to it by the EditSoundboardList. It
allows users to change the sound name or the sound file associated with it, either a microphone
recording, local sound file, or remote sound file. The 'Record a Clip' button activates the microphone
, if permissions are enabled, ... If permissions aren't enabled......The'Remote' If the cancel button is pressed,
it doesn't load any of the changes made on this screen to the item in the database and returns you
to the EditSoundboardListFragment. If the save button is pressed, it saves any of the changes made to
this sound, a new sound file and/or new sound name,  from this screen to the database and returns you
to the EditSoundboardListFragment. If the delete button is pressed, the sound is deleted from the
database and it returns you to the EditSoundboardListFragment.


SoundboardRemoteListFragment:

---- XML FILE ----
activity_main: An empty frame layout to load each fragment into

edit_sound: Edit sound form displayed by the EditSoundFragment

edit_sound_item: The item displayed per sound in the EditSoundboardListFragment

edit_soundboard: A RecyclerView to hold edit_sound_items and the button toolbar

main_soundboard: A RecyclerView to hold sound_items and the button toolbar

new_sound: New sound form displayed by the AddSoundFragment

remote_sound_item:

remote_soundboard:

sound_item: The item displayed per sound in the SoundboardListFragment

