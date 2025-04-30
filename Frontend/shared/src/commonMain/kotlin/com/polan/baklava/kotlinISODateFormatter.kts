import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun isoStringToKotlinDateTime(isoString: String): LocalDateTime {
    return LocalDateTime.parse(isoString, DateTimeFormatter.ISO_DATE_TIME)
}

fun isoStringToSwiftDate(_ isoString: String) -> Date? {
    let formatter = ISO8601DateFormatter()
    formatter.formatOptions = [.withInternetDateTime, .withFractionalSeconds]
    return formatter.date(from: isoString)
}

