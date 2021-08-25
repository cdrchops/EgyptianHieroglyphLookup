function parseGardiner(tmp, delimiter) {
    var splarr = tmp.split(delimiter);
    var uni = "<span class=\"hieroglyphDisplay\">";
    for (const str of splarr) {
        var tmpmap = hmap[str.trim()];
        if (tmpmap) {
            uni += hex2char(hmap[str.trim()].unicodeCode);
        } else {
            var tmp = hpngmap[str.trim()];
            if (tmp !== undefined && tmp !== "") {
                uni += "<img src=\"img/" + tmp + "\">"
            } else {
                uni += "<span class=\"normalFont\">" + str + "</span>";
            }
        }
    }
    uni += "</span>";

    return uni;
}

function processGardiner(tmp) {
    // var tmpa = tmp.gardiner.split("-")
    var delimiter = "-";
    /*if (tmp.indexOf(" ") !== -1) {
        delimiter = " ";
    }*/
    var uni = "";
    if (tmp.indexOf(",") !== -1) {
        var comsplarr = tmp.split(",")
        for (const com of comsplarr) {
            // console.log("compsplarr " + com);

            uni += parseGardiner(com, delimiter);
            uni += ",&nbsp;&nbsp;"
        }
    } else {
        var number = dmap[tmp].entryNumber;
        uni += "<a name=\"" + number + "\"></a>";
        uni += parseGardiner(tmp, delimiter);
    }

    return uni;
}

function processGardinerSuperscript(tmp) {
    // var tmpa = tmp.gardiner.split("-")
    var delimiter = "-";
    /*if (tmp.indexOf(" ") !== -1) {
        delimiter = " ";
    }*/
    var uni = "";
    if (tmp.indexOf(",") !== -1) {
        var comsplarr = tmp.split(",")
        for (var com of comsplarr) {
            com = com.trim();
            var lookup = dmap[com];
            if (lookup?.entryNumber === undefined) {
                console.log(com);
                // console.log(dmap[com]);//.entryNumber;
            } else {

                uni += parseGardiner(com, delimiter);
                var number = dmap[com].entryNumber;
                uni += "<sup><a href=\"#" + number + "\">" + number + "</a></sup>";
                // uni += "<sup>" + lookup.entryNumber + "</sup>"
                uni += ",&nbsp;&nbsp;"
            }
        }
    } else {
        // var lookup = dmap[tmp].entryNumber;
        uni += parseGardiner(tmp, delimiter);

        // uni += "<sup>" + lookup + "</sup>"
        var number = dmap[tmp].entryNumber;
        uni += "<sup><a href=\"#" + number + "\">" + number + "</a></sup>";
    }

    return uni;
}

function displaySection(firstCell, secondCell, cellNumber) {
    if (cellNumber !== "") {
        return "<div class='row'><div class='cellNumber'>" + cellNumber + "</div><div class='cellA'>" + firstCell + "</div><div class='cellB'>" + secondCell + " </div></div>";
    } else {
        return "<div class='row'><div class='cellA'>" + firstCell + "</div><div class='cellB'>" + secondCell + "</div><div class='cellNumber'> </div></div>";
    }
}

function iteration() {
    // var html = "<table>";
    var html = "";

    for (var d in dmap) {
        var dict = dmap[d];
        var uni = processGardiner(dict.gardiner);

        html += displaySection(uni, "[<b>" + dict.transliteration + "</b>] " + dict.translation + " <br/>{" + dict.gardiner + "} <br/>", dict.entryNumber);
        // html += "<tr><td>" + uni + "</td><td>" +"&nbsp;&nbsp;&nbsp;&nbsp;[<b>" + dict.transliteration + "</b>] <b>" + dict.translation + "</b> {" + dict.gardiner + "}"+ " </td></tr>";
        // html += "<div class='row'><div class='cell'>" + uni + "</div><div class='cell'>" +"&nbsp;&nbsp;&nbsp;&nbsp;[<b>" + dict.transliteration + "</b>] <b>" + dict.translation + "</b> {" + dict.gardiner + "}"+ " </div></div>";
    }
    // html += "</table><br/>";

    document.getElementById("hiero").innerHTML = html;
}

iteration();

function phoneti() {
    var html = "";
    // var html = "<table>";

    for (var d in phonmap) {
        var dict = phonmap[d];
        var uni = processGardinerSuperscript(dict.gardiner);

        html += displaySection(dict.transliteration, uni + "  <br/>", "");
        // html += "<tr><td>" + dict.transliteration + "</td><td>" +"&nbsp;&nbsp;&nbsp;&nbsp;<b>" + uni + "</b>  " + dict.translation + " {" + dict.gardiner + "}"+ " </td></tr>";
    }

    // html += "</table><br/>";

    document.getElementById("phonet").innerHTML = html;
}

phoneti();

function definition() {
    // var html = "<table>";
    var html = "";

    for (var d in defmap) {
        var dict = defmap[d];
        var uni = processGardinerSuperscript(dict.gardiner);

        html += displaySection(dict.translation, "" + uni + "<br/>", "");
        // html += "<tr><td>" + dict.translation + "</td><td>" +"&nbsp;&nbsp;&nbsp;&nbsp;" + uni + " [<b>" + dict.transliteration + "</b>]  {" + dict.gardiner + "}"+ "</td></tr>";
    }
    // html += "</table><br/>";

    document.getElementById("defi").innerHTML = html;
}

definition();