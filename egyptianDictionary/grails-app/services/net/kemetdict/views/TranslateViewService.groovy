package net.kemetdict.views

import grails.gorm.services.Service

@Service(TranslateView)
interface TranslateViewService {

    TranslateView get(Serializable id)

    List<TranslateView> list(Map args)

    Long count()

    void delete(Serializable id)

    TranslateView save(TranslateView translateView)

}