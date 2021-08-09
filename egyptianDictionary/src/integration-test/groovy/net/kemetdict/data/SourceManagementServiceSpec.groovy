package net.kemetdict.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SourceManagementServiceSpec extends Specification {

    SourceManagementService sourceManagementService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SourceManagement(...).save(flush: true, failOnError: true)
        //new SourceManagement(...).save(flush: true, failOnError: true)
        //SourceManagement sourceManagement = new SourceManagement(...).save(flush: true, failOnError: true)
        //new SourceManagement(...).save(flush: true, failOnError: true)
        //new SourceManagement(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sourceManagement.id
    }

    void "test get"() {
        setupData()

        expect:
        sourceManagementService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SourceManagement> sourceManagementList = sourceManagementService.list(max: 2, offset: 2)

        then:
        sourceManagementList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        sourceManagementService.count() == 5
    }

    void "test delete"() {
        Long sourceManagementId = setupData()

        expect:
        sourceManagementService.count() == 5

        when:
        sourceManagementService.delete(sourceManagementId)
        sessionFactory.currentSession.flush()

        then:
        sourceManagementService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SourceManagement sourceManagement = new SourceManagement()
        sourceManagementService.save(sourceManagement)

        then:
        sourceManagement.id != null
    }
}
