import kotlinx.datetime.*

fun isoStringToKotlinDateTime(isoString: String): LocalDateTime {
    return LocalDateTime.parse(isoString)
}

fun kotlinDateTimeToIsoString(dateTime: LocalDateTime): String {
    return dateTime.toString() + ":00Z"
}