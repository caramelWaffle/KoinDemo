import android.content.Context
import com.example.koindemo.SingletonHolder

class Contextor private constructor(private var context: Context){

    companion object : SingletonHolder<Contextor, Context>(::Contextor)

}