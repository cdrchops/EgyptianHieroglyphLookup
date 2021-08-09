package net.kemetdict.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DefinitionSourceLinkServiceSpec extends Specification {

    DefinitionSourceLinkService definitionSourceLinkService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DefinitionSourceLink(...).save(flush: true, failOnError: true)
        //new DefinitionSourceLink(...).save(flush: true, failOnError: true)
        //DefinitionSourceLink definitionSourceLink = new DefinitionSourceLink(...).save(flush: true, failOnError: true)
        //new DefinitionSourceLink(...).save(flush: true, failOnError: true)
        //new DefinitionSourceLink(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //definitionSourceLink.id
    }

    void "test get"() {
        setupData()

        expect:
        definitionSourceLinkService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DefinitionSourceLink> definitionSourceLinkList = definitionSourceLinkService.list(max: 2, offset: 2)

        then:
        definitionSourceLinkList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        definitionSourceLinkService.count() == 5
    }

    void "test delete"() {
        Long definitionSourceLinkId = setupData()

        expect:
        definitionSourceLinkService.count() == 5

        when:
        definitionSourceLinkService.delete(definitionSourceLinkId)
        sessionFactory.currentSession.flush()

        then:
        definitionSourceLinkService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DefinitionSourceLink definitionSourceLink = new DefinitionSourceLink()
        definitionSourceLinkService.save(definitionSourceLink)

        then:
        definitionSourceLink.id != null
    }
}
