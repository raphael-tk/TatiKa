import android.content.Context
import com.tralkamy.tatika.data.db.TatiKaSQLiteHelper

object DatabaseBuilder {
    @Volatile
    private var INSTANCE: TatiKaSQLiteHelper? = null

    fun getInstance(context: Context): TatiKaSQLiteHelper {
        return INSTANCE ?: synchronized(this) {
            val instance = TatiKaSQLiteHelper(context.applicationContext)
            INSTANCE = instance
            instance
        }
    }
}
