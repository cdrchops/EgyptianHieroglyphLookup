package net.kemetdict.data

def dictionary = new File("./database.txt").readLines()
def hieroglyphs = new File("./hieroglyphs.txt").readLines()
def phon = new File("./phonetic.txt").readLines()
def defin = new File("./definition.txt").readLines()

//group_concat(distinct gardiner separator ', ')	group_concat(distinct phonetic separator ', ')	group_concat(distinct definition separator ', ')	group_concat(distinct notes separator ', ')

def output = new File("./output.txt")
output.write("")
/*dictionary.each {
    def gardiner, phonetic, definition, notes
    def splarr = it.split("\t");
    splarr.eachWithIndex { String entry, int i ->
        switch(i) {
            case 0:
                gardiner = entry
                break
            case 1:
                phonetic = entry
                break
            case 2:
                definition = entry
                break
            case 3:
                notes = entry
                break
        }
    }

//    output.append("dmap[\"${gardiner}\"] = new Dictionary(\"${gardiner}\", \"${phonetic}\", \"${definition}\", \"${notes}\");\n")
}

hieroglyphs.each {
    def unicode, gardiner, category, gardinerModified, notes
    def splarr = it.split("\t");
    splarr.eachWithIndex { String entry, int i ->
        switch(i) {
            case 0:
                unicode = entry
                break
            case 1:
                gardiner = entry
                break
            case 2:
                category = entry
                break
            case 3:
                gardinerModified = entry
                break
            case 4:
                notes = entry
                break
        }
    }

//    output.append("hmap[\"${gardinerModified}\"] = Hieroglyph(\"${unicode}\", \"${gardiner}\", \"${category}\", \"${gardinerModified}\", \"${notes}\");\n")
}*/

phon.each {
    def gardiner, phonetic, definition, notes
    def splarr = it.split("\t");
    splarr.eachWithIndex { String entry, int i ->
        switch(i) {
            case 0:
                phonetic = entry
                break
            case 1:
                gardiner = entry
                break
            case 2:
                definition = entry
                break
            case 3:
                notes = entry
                break
        }
    }

    output.append("phonmap[\"${phonetic}\"] = new Dictionary(\"${gardiner}\", \"${phonetic}\", \"${definition}\", \"${notes}\");\n")
}

defin.each {
    def gardiner, phonetic, definition, notes
    def splarr = it.split("\t");
    splarr.eachWithIndex { String entry, int i ->
        switch(i) {
            case 0:
                definition = entry

                break
            case 1:
                gardiner = entry

                break
            case 2:
                phonetic = entry
                break
            case 3:
                notes = entry
                break
        }
    }

    output.append("defmap[\"${definition}\"] = new Dictionary(\"${gardiner}\", \"${phonetic}\", \"${definition}\", \"${notes}\");\n")
}

