package net.kemetdict.data

import grails.gorm.services.Service

@Service(DefinitionLink)
interface DefinitionLinkService {

    DefinitionLink get(Serializable id)

    List<DefinitionLink> list(Map args)

    Long count()

    void delete(Serializable id)

    DefinitionLink save(DefinitionLink definitionLink)

}