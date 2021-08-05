package EgyptianDictionary.processing

import Dictionary

def dict = new File("edict.txt").readLines()

def dictList = []

dict.eachWithIndex {it, idx ->
    if (it.trim() != "" && it.trim() != "\t\t\t") {
        def splarr = it.trim().split("\t")
        def dictionary = new Dictionary()
//        if (splarr.size() < 2) {
//            println idx
//            println it
//        }
//        println splarr.size()
    //    dictionary.hieroglyph = splarr[0]

        if (splarr.size() > 3) {
            dictionary.other = splarr[3]
        }

        if (splarr.size() > 0) {
            dictionary.gardiner = splarr[0]
            dictionary.transliteration = splarr[1]
            dictionary.translation = splarr[2]
            dictList << dictionary
        }
    }
}

//def f = new File("dictionary.js")
//f.write("var dmap = new Map();\n")
//
//dictList.each {
//    f.append("dmap[\"${it.gardiner}\"] = new Dictionary(\"${it.gardiner}\", \"${it.transliteration}\", \"${it.translation.replaceAll("\"", "")}\", \"${it.other.replaceAll("\"", "")}\");\n")
//}

def definitionEntries = new HashMap<String, List>()

dictList.each {
    String translation = it.translation.replaceAll("\"", "")
    if (definitionEntries.containsKey("${translation}")) {
        def lst = definitionEntries.get("${translation}")
        lst << it.gardiner

        definitionEntries.put("${translation}", lst)
    } else {
        def tmpList = []
        tmpList << it.gardiner
        definitionEntries.put("${translation}", tmpList)
    }
}

def f2 = new File("entries.js")
f2.write("var entryMap = new Map();\n")

definitionEntries.each {
    f2.append("entryMap[\"${it.key}\"] = \"${it.value.join(",")}\";\n");
}