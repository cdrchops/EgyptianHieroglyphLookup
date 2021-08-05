<!doctype html>
<%@ page import="javax.xml.transform.Source; net.kemetdict.data.Definition; net.kemetdict.data.DefinitionLink; net.kemetdict.data.Word; net.kemetdict.data.SourceManagement; net.kemetdict.data.DefinitionSourceLink; net.kemetdict.data.WordSourceLink"  %>
<html>
<head>
%{--    <meta name="layout" content="main"/>--}%
    <title>Welcome to the Egyptian Dictionary Online Project</title>
    <style>
    .single {
        float:left;text-align:center;vertical-align: middle;position:relative
    }

    .dualSideOuter {
        float:left;
        text-align:center;
        position:relative;
        margin-top:-15px
    }

    .dualSideTop {
        /*display:block;position:relative*/
    }

    .dualSideBot {
        /*display:block;position:relative;margin-top:-50px*/
    }

    .dualOuter {
        float:left;
        text-align:center;
        position:relative;
        margin-top:-15px
    }

    .dualInnerTop {
        display:block;position:relative
    }

    .dualInnerBot {
        display:block;position:relative;margin-top:-50px
    }

    .tripleOuter {
        float:left;
        text-align:center;
        position:relative;
        margin-top:-15px
    }
    .tripleInnerTop {
        display:block;position:relative;margin-top:-15px
    }
    .tripleInnerMid {
        display:block;position:relative;margin-top:-45px
    }
    .tripleInnerBot {
        display:block;position:relative;margin-top:-50px
    }
    </style>
</head>
<body>
<%
//    def wsl = new WordSourceLink()
//    def dsl = new DefinitionSourceLink()

//    def sourceManagement = SourceManagement.findById(1)//new SourceManagement(code: 'unk', color:'blue', description:'unknown', textColor: 'white', fullName:'unknown', enabled: true, bibliographyFullAPA: 'unknown', searchParameter: 'includeUnk', url: "http://egyptiandictionary.net")
    //sourceManagement.save()
%>
<%
    def parseDefinitionDisplay = {defLinkList ->
        def displayMap = [:]

        defLinkList.each {linklst ->
            if (displayMap.containsKey(linklst.word.pronunciation)) {
                def value = displayMap.get(linklst.word.pronunciation)
                value << linklst.definition.definition
            } else {
                def value = []
                value << linklst.definition.definition
                displayMap.put(linklst.word.pronunciation, value)
            }
        }

        return displayMap
    }

    def parseGardinerDisplay = {wordList ->
        def displayMap = [:]

        wordList.each {linklst ->
            if (displayMap.containsKey(linklst.gardiner)) {
                def value = displayMap.get(linklst.gardiner)
                value << DefinitionLink.findAllByWord(linklst)
            } else {
                def value = []
                value << DefinitionLink.findAllByWord(linklst)
                displayMap.put(linklst.gardiner, value)
            }
        }

        return displayMap
    }

    def definitionSearch = {definition ->
        Definition.findAll("from Definition where definition=?0", [definition])
    }

    def gardinerSearch = {gardiner ->
        parseGardinerDisplay(Word.findAll("from Word where gardiner=?0", [gardiner]))
    }

    def phoneticSearch = {phonetic ->
        Word.findAll("from Word where pronunciation=?0", [phonetic])
    }

    def search = {type, searchElement ->
        switch (type) {
            case 'definition':
                definitionSearch(searchElement)
                break
            case 'gardiner':
                gardinerSearch(searchElement)
                break
            case 'phonetic':
                phoneticSearch(searchElement)
                break
        }
    }

    def definition = search("definition", "tread")
    def phonetic = search("phonetic", "a")
    def gardiner = search("gardiner", "g1")
%>
    <g:if test="${definition}">
        <g:each in="${definition}" var="entry">
            ${entry.definition}<sup><%= DefinitionSourceLink.findByDefinition(entry).sourceManagement.code %></sup>
            <% def defLinkList = DefinitionLink.findAll("from DefinitionLink where definition=?0", [entry]) %>
            <g:each in="${defLinkList}" var="defLink">
                ${defLink.word.gardiner}<sup><%= DefinitionSourceLink.findByDefinition(entry).sourceManagement.code %></sup> ${defLink.word.pronunciation}<sup><%= DefinitionSourceLink.findByDefinition(entry).sourceManagement.code %></sup><br/>
            </g:each>
        </g:each>
    </g:if>

    <g:if test="${phonetic}">
        <g:each in="${phonetic}" var="entry">
            ${entry.gardiner}<sup><%= WordSourceLink.findByWord(entry).sourceManagement.code %></sup>
            <%
               def defLinkList = DefinitionLink.findAll("from DefinitionLink where word=?0", [entry])
               def display = parseDefinitionDisplay(defLinkList)
            %>
            <g:each in="${display}" var="disp">
                ${disp.key}<sup><%= WordSourceLink.findByWord(entry).sourceManagement.code %></sup> --
                <g:each in="${disp.value}" var="val">
                    ${val} <sup><%= DefinitionSourceLink.findByDefinition(Definition.find("from Definition where definition=?0", [val])).sourceManagement.code %></sup>
                </g:each>
            </g:each>
        </g:each>
    </g:if>

    <g:if test="${gardiner}">
        <br/>
        <g:each in="${gardiner}" var="entry">
            ${entry.key} -
%{--            This needs to make sure each entry has a source code attached so it can be identified where it came from --}%
            <g:each in="${entry.value}" var="val">
                ${val.definition.definition}<sup>${DefinitionSourceLink.findAllByDefinition(val.definition).sourceManagement.code}</sup>
            </g:each>
        </g:each>
    </g:if>

    %{--def lst = Definition.list() --}%
    %{--<g:each in="${lst}" var="entry">
        ${entry.definition}<sup><%= DefinitionSourceLink.findByDefinition(entry).sourceManagement.description %></sup>
    --}%%{--    <% def dsl = new DefinitionSourceLink(definition: entry, sourceManagement: sourceManagement)--}%%{--
    --}%%{--        if (!dsl.save()) {--}%%{--
    --}%%{--            out << dsl.errors--}%%{--
    --}%%{--        }--}%%{--
    --}%%{--    %>--}%%{--
        <% def defLinkList = DefinitionLink.findAll("from DefinitionLink where definition=?0", [entry]) %>
        <g:each in="${defLinkList}" var="defLink">
            ${defLink.word.gardiner} -- ${defLink.word.pronunciation}<sup><%= DefinitionSourceLink.findByDefinition(entry).sourceManagement.description %></sup><br/>

    --}%%{--        <% def wsl = new WordSourceLink(word: defLink.word, sourceManagement: sourceManagement)--}%%{--
    --}%%{--            if (!wsl.save(flush:true)) {--}%%{--
    --}%%{--                out << wsl.errors--}%%{--
    --}%%{--            }--}%%{--
    --}%%{--        %>--}%%{--
        </g:each>
    </g:each>--}%
    <div style="display:table-row">
        <div style="display:table-cell">Hieroglyphics</div>
        <div style="display:table-cell">Spelling</div>
        <div style="display:table-cell">Definition</div>
        <div style="display:table-cell">Gardiner Code</div>
        <div style="display:table-cell">Layout Code</div>
        <div style="display:table-cell">Display With Code</div>
    </div>
    <div style="display:table-row">
        <div style="display:table-cell; padding-right:20px"><div id="middleaegyptische" style="font-size:36pt">𓃀𓇋𓈖𓏏𓅪𓁷𓏤𓄣𓏤</div></div>
        <div style="display:table-cell; padding-right:20px">bint Hr ib</div>
        <div style="display:table-cell; padding-right:20px">what is displeasing (D)<br/>unpleasant (G)</div>
        <div style="display:table-cell; padding-right:20px">D58-M17-N35-X1-G37-D2-Z1-F34-Z1</div>
        <div style="display:table-cell; padding-right:20px">D58*M17*N35:X1*G37*D2:Z1*F34:Z1</div>
        <div style="display:table-cell"><div style="font-size:36pt;position:absolute">
            <div class="single">𓃀</div>
            <div class="single">𓇋</div>
            <div class="dualOuter"><span class="dualInnerTop">𓈖</span><span class="dualInnerBot">𓏏</span></div>
            <div class="single">𓅪</div>
            <div class="dualOuter"><span class="dualInnerTop">𓁷</span><span class="dualInnerBot">𓏤</span></div>
            <div class="dualOuter"><span class="dualInnerTop">𓄣</span><span class="dualInnerBot">𓏤</span></div>
        </div></div>
    </div>
</body>
</html>
