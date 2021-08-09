package net.kemetdict.data

import grails.gorm.services.Service

@Service(DefinitionSourceLink)
interface DefinitionSourceLinkService {

    DefinitionSourceLink get(Serializable id)

    List<DefinitionSourceLink> list(Map args)

    Long count()

    void delete(Serializable id)

    DefinitionSourceLink save(DefinitionSourceLink definitionSourceLink)

}