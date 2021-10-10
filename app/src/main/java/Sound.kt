import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Sound(@PrimaryKey val id: UUID = UUID.randomUUID(),
                 var name: String = "",
                 var colorval : Int = 0,
                 var filename: String = "",
                 var listOrder: Int = 0)

//Filename, UUID. etc