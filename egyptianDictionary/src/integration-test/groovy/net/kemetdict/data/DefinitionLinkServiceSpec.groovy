package net.kemetdict.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DefinitionLinkServiceSpec extends Specification {

    DefinitionLinkService definitionLinkService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DefinitionLink(...).save(flush: true, failOnError: true)
        //new DefinitionLink(...).save(flush: true, failOnError: true)
        //DefinitionLink definitionLink = new DefinitionLink(...).save(flush: true, failOnError: true)
        //new DefinitionLink(...).save(flush: true, failOnError: true)
        //new DefinitionLink(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //definitionLink.id
    }

    void "test get"() {
        setupData()

        expect:
        definitionLinkService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DefinitionLink> definitionLinkList = definitionLinkService.list(max: 2, offset: 2)

        then:
        definitionLinkList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        definitionLinkService.count() == 5
    }

    void "test delete"() {
        Long definitionLinkId = setupData()

        expect:
        definitionLinkService.count() == 5

        when:
        definitionLinkService.delete(definitionLinkId)
        sessionFactory.currentSession.flush()

        then:
        definitionLinkService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DefinitionLink definitionLink = new DefinitionLink()
        definitionLinkService.save(definitionLink)

        then:
        definitionLink.id != null
    }
}
