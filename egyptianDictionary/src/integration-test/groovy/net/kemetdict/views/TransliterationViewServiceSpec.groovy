package net.kemetdict.views

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TransliterationViewServiceSpec extends Specification {

    TransliterationViewService transliterationViewService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TransliterationView(...).save(flush: true, failOnError: true)
        //new TransliterationView(...).save(flush: true, failOnError: true)
        //TransliterationView transliterationView = new TransliterationView(...).save(flush: true, failOnError: true)
        //new TransliterationView(...).save(flush: true, failOnError: true)
        //new TransliterationView(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //transliterationView.id
    }

    void "test get"() {
        setupData()

        expect:
        transliterationViewService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TransliterationView> transliterationViewList = transliterationViewService.list(max: 2, offset: 2)

        then:
        transliterationViewList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        transliterationViewService.count() == 5
    }

    void "test delete"() {
        Long transliterationViewId = setupData()

        expect:
        transliterationViewService.count() == 5

        when:
        transliterationViewService.delete(transliterationViewId)
        sessionFactory.currentSession.flush()

        then:
        transliterationViewService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TransliterationView transliterationView = new TransliterationView()
        transliterationViewService.save(transliterationView)

        then:
        transliterationView.id != null
    }
}
