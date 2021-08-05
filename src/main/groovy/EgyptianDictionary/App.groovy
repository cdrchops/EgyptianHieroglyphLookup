package EgyptianDictionary

println "\uD80C\uDC00\uD80D\uDC30\uD80C\uDE50"

import EgyptianDictionary.Dictionary

def lst = new File("./test.txt").readLines()

lst.each {
    if (it.startsWith("\\[")) {
        def dictionary = new Dictionary()
        dictionary.translation = it.substring(it.indexOf("\\]"), it.indexOf("\\{"))
        dictionary.transliteration = it.substring(it.indexOf("\\["), it.indexOf("\\]"))
        dictionary.hieroglyph = it.substring(it.indexOf("\\{"), it.indexOf("\\}"))

        println "dmap[\"${dictionary.hieroglyph}\"] = new Dictionary(\"${dictionary.hieroglyph}\", \"${dictionary.transliteration}\", \"${dictionary.translation}\", \"${dictionary.gardiner}\")"
    }
}