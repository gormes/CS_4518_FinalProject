---- DATABASE ----
This application uses a local database(sound-database ...) and a remote database consisting of
Sound items.

Sound: List the properties

---- FRAGMENTS ----
This application is built from fragments which are managed from the MainActivity.

MainActivity: Switches between fragments using Callbacks in addition. Asks user for permission to
use the phone's microphone and speaker. Starts a SoundboardListFragment when the app is first opened

SoundboardListFragment: This fragment is the app's homescreen. sound_items are displayed
in a scrollable RecyclerView and there is a toolbar of buttons at the bottom. When a sound_item is
pressed, it's respective sound file is played through the phones speakers. When the add button is
pressed, the AddSoundFragment is opened and when the edit button is pressed the EditSoundboard
fragment is opened.



