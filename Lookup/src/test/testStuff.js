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

// formattingStackerTest();

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