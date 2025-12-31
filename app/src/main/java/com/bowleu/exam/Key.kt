package com.bowleu.exam

data class Key(
    val field1: Int,
    var field2: String
) {
    var field3: String? = null
}

// field3 не в конструкторе -> в hashCode не будет учитываться
// field2 - var -> при изменении field2 меняется и hashCode -> вероятно при изменении hash
// будет доступ к другой ячейке HashMap. То есть value остался доступным по старому hashCode,
// а читаем мы по новому.
