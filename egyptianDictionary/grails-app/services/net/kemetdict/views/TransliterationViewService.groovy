package net.kemetdict.views

import grails.gorm.services.Service

@Service(TransliterationView)
interface TransliterationViewService {

    TransliterationView get(Serializable id)

    List<TransliterationView> list(Map args)

    Long count()

    void delete(Serializable id)

    TransliterationView save(TransliterationView transliterationView)

}