
import java.util.regex.Pattern
import java.util.regex.Matcher

class Hieroglyph {
    def unicodeCode
    def gardinerCode
    def gardinerCategory
    def adjustedGardinerCode
    def additionalDetails
//    return {'unicodeCode': unicodeCode, 'gardinerCode': gardinerCode, 'gardinerCategory': gardinerCategory, 'adjustedGardinerCode': adjustedGardinerCode, 'additionalDetails': additionalDetails}
}

//String s = "hey where.is.the book on.brown.table\nhey there.is.a.boy.in.the city Delhi\nhey here.is.the.boy living next door"
//println s.replaceAll(/(\G(?!\A)\w+|\b(?:where|there)\b)\./, '$1_')

//def star = "hmap[\"ESG\"] = new Hieroglyph(unicodeCode: \"13438\", \"ESG\", \"end segment classifier\", \"ESG\", additionalDetails:[])"

//println star.replaceAll(/,\s\"([A-Za0-9]{4})\",\s\"([A-Z]{1,2})\./, ', gardinerCode:\"$1\", gardinerCategory: \"$2", adjustedGardinerCode:')

def file = new File("hmap.txt").readLines()


file.each {str ->
    def unicodeCode
    def gardinerCode
    def gardinerCategory
    def adjustedGardinerCode
    def additionalDetails

    str.split(", ").eachWithIndex {it, idx ->
        it = it.trim().replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "")
        switch(idx) {
            case 0:
                unicodeCode = it
                break
            case 1:
                gardinerCode = it
                break
            case 2:
                gardinerCategory = it
                break
            case 3:
                adjustedGardinerCode = it
                break
            case 4:
                additionalDetails = it
        }

    }

    println "INSERT INTO `egyptiandictionary`.`hieroglyphics`(`unicodeCode`,`gardinerCode`,`gardinerCategory`,`adjustedGardinerCode`,`additionalDetails`)VALUES(\'$unicodeCode\',\'$gardinerCode\',\'$gardinerCategory\',\'$adjustedGardinerCode\',\'$additionalDetails\');"
}
