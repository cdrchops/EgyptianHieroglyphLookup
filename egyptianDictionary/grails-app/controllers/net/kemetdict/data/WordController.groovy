package net.kemetdict.data

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class WordController {

    WordService wordService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond wordService.list(params), model:[wordCount: wordService.count()]
    }

    def show(Long id) {
        respond wordService.get(id)
    }

    def create() {
        respond new Word(params)
    }

    def save(Word word) {
        if (word == null) {
            notFound()
            return
        }

        try {
            wordService.save(word)
        } catch (ValidationException e) {
            respond word.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'word.label', default: 'Word'), word.id])
                redirect word
            }
            '*' { respond word, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond wordService.get(id)
    }

    def update(Word word) {
        if (word == null) {
            notFound()
            return
        }

        try {
            wordService.save(word)
        } catch (ValidationException e) {
            respond word.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'word.label', default: 'Word'), word.id])
                redirect word
            }
            '*'{ respond word, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        wordService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'word.label', default: 'Word'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'word.label', default: 'Word'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
