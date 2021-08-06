package egyptiandictionary
import javax.xml.transform.Source
import net.kemetdict.data.Definition
import net.kemetdict.data.DefinitionLink
import net.kemetdict.data.Word
import net.kemetdict.data.SourceManagement
import net.kemetdict.data.DefinitionSourceLink
import net.kemetdict.data.WordSourceLink

class DisplTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def phonetic = {params ->
        def phon = params.phonetic
        def sb = new StringBuilder()
        if (phon) {
            phon.each {entry ->
                sb << "${entry.gardiner}<sup class=\"source\">${WordSourceLink.findByWord(entry).sourceManagement.code}</sup>"
                def defLinkList = DefinitionLink.findAll("from DefinitionLink where word=?0", [entry])
                def display = parseDefinitionDisplay(defLinkList)
                display.each {disp ->
                    sb << "${disp.key}<sup class=\"source\">${WordSourceLink.findByWord(entry).sourceManagement.code}</sup> --"
                    disp.value.each {val ->
                        sb << "${val}<sup class=\"source\">${DefinitionSourceLink.findByDefinition(Definition.find("from Definition where definition=?0", [val])).sourceManagement.code}</sup>,"
                    }
                }
            }

/*            <g:each in="${phonetic}" var="entry">
                    ${entry.gardiner}<sup class="source"><%= WordSourceLink.findByWord(entry).sourceManagement.code %></sup>
            <%
               def defLinkList = DefinitionLink.findAll("from DefinitionLink where word=?0", [entry])
               def display = parseDefinitionDisplay(defLinkList)
            %>
            <g:each in="${display}" var="disp">
                ${disp.key}<sup class="source"><%= WordSourceLink.findByWord(entry).sourceManagement.code %></sup> --
            <g:each in="${disp.value}" var="val">

                </g:each>
            </g:each>
        </g:each>*/
        }

        out << raw(sb.toString())
    }

    def gardiner = {params ->

    }

    def definition = {params ->

    }
}
