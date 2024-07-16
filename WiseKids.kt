fun main() {
    val app = LearningApp()
    app.start()
}

class LearningApp {
    fun start() {
        println("====================================")
        println("Selamat datang di Aplikasi Belajar Anak WiseKids!")
        println("====================================")
        var pilihan: Int
        do {
            println("\nPilih menu:")
            println("1. Membaca")
            println("2. Menulis")
            println("3. Menghitung")
            println("4. Keluar")
            print("Masukkan pilihan Anda: ")
            pilihan = readLine()?.toIntOrNull() ?: 0

            when (pilihan) {
                1 -> Membaca().start()
                2 -> Menulis().start()
                3 -> Menghitung().start()
                4 -> println("Terima kasih telah menggunakan aplikasi ini!")
                else -> println("Pilihan tidak valid, silakan coba lagi.")
            }
        } while (pilihan != 4)
    }
}

abstract class Activity(val name: String) {
    abstract fun start()
}

class Membaca : Activity("Membaca"), Playable {
    override fun start() {
        println("====================================")
        println("Mulai aktivitas Membaca")
        println("====================================")
        println("Aturan Permainan Membaca:")
        println("1. Anda akan diberikan sebuah kata dengan satu huruf yang hilang.")
        println("2. Pilih huruf yang benar untuk melengkapi kata tersebut.")
        println("3. Ada 5 soal yang harus dijawab.")
        println("4. Skor akan diberikan berdasarkan jawaban yang benar.")
        println("====================================")
        play()
    }

    override fun play() {
        println("Mulai bermain Membaca...")
        permainanMembaca()
    }

    private fun permainanMembaca() {
        val kataKata = listOf("Kucing", "Anjing", "Burung", "Ikan", "Gajah")
        var skor = 0

        repeat(5) {
            val kataUntukDilengkapi = kataKata.random()
            val indexHurufHilang = (0 until kataUntukDilengkapi.length).random()
            val hurufHilang = kataUntukDilengkapi[indexHurufHilang]

            val kataDenganHurufHilang = kataUntukDilengkapi.substring(0, indexHurufHilang) + "_" + kataUntukDilengkapi.substring(indexHurufHilang + 1)
            val pilihanHuruf = listOf(hurufHilang, ('A'..'Z').random(), ('A'..'Z').random()).shuffled()

            println("\nLengkapi kata berikut: $kataDenganHurufHilang")
            pilihanHuruf.forEachIndexed { index, huruf -> println("${index + 1}. $huruf") }

            print("Jawab: ")
            val jawaban = readLine()?.toIntOrNull() ?: 0
            if (jawaban in 1..pilihanHuruf.size && pilihanHuruf[jawaban - 1] == hurufHilang) {
                println("Benar!")
                skor++
            } else {
                println("Salah, jawaban yang benar adalah $hurufHilang")
            }
        }

        println("====================================")
        println("Permainan selesai! Skor Anda: $skor dari 5")
        println("====================================")
    }
}

class Menulis : Activity("Menulis"), Playable {
    override fun start() {
        println("====================================")
        println("Mulai aktivitas Menulis")
        println("====================================")
        println("Aturan Permainan Menulis:")
        println("1. Anda akan diberikan sebuah kata untuk dieja.")
        println("2. Eja kata tersebut dengan benar.")
        println("3. Ada 5 soal yang harus dijawab.")
        println("4. Skor akan diberikan berdasarkan jawaban yang benar.")
        println("====================================")
        play()
    }

    override fun play() {
        println("Mulai bermain Menulis...")
        permainanMenulis()
    }

    private fun permainanMenulis() {
        val kataKata = listOf("Kucing", "Anjing", "Burung", "Ikan", "Gajah")
        var skor = 0

        repeat(5) {
            val kataUntukMenulis = kataKata.random()

            println("\nSilakan eja kata berikut: $kataUntukMenulis")
            var jawaban: String?
            var benar = false

            while (!benar) {
                print("Jawab: ")
                jawaban = readLine()

                if (jawaban.equals(kataUntukMenulis, ignoreCase = true)) {
                    println("Benar!")
                    skor++
                    benar = true
                } else {
                    println("Salah, coba lagi!")
                    println("Ejaan yang benar adalah $kataUntukMenulis")
                    break
                }
            }
        }

        println("====================================")
        println("Permainan selesai! Skor Anda: $skor dari 5")
        println("====================================")
    }
}

class Menghitung : Activity("Menghitung"), Playable {
    override fun start() {
        println("====================================")
        println("Mulai aktivitas Menghitung")
        println("====================================")
        println("Aturan Permainan Menghitung:")
        println("1. Anda akan diberikan beberapa soal penjumlahan.")
        println("2. Hitung hasil penjumlahan tersebut.")
        println("3. Ada 5 soal yang harus dijawab.")
        println("4. Skor akan diberikan berdasarkan jawaban yang benar.")
        println("====================================")
        play()
    }

    override fun play() {
        println("Mulai bermain Menghitung...")
        permainanMenghitung()
    }

    private fun permainanMenghitung() {
        val soal = mutableListOf<Pair<Int, Int>>()
        for (i in 1..5) {
            val a = (1..10).random()
            val b = (1..10).random()
            soal.add(Pair(a, b))
        }

        var skor = 0
        for ((a, b) in soal) {
            print("\nBerapakah $a + $b? Jawab: ")
            val jawaban = readLine()?.toIntOrNull()
            if (jawaban == a + b) {
                println("Benar!")
                skor++
            } else {
                println("Salah, jawaban yang benar adalah ${a + b}")
            }
        }

        println("====================================")
        println("Permainan selesai! Skor Anda: $skor dari ${soal.size}")
        println("====================================")
    }
}

interface Playable {
    fun play()
}
