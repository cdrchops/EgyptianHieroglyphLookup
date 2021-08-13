<!doctype html>
<%@ page import="net.kemetdict.data.Gardiner; javax.xml.transform.Source; net.kemetdict.data.Hiero; net.kemetdict.data.Definition; net.kemetdict.data.DefinitionLink; net.kemetdict.data.Word; net.kemetdict.data.SourceManagement; net.kemetdict.data.DefinitionSourceLink; net.kemetdict.data.WordSourceLink"  %>
<html>
<head>
    <title>Welcome to the Egyptian Dictionary Online Project</title>
    <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
    <script>
        var insertHTML = "";
        function Hieroglyph(unicodeCode, gardinerCode, gardinerCategory, adjustedGardinerCode, additionalDetails) {
            return {'unicodeCode': unicodeCode, 'gardinerCode': gardinerCode, 'gardinerCategory': gardinerCategory, 'adjustedGardinerCode': adjustedGardinerCode, 'additionalDetails': additionalDetails}
        }

        function Dictionary(gardiner, transliteration, translation, other) {
            var tmpother = other === 'null' ? '' : other;
            var translationCombined = translation + " " + tmpother;
            translationCombined = translationCombined.replace(/'/g, "\\'");

            insertHTML += "INSERT INTO `dictionary` (`gardiner`, `transliteration`, `translation`) VALUES ('" + gardiner + "', '" + transliteration + "', '" + translationCombined + "');<br/>";
            return {'gardiner': gardiner, 'transliteration': transliteration, 'translation': translation, 'other': other}
        }

    </script>
    <asset:javascript src="dictionary.js"/>
    <asset:javascript src="hieroglyphs.js"/>
    <asset:javascript src="entries.js"/>
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

%{--    <meta name="layout" content="main"/>--}%


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

    .source {
        /*display:block;*//* visibility: visible;*/
    }
    </style>
</head>
<body>
<pre>
    convert gardiner to unicode via the hieroglyphs table
    display values from database
    put hieroglyph into database - so it can be looked up
</pre>
%{--
<div id="showHideSup">Show/Hide Citations</div>

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
                displayMap.put(linklst.gardiner, value)
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

    def definition = search("definition", "striking-power")
    def phonetic = null//search("phonetic", "a")
//    def phonetic2 = search("phonetic", "at")
    def gardiner = null;//search("gardiner", "g1")
%>
    <g:if test="${definition}">
        <g:each in="${definition}" var="entry">
            ${entry.definition}<sup class="source"><%= DefinitionSourceLink.findByDefinition(entry).sourceManagement.code %></sup>
            <% def defLinkList = DefinitionLink.findAll("from DefinitionLink where definition=?0", [entry]) %>
            <g:each in="${defLinkList}" var="defLink">
                ${defLink.word.gardiner}<sup class="source"><%= DefinitionSourceLink.findByDefinition(entry).sourceManagement.code %></sup> ${defLink.word.pronunciation}<sup class="source"><%= DefinitionSourceLink.findByDefinition(entry).sourceManagement.code %></sup><br/>
            </g:each>
        </g:each>
    </g:if>

    <g:if test="${phonetic}">
        <g:each in="${phonetic}" var="entry">
            ${entry.gardiner}<sup class="source"><%= WordSourceLink.findByWord(entry).sourceManagement.code %></sup>
            <%
               def defLinkList = DefinitionLink.findAll("from DefinitionLink where word=?0", [entry])
               def display = parseDefinitionDisplay(defLinkList)
            %>
            <g:each in="${display}" var="disp">
                ${disp.key}<sup class="source"><%= WordSourceLink.findByWord(entry).sourceManagement.code %></sup> --
                <g:each in="${disp.value}" var="val">
                    ${val}<sup class="source"><%= DefinitionSourceLink.findByDefinition(Definition.find("from Definition where definition=?0", [val])).sourceManagement.code %></sup>,
                </g:each>
            </g:each>
        </g:each>
    </g:if>

    <g:if test="${gardiner}">
        <br/>
        <g:each in="${gardiner}" var="entry">
            ${entry.key}<sup class="source">citation here</sup>
--}%%{--            This needs to make sure each entry has a source code attached so it can be identified where it came from --}%%{--
            <g:each in="${entry.value}" var="val">
                pronunciation here --
                <% val.eachWithIndex {it, idx ->
                    out << it.definition.definition
                    out << raw("<sup class=\"source\">${DefinitionSourceLink.findAllByDefinition(it.definition).sourceManagement.code.join(",")}</sup>")
//                    if (idx < val.size()) {
                        out << ", "
//                    }
                } %>
            </g:each>
        </g:each>
    </g:if>--}%

%{--<g:if test="${phonetic2}">
<br/>
    <g:each in="${phonetic2}" var="entry">
        ${entry.gardiner}<sup class="source"><%= WordSourceLink.findByWord(entry).sourceManagement.code %></sup>
        <%
            def defLinkList = DefinitionLink.findAll("from DefinitionLink where word=?0", [entry])
            def display = parseDefinitionDisplay(defLinkList)
        %>
        <g:each in="${display}" var="disp">
            ${disp.key}<sup class="source"><%= WordSourceLink.findByWord(entry).sourceManagement.code %></sup> --
            <g:each in="${disp.value}" var="val">
                ${val}<sup class="source"><%= DefinitionSourceLink.findByDefinition(Definition.find("from Definition where definition=?0", [val])).sourceManagement.code %></sup>,
            </g:each>
        </g:each>
    </g:each>
</g:if>--}%

<br/><br/><br/>
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
<br/><br/><br/><br/>

<%
    def hglyph = {
        def result = ''

        def n = Integer.parseInt("13000", 16)
        if (n <= 0x10FFFF) {
//        char[] ch=Character.toChars(codepoint);

//        result += String.fromCodePoint(n)
            result += Character.toChars(n);
        } else {
        //result += 'hex2Char error: Code point out of range: '+ dec2hex(n)
        }

        return result;
    }
%>
<div id="resultTest"></div>
<%
def gardiner = Gardiner.list()

gardiner.each {
    out << it
}
%>

%{--<% def hieroList = Hiero.list()
hieroList.each { %>
    <%= hglyph(it.unicodeCode) %>

<% } %>--}%
<font style="font-size:32px">
<%= result %> -- result
<script>
    %{--for (const x of dmap) {--}%

    %{--}--}%
    %{--// document.getElementById("resultTest").innerHTML = hmap['G1'].unicode;--}%
    %{--function logMapElements(value, key, map) {--}%
    %{--    console.log(map);--}%
    %{--    // INSERT INTO `egyptiandictionary`.`hieroglyphics`(`idhieroglyphics`,`unicodeCode`,`gardinerCode`,`gardinerCategory`,`adjustedGardinerCode`,`additionalDetails`)VALUES(<{idhieroglyphics: }>,<{unicodeCode: }>,<{gardinerCode: }>,<{gardinerCategory: }>,<{adjustedGardinerCode: }>,<{additionalDetails: }>);--}%
    %{--    --}%%{--console.log("INSERT INTO `egyptiandictionary`.`dictionary` (`gardiner`, `transliteration`, `translation`) VALUES (\'${dictionary.hieroglyph}\', \'${dictionary.transliteration}\', \'${dictionary.translation}\');");--}%
    %{--}--}%


    %{--hmap.forEach(logMapElements);--}%
    for (var i = 0; i < hmap.size; i++) {
        console.log(hmap[i]);
    }
</script>
</font>
%{--
    <script>
        $("#showHideSup").click(function() {
            $(".source").each(function( index ) {
                // $(this).css("display","none");
                // $(this).css("visibility","hidden");
                $(this).toggle();
            });
        });
    </script>


<!--http://unicode.org/wg2/docs/n4818-quadrat-encoding.pdf-->
<!--&#x13000;&#x13430;&#x13250;-->
<!--https://codepen.io/haradabox/pen/jryJdR-->
<!--http://hieroglyphseverywhere.blogspot.com/2016/12/web-browser-test-for-hieroglyphic.html-->
<!--https://github.com/HieroglyphsEverywhere/Fonts/tree/master/Experimental-->
<!--http://www.russellcottrell.com/greek/utilities/SurrogatePairCalculator.htm-->

<!--&#x13102; &#x13432; &#x133CF;-->
<input type="text" id="gardiner" value="D58-M17-N35-X1-G37-D2-Z1-F34-Z1"><button onclick="convert();">Convert</button>
<div id="middleaegyptische" style="font-size:36pt"></div>
<div id="translationaegyptische"></div>
<input type="text" id="lookup" value="viscera"><button onclick="lookup();">Lookup</button><br/>
<div id="lookupageyp" style="font-size:36pt"></div>
<br/><br/>
<div id="testElement"></div>

<!--<span style="font-size:36pt;padding:0">-->
<!--𓃀𓇋𓈖𓏏𓅪𓁷𓏤𓄣𓏤-->
<!--</span>-->
<br/>
<div style="font-size:36pt;position:absolute">
    <div class="single">𓃀</div>
    <div class="single">𓇋</div>
    <div class="dualOuter"><span class="dualInnerTop">𓈖</span><span class="dualInnerBot">𓏏</span></div>
    <div class="single">𓅪</div>
    <div class="dualOuter"><span class="dualInnerTop">𓁷</span><span class="dualInnerBot">𓏤</span></div>
    <div class="dualOuter"><span class="dualInnerTop">𓄣</span><span class="dualInnerBot">𓏤</span></div>
</div>
<br/><br/><br/>
<div style="font-size:36pt;position:absolute">
    <div class="dualOuter"><span class="dualInnerTop">𓃛</span><span class="dualInnerBot">𓏏</span></div>
    <div style="float:left;text-align:center;position:relative;margin-top:-15px"><span style="display:block;position:relative">𓄯</span><span style="display:block;position:relative;margin-top:-50px">𓏛</span></div>
</div>
<br/><br/><br/>
<div id="stacker" style="font-size:36pt;position:absolute"></div>
<br/><br/>
Here --
<div id="insertQueries"></div> -- Here
<script>
    // vertical
    // right to left
    // left to right -- default
    function single(sgl) {
        console.log(sgl);
        return "<div class=\"single\">" + hex2char(hmap[sgl].unicodeCode) + "</div>"
    }

    function dualStacked(dual1, dual2) {
        var d1 = dual1.includes("<") ? dual1 : hex2char(hmap[dual1].unicodeCode);
        var d2 = dual2.includes("<") ? dual2 : hex2char(hmap[dual2].unicodeCode);
        var claz = dual1.includes("<") || dual2.includes("<") ? "" : "class=\"dualOuter\"";
        return "<div" + claz + "><span class=\"dualInnerTop\">" + d1 + "</span><span class=\"dualInnerBot\">" + d2 + "</span></div>"
    }

    function tripleStacked(trip1, trip2, trip3) {
        return "<div class=\"tripleOuter\"><span class=\"tripleInnerTop\">" + hex2char(hmap[trip1].unicodeCode) + "</span><span class=\"tripleInnerMid\">" + hex2char(hmap[trip2].unicodeCode) + "</span><span class=\"tripleInnerBot\">" + hex2char(hmap[trip3].unicodeCode) + "</span></div>"
    }

    function tripleStackedSide(trip1, trip2, trip3) {
        //checks to see if there's html - if there is we're just going to include that.  if there's no html then we just lookup the unicode
        var t1 = trip1.includes("<") ? trip1 : hex2char(hmap[trip1].unicodeCode);
        var t2 = trip2.includes("<") ? trip2 : hex2char(hmap[trip2].unicodeCode);
        var t3 = trip3.includes("<") ? trip3 : hex2char(hmap[trip3].unicodeCode);

        return "<div class=\"tripleOuter\"><span class=\"tripleInnerTop\">" + t1 + "</span><span class=\"tripleInnerMid\">" + t2 + "</span><span class=\"tripleInnerBot\">" + t3 + "</span></div>"
    }

    function dualSide(dual1, dual2) {
        var d1 = dual1.includes("<") ? dual1 : hex2char(hmap[dual1].unicodeCode);
        var d2 = dual2.includes("<") ? dual2 : hex2char(hmap[dual2].unicodeCode);
        var claz = dual1.includes("<") || dual2.includes("<") ? "" : "class=\"dualSideOuter\"";
        return "<div" + claz + "><span class=\"dualSideTop\">" + d1 + "</span><span class=\"dualSideBot\">" + d2 + "</span></div>"
    }

    function dualSideStacked(dual1, dual2) {
        return "<div class=\"\"><span class=\"dualSideTop\">" + hex2char(hmap[dual1].unicodeCode) + "</span><span class=\"dualSideBot\">" + hex2char(hmap[dual2].unicodeCode) + "</span></div>"
    }

    //http://unicode.org/wg2/docs/n4818-quadrat-encoding.pdf
    //http://www.unicode.org/L2/L2016/16018-three-for-egyptian.pdf
    function formattingStackerTest() {
        var html = "";
        var str = "AA1*X1:Y1:Z2";

        //fix these so they continue to flow
        html += dualStacked(dualSide("AA1", "X1"), "Y1");

        //the idea that we can stack a set and include them in one of the sections
        html += tripleStackedSide(dualSideStacked("AA1", "X1"), "Y1", "Z2");
        html += tripleStackedSide("Y1", dualSideStacked("AA1", "X1"), "Z2");
        html += tripleStackedSide("Y1", "Z2", dualSideStacked("AA1", "X1"));

        html += dualStacked(dualSide("AA1", "X1"), "Y1");

        // var wrd = "";
        // var vert = [];
        //
        // var isVert = false;
        // for (let i = 0; i < str.length; i++) {
        //     const it = str[i];
        //     console.log(it);
        //     if (it !== "*" && it !== ":" && it !== "+") {
        //         wrd += it;
        //         if (i === str.length - 1) {
        //             if (isVert) {
        //                 vert.push(wrd);
        //             }
        //             var vlen = vert.length;
        //             if (vlen > 0) {
        //                 if (vlen === 2) {
        //                     html += dualStacked(vert[0], vert[1]);
        //                 } else if (vlen === 3) {
        //                     html += tripleStacked(vert[0], vert[1], vert[2]);
        //                 }
        //             }
        //         }
        //     } else {
        //         if (it === "*") {
        //             html += single(wrd);
        //             vert = []
        //         } else if (it === ":") {
        //             vert.push(wrd);
        //             isVert = true;
        //         } else {
        //             vert = []
        //         }
        //
        //         wrd = "";
        //     }
        // }
        //
        // console.log("yeah baby yeah");

        document.getElementById("stacker").innerHTML = html;
    }

    formattingStackerTest();

    function automatedStackerTest() {
        var html = "";
        html += dualStacked("D36", "X1");
        html += single("O1");
        html += dualStacked("D45", "D21");
        html += dualStacked("X1", "Y1");
        html += "<br/><br/>";
        html += dualStacked("D36", "X1");
        html += single("O1");
        html += single("M30");
        html += tripleStacked("X1", "N33", "N33A");
        html += "<br/><br/>";
        html += single("D36");
        html += single("U28");
        html += dualStacked("G1", "Z7");
        html += single("A24");
        html += "<br/><br/>";
        html += dualStacked("D36", "Q3");
        html += dualStacked("N35", "N35");
        html += single("F27");

        document.getElementById("stacker").innerHTML = html;
    }

    // automatedStackerTest();

    function hex2char ( hex ) {
        // converts a single hex number to a character
        // note that no checking is performed to ensure that this is just a hex number, eg. no spaces etc
        // hex: string, the hex codepoint to be converted
        var result = ''
        var n = parseInt(hex, 16)
        if (n <= 0x10FFFF) {
            result += String.fromCodePoint(n)
        } else {
            //result += 'hex2Char error: Code point out of range: '+ dec2hex(n)
        }

        return result
    }

    function convert() {
        var html = "";
        // var tc = "G1-G1-X1-G1-D40-A14";//document.getElementById("gardiner")
        //"I10-N35-V28-F51-N35-M36-D21-G43-G43-F51";//
        var tc = document.getElementById("gardiner").value;
        var delimiter = "-";
        if (tc.indexOf(" ") !== -1) {
            delimiter = " ";
        }
        var splarr = tc.split(delimiter);
        for (const str of splarr) {
            html += hex2char(hmap[str].unicodeCode);
        }

        var dhtml = dmap[tc];
        if (dhtml !== undefined) {
            document.getElementById("translationaegyptische").innerHTML = JSON.stringify(dhtml);
        }

        document.getElementById("middleaegyptische").innerHTML = html;
    }

    function lookup() {
        var tc = document.getElementById("lookup").value;
        var dhtml = entryMap[tc];
        var html = "";
        if (dhtml !== undefined) {
            var splarr = dhtml.split(",");
            for (const splarrElement of splarr) {
                var hexSplit = splarrElement.split("-");
                for (const str of hexSplit) {
                    html += hex2char(hmap[str].unicodeCode);
                }
                // // html += dmap(splarrElement);
                html += "<br/>";
            }
            document.getElementById("lookupageyp").innerHTML = html;
        }
    }

    convert();

    // document.getElementById("insertQueries").innerHTML = insertHTML;

    // function logMapElements(value, key, map) {
    --}%%{--//     console.log("INSERT INTO `egyptiandictionary`.`dictionary` (`gardiner`, `transliteration`, `translation`) VALUES (\'${dictionary.hieroglyph}\', \'${dictionary.transliteration}\', \'${dictionary.translation}\');");--}%%{--
    // }
    //
    // function makeInsert() {
    //     dmap.forEach(logMapElements);
    // }
    //
    // makeInsert();

    // test
    // var tmpl = "BOB WAS HERE -- ";
    // tmpl += hex2char("1315C");
    // tmpl += hex2char("13435");
    // tmpl += hex2char("133CF");
    // document.getElementById("insertQueries").innerHTML = tmpl;

</script>--}%
</body>
</html>
