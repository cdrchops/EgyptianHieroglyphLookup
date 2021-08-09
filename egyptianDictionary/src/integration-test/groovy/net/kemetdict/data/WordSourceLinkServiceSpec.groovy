package net.kemetdict.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class WordSourceLinkServiceSpec extends Specification {

    WordSourceLinkService wordSourceLinkService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new WordSourceLink(...).save(flush: true, failOnError: true)
        //new WordSourceLink(...).save(flush: true, failOnError: true)
        //WordSourceLink wordSourceLink = new WordSourceLink(...).save(flush: true, failOnError: true)
        //new WordSourceLink(...).save(flush: true, failOnError: true)
        //new WordSourceLink(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //wordSourceLink.id
    }

    void "test get"() {
        setupData()

        expect:
        wordSourceLinkService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<WordSourceLink> wordSourceLinkList = wordSourceLinkService.list(max: 2, offset: 2)

        then:
        wordSourceLinkList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        wordSourceLinkService.count() == 5
    }

    void "test delete"() {
        Long wordSourceLinkId = setupData()

        expect:
        wordSourceLinkService.count() == 5

        when:
        wordSourceLinkService.delete(wordSourceLinkId)
        sessionFactory.currentSession.flush()

        then:
        wordSourceLinkService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        WordSourceLink wordSourceLink = new WordSourceLink()
        wordSourceLinkService.save(wordSourceLink)

        then:
        wordSourceLink.id != null
    }
}
