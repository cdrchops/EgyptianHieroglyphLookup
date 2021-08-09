package net.kemetdict.data

import grails.gorm.services.Service

@Service(Word)
interface WordService {

    Word get(Serializable id)

    List<Word> list(Map args)

    Long count()

    void delete(Serializable id)

    Word save(Word word)

}