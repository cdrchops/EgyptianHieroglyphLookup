package EgyptianDictionary.processing

import EgyptianDictionary.Hieroglyph

//parse unicode hieroglyphic text to assign a gardiner index to each one
def unicodeFile = new File("unicode to gardiner.txt").readLines()

String category
def hList = []
def hglyph = new Hieroglyph()
unicodeFile.each {
    if (Character.isLetter(it.charAt(0))) {
        category = it
    } else if (Character.isDigit(it.charAt(0))) {
        hList << hglyph
        hglyph = new Hieroglyph()
        hglyph.gardinerCategory = category
        def splarr = it.split(" ")
        if (splarr.size() < 4) {
            println it
        } else {
            hglyph.unicodeCode = splarr[0]
            try {
                hglyph.gardinerCode = splarr[4]
                def sb = new StringBuilder()
                def continueStrippingZeroes = true
                hglyph.gardinerCode.each {
                    def chr = it as char

                    if (Character.isLetter(chr)) {
                        sb << chr
                    } else if (Character.isDigit(chr)) {
                        def tmpint = Integer.parseInt("${chr}")
                        if (tmpint == 0 && continueStrippingZeroes) {

                        } else {
                            if (tmpint > 0) {
                                sb << chr
                                continueStrippingZeroes = false
                            }
                            if (tmpint == 0 && !continueStrippingZeroes) {
                                sb << chr
                            }
                        }
                    }
                }
                hglyph.adjustedGardinerCode = sb.toString()
            } catch (Exception e) {
                println it
            }
        }
    } else {
        hglyph.addDetail(it)
    }
}

hList.each {Hieroglyph it ->
    println "hmap.\"${it.adjustedGardinerCode}\" = new Hieroglyph(unicodeCode: \"${it.unicodeCode}\", gardinerCode: \"${it.gardinerCode}\", gardinerCategory: \"${it.gardinerCategory}\", adjustedGardinerCode: \"${it.adjustedGardinerCode}\", additionalDetails: [${it.additionalDetails.join(",")}])"


//    println it.getAdjustedGardinerCode()
//    println "Hieroglyph{" +
//            "unicodeCode='" + it.unicodeCode + '\'' +
//            ", gardinerCode='" + it.gardinerCode + '\'' +
//            ", gardinerCategory='" + it.gardinerCategory + '\'' +
//            ", additionalDetails=" + it.additionalDetails +
//            '}'
}

