package net.kemetdict.data

import grails.gorm.services.Service

@Service(WordDefinitionSourceLink)
interface WordDefinitionSourceLinkService {

    WordDefinitionSourceLink get(Serializable id)

    List<WordDefinitionSourceLink> list(Map args)

    Long count()

    void delete(Serializable id)

    WordDefinitionSourceLink save(WordDefinitionSourceLink wordDefinitionSourceLink)

}