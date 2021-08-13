package net.kemetdict.views

import grails.gorm.services.Service

@Service(GardinerView)
interface GardinerViewService {

    GardinerView get(Serializable id)

    List<GardinerView> list(Map args)

    Long count()

    void delete(Serializable id)

    GardinerView save(GardinerView gardinerView)

}