package net.kemetdict.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DefinitionServiceSpec extends Specification {

    DefinitionService definitionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Definition(...).save(flush: true, failOnError: true)
        //new Definition(...).save(flush: true, failOnError: true)
        //Definition definition = new Definition(...).save(flush: true, failOnError: true)
        //new Definition(...).save(flush: true, failOnError: true)
        //new Definition(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //definition.id
    }

    void "test get"() {
        setupData()

        expect:
        definitionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Definition> definitionList = definitionService.list(max: 2, offset: 2)

        then:
        definitionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        definitionService.count() == 5
    }

    void "test delete"() {
        Long definitionId = setupData()

        expect:
        definitionService.count() == 5

        when:
        definitionService.delete(definitionId)
        sessionFactory.currentSession.flush()

        then:
        definitionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Definition definition = new Definition()
        definitionService.save(definition)

        then:
        definition.id != null
    }
}
