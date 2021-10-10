import java.util.*

data class Sound(val id: UUID = UUID.randomUUID(),
                 var name: String = "",
                 var colorval : Int = 0,
                 var filename: String = "")

//Filename, UUID. etc