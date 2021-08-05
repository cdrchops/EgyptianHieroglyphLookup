package EgyptianDictionary

import java.util.regex.Matcher
import java.util.regex.Pattern

//final Pattern sTag = Pattern.compile("<s\\b[^>]*>(.*?)</s>")
final Pattern sTag = Pattern.compile("(\\})([\\n]{1,100})(\\s\\[)")
//final Matcher m = sTag.matcher(it)
//
//final StringBuffer sb = new StringBuffer(it.length());
//while (m.find()) {
//    final String text1 = m.group(1);
//    final String text2 = m.group(3);
//    m.appendReplacement(sb, text1 + "" + text2);
//}
//
//m.appendTail(sb);
//println sb.toString()

def test = new File("test.txt").readLines()

def sb = new StringBuilder()

test.each {
    sb << it
}

def text = sb.toString()

text = text.replaceAll("\n", " ")
text = text.replaceAll("\\[", "\n\\[")
text = text.replaceAll(/(\})([A-Z])/, '$1\n$2')
text = text.replaceAll(/(\])([a-zA-Z])/, '$1 $2')
text = text.replaceAll(/([0-9])([A-Za-z])/, '$1 $2')
println text