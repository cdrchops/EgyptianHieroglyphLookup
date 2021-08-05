package EgyptianDictionary

class Hieroglyph {
    String unicodeCode
    String adjustedGardinerCode
    String gardinerCode
    String gardinerCategory
    def additionalDetails = []

    void addDetail(str) {
        additionalDetails << "\"${str}\""
    }

    @Override
    String toString() {
        return "Hieroglyph{" +
                "unicodeCode='" + unicodeCode + '\'' +
                ", gardinerCode='" + gardinerCode + '\'' +
                ", gardinerCategory='" + gardinerCategory + '\'' +
                ", additionalDetails=" + additionalDetails +
                '}';
    }
}
