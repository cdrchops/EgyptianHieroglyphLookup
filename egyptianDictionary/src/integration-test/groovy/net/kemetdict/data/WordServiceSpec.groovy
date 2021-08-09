package net.kemetdict.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class WordServiceSpec extends Specification {

    WordService wordService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Word(...).save(flush: true, failOnError: true)
        //new Word(...).save(flush: true, failOnError: true)
        //Word word = new Word(...).save(flush: true, failOnError: true)
        //new Word(...).save(flush: true, failOnError: true)
        //new Word(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //word.id
    }

    void "test get"() {
        setupData()

        expect:
        wordService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Word> wordList = wordService.list(max: 2, offset: 2)

        then:
        wordList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        wordService.count() == 5
    }

    void "test delete"() {
        Long wordId = setupData()

        expect:
        wordService.count() == 5

        when:
        wordService.delete(wordId)
        sessionFactory.currentSession.flush()

        then:
        wordService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Word word = new Word()
        wordService.save(word)

        then:
        word.id != null
    }
}
