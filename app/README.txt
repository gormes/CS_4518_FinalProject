---- DATABASE ----
This application uses a local database(sound-database ...) and a remote database consisting of
Sound items.

Sound: List the properties

SoundDao: Interacts directly with the database

SoundRepository: Facilitates interactions between fragments and the Dao

---- FRAGMENTS ----
This application is built from fragments which are managed from the MainActivity.

MainActivity: Switches between fragments using Callbacks in addition. Asks user for permission to
use the phone's microphone and speaker. Starts a SoundboardListFragment when the app is first opened

SoundboardListFragment: This fragment is the app's homescreen. sound_items, populated by the
local database are displayed in a scrollable RecyclerView and there is a toolbar of buttons at the
bottom. When a sound_item is pressed, it's respective sound file is played through the phones
speakers. When the add button is pressed, the AddSoundFragment is opened and when the edit button
is pressed the EditSoundboardList fragment is opened.

EditSoundboardList: This fragment displays the current list of sounds in the local database as
edit_soundboard_items and a done button. The up and down buttons on each fragment allow you
to change the order of the items in the list. When the sound item is pressed, it passes the id of
that sound to a new instance of an EditSoundFragment. The done button closes the fragment and
reopens the SoundboardListFragment.

EditSoundFragment: This fragment loads the sound that was passed to it by the EditSoundboardList..


