package EgyptianDictionary.processing

def dicksonDictionary = new File("DicksonDictionary.txt").readLines()
def dicksonDictionarya = new File("DicksonDictionaryb.txt").readLines()
def dicksonDictionaryb = new File("DicksonDictionarya.txt")
dicksonDictionaryb.write("")

def lst = []

def entry = new StringBuilder()
dicksonDictionarya.each {
    if (it != "") {
        if (!it.startsWith("[")) {
            entry << " "
            entry << it
            lst << entry.toString()
            entry = new StringBuilder()
        } else {
            entry << it
        }
    }
}

lst.each {
    dicksonDictionaryb.append("${it.trim()}\n")
}

