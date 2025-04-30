import Foundation

func isoStringToSwiftDate(_ isoString: String) -> Date? {
    let formatter = ISO8601DateFormatter()
    formatter.formatOptions = [.withInternetDateTime]
    return formatter.date(from: isoString)
}

func swiftDateToIsoString(_ date: Date) -> String {
    let formatter = ISO8601DateFormatter()
    formatter.formatOptions = [.withInternetDateTime]
    var isoString = formatter.string(from: date)
    if isoString.hasSuffix(":00Z") {
        isoString.removeLast(4) // Remove ":00Z"
    }
    return isoString
}

func absoluteUserFriendlyDate(from date: Date) -> String {
    let formatter = DateFormatter()
    formatter.dateStyle = .medium
    formatter.timeStyle = .short
    formatter.locale = Locale.current
    let formattedDate = formatter.string(from: date)
    
    // Split the formatted date into date and time components
    let components = formattedDate.split(separator: ",")
    
    // Combine the date with 'at' before the time part
    return "\(components[0])\(components[1]), at\(components[2])"
}


