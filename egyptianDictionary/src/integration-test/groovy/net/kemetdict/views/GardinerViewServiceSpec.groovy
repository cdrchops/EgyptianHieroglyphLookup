package net.kemetdict.views

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class GardinerViewServiceSpec extends Specification {

    GardinerViewService gardinerViewService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new GardinerView(...).save(flush: true, failOnError: true)
        //new GardinerView(...).save(flush: true, failOnError: true)
        //GardinerView gardinerView = new GardinerView(...).save(flush: true, failOnError: true)
        //new GardinerView(...).save(flush: true, failOnError: true)
        //new GardinerView(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //gardinerView.id
    }

    void "test get"() {
        setupData()

        expect:
        gardinerViewService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<GardinerView> gardinerViewList = gardinerViewService.list(max: 2, offset: 2)

        then:
        gardinerViewList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        gardinerViewService.count() == 5
    }

    void "test delete"() {
        Long gardinerViewId = setupData()

        expect:
        gardinerViewService.count() == 5

        when:
        gardinerViewService.delete(gardinerViewId)
        sessionFactory.currentSession.flush()

        then:
        gardinerViewService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        GardinerView gardinerView = new GardinerView()
        gardinerViewService.save(gardinerView)

        then:
        gardinerView.id != null
    }
}
