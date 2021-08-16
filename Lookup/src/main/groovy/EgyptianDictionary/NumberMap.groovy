package groovy.EgyptianDictionary

def dict = new File("./dict.js").readLines()

dict.eachWithIndex { String entry, int i ->
    def tmp = entry.substring(0, entry.size() - 2).replace("Dictionary", "DictionaryWithIndex")
    tmp += ", \"${i}\");"
    println tmp
}