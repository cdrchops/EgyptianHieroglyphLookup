package net.kemetdict.data

class Definition {
    static mapping = {
        definition type: 'text'
    }

    String definition

    String toString() {
        "$id -- $definition"
    }
}
