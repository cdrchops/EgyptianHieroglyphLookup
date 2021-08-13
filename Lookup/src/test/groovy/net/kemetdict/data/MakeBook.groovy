package net.kemetdict.data

import net.kemetdict.data.Gardiner
import net.kemetdict.data.Hiero
import net.kemetdict.data.Translate
import net.kemetdict.data.Transliteration

def gardiner = new File("gardiner.tsv").readLines()
def hiero = new File("hiero.tsv").readLines()
def translation = new File("translation.tsv").readLines()
def transliterate = new File("transliteration.tsv").readLines()

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

def hieroMap = [:]
hiero.each {
    def tmp = new Hiero()
    def starr = it.split(",")

    starr.eachWithIndex {
        switch (it) {
            case 0:
                tmp.id = it
                break
            case 1:
                tmp.version = it
                break
            case 2:
                tmp.unicodeCode = it
                break
            case 3:
                tmp.gardinerCode = it
                break
            case 4:
                tmp.gardinerCategory = it
                break
            case 5:
                tmp.adjustedGardinerCode = it
                break
            case 6:
                tmp.additionalDetails = it
                break
        }
    }

    hieroMap.put(tmp.gardinerCode, tmp)
}

def gardinerMap = [:]
gardiner.each {
    def tmp = new Gardiner()
    def starr = it.split("\t")

    starr.eachWithIndex { String entry, int i ->
        switch (it) {
            case 0:
                tmp.gardiner = it
                break
            case 1:
                tmp.transliteration = it
                break
            case 2:
                tmp.translation = it
                break
        }
    }

    def tmpg = new StringBuilder()
    tmp.gardiner.split("-").each {
        tmpg << hglyph(hieroMap.get(it).unicodeCode)
    }

    println tmpg
}