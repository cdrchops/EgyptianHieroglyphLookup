package EgyptianDictionary

//println "\uD80C\uDC00\uD80D\uDC30\uD80C\uDE50"

//import EgyptianDictionary.Dictionary
class Dictionary2 {
//    def alphabet
    def hieroglyph
    def gardiner
    def transliteration
    def translation
    def other//knownAs
}

def lst = new File("./processed.txt").readLines()

lst.eachWithIndex {it, idx ->
    try {
        if (it.contains("[")) {
            def dictionary = new Dictionary2()
            dictionary.translation = it.substring(it.indexOf("]") + 1, it.indexOf("{"))
            dictionary.transliteration = it.substring(it.indexOf("[") + 1, it.indexOf("]"))
            dictionary.hieroglyph = it.substring(it.indexOf("{") + 1, it.indexOf("}"))

//            println "dmap[\"${dictionary.hieroglyph}\"] = new Dictionary(\"${dictionary.hieroglyph}\", \"${dictionary.transliteration}\", \"${dictionary.translation}\", \"${dictionary.gardiner}\")"
        }
    } catch (Exception e) {
        println idx
        println it
        e.printStackTrace()
    }
}