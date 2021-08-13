package net.kemetdict.views

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TranslateViewServiceSpec extends Specification {

    TranslateViewService translateViewService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TranslateView(...).save(flush: true, failOnError: true)
        //new TranslateView(...).save(flush: true, failOnError: true)
        //TranslateView translateView = new TranslateView(...).save(flush: true, failOnError: true)
        //new TranslateView(...).save(flush: true, failOnError: true)
        //new TranslateView(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //translateView.id
    }

    void "test get"() {
        setupData()

        expect:
        translateViewService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TranslateView> translateViewList = translateViewService.list(max: 2, offset: 2)

        then:
        translateViewList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        translateViewService.count() == 5
    }

    void "test delete"() {
        Long translateViewId = setupData()

        expect:
        translateViewService.count() == 5

        when:
        translateViewService.delete(translateViewId)
        sessionFactory.currentSession.flush()

        then:
        translateViewService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TranslateView translateView = new TranslateView()
        translateViewService.save(translateView)

        then:
        translateView.id != null
    }
}
