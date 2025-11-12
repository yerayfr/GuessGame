import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    // Seleccionamos una palabra aleatoria de esta lista de palabras
    val words = listOf("Android", "Fragment", "Kotlin", "Model","Mapache")
    var secretWord  = words.random().uppercase()
    // String que se mostrará en la pantalla (guiones y letras a medida que las vamos descubriendo)
    var secretWordDisplay = ""
    // Intentos del usuario. Caracteres que vaya probando el usuario.
    var guesses = mutableListOf<Char>()
    // Vidas
    var lives = 3

    init {
        // Inicializamos la palabra con _
        secretWordDisplay = generateSecretWordDisplay()
    }
    fun restart() {
        guesses.clear()
        lives = 8
        secretWord = words.random().uppercase()
        secretWordDisplay = generateSecretWordDisplay()
    }

    fun resultMessage() =
        if (win()) "Ganaste!\n  La palabra secreta era $secretWord"
        else  "Oops, perdiste!\n  La palabra secreta era $secretWord"


    // Genera la representación visual de la palabra oculta
    fun generateSecretWordDisplay() =
        // Recorremos cada uno de los caracteres de la palabra
        secretWord.map {
            // Si el caracter está en la lista, lo añadimos; sino, continuamos con _
            if (it in guesses) it
            else '_'
        }.joinToString("")

    // Realiza un intento de adivinanza por parte del usuario
    fun makeGuess(guess: String){
        if(guess.length > 0) {
            // Extraemos la letra inicial (aunque solo nos pueden introducir un caracter)
            val letter = guess.uppercase()[0]
            // La añadimos a la lista de intentos
            guesses.add(letter)

            secretWordDisplay = generateSecretWordDisplay()
            if(!secretWord.contains(letter)) lives -= 1
        }
    }

    // Función para verificar si ganamos
    fun win() = secretWord == secretWordDisplay
    // Función para comprobar si nos quedan vidas
    fun lost() = lives <= 0
}