package net.kemetdict.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class WordDefinitionSourceLinkServiceSpec extends Specification {

    WordDefinitionSourceLinkService wordDefinitionSourceLinkService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new WordDefinitionSourceLink(...).save(flush: true, failOnError: true)
        //new WordDefinitionSourceLink(...).save(flush: true, failOnError: true)
        //WordDefinitionSourceLink wordDefinitionSourceLink = new WordDefinitionSourceLink(...).save(flush: true, failOnError: true)
        //new WordDefinitionSourceLink(...).save(flush: true, failOnError: true)
        //new WordDefinitionSourceLink(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //wordDefinitionSourceLink.id
    }

    void "test get"() {
        setupData()

        expect:
        wordDefinitionSourceLinkService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<WordDefinitionSourceLink> wordDefinitionSourceLinkList = wordDefinitionSourceLinkService.list(max: 2, offset: 2)

        then:
        wordDefinitionSourceLinkList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        wordDefinitionSourceLinkService.count() == 5
    }

    void "test delete"() {
        Long wordDefinitionSourceLinkId = setupData()

        expect:
        wordDefinitionSourceLinkService.count() == 5

        when:
        wordDefinitionSourceLinkService.delete(wordDefinitionSourceLinkId)
        sessionFactory.currentSession.flush()

        then:
        wordDefinitionSourceLinkService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        WordDefinitionSourceLink wordDefinitionSourceLink = new WordDefinitionSourceLink()
        wordDefinitionSourceLinkService.save(wordDefinitionSourceLink)

        then:
        wordDefinitionSourceLink.id != null
    }
}
