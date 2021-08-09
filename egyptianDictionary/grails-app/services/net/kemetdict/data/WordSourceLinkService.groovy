package net.kemetdict.data

import grails.gorm.services.Service

@Service(WordSourceLink)
interface WordSourceLinkService {

    WordSourceLink get(Serializable id)

    List<WordSourceLink> list(Map args)

    Long count()

    void delete(Serializable id)

    WordSourceLink save(WordSourceLink wordSourceLink)

}