package net.kemetdict.data

class SourceManagement {

    static constraints = {
    }

    String code
    String color
    String description
    String textColor
    String fullName
    boolean enabled
    String bibliographyFullAPA
    String searchParameter
    String url

    public String toString() {
        return code
    }
}