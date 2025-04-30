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



