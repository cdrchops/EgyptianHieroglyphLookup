package net.kemetdict.data

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class WordDefinitionSourceLinkController {

    WordDefinitionSourceLinkService wordDefinitionSourceLinkService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond wordDefinitionSourceLinkService.list(params), model:[wordDefinitionSourceLinkCount: wordDefinitionSourceLinkService.count()]
    }

    def show(Long id) {
        respond wordDefinitionSourceLinkService.get(id)
    }

    def create() {
        respond new WordDefinitionSourceLink(params)
    }

    def save(WordDefinitionSourceLink wordDefinitionSourceLink) {
        if (wordDefinitionSourceLink == null) {
            notFound()
            return
        }

        try {
            wordDefinitionSourceLinkService.save(wordDefinitionSourceLink)
        } catch (ValidationException e) {
            respond wordDefinitionSourceLink.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'wordDefinitionSourceLink.label', default: 'WordDefinitionSourceLink'), wordDefinitionSourceLink.id])
                redirect wordDefinitionSourceLink
            }
            '*' { respond wordDefinitionSourceLink, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond wordDefinitionSourceLinkService.get(id)
    }

    def update(WordDefinitionSourceLink wordDefinitionSourceLink) {
        if (wordDefinitionSourceLink == null) {
            notFound()
            return
        }

        try {
            wordDefinitionSourceLinkService.save(wordDefinitionSourceLink)
        } catch (ValidationException e) {
            respond wordDefinitionSourceLink.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'wordDefinitionSourceLink.label', default: 'WordDefinitionSourceLink'), wordDefinitionSourceLink.id])
                redirect wordDefinitionSourceLink
            }
            '*'{ respond wordDefinitionSourceLink, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        wordDefinitionSourceLinkService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'wordDefinitionSourceLink.label', default: 'WordDefinitionSourceLink'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wordDefinitionSourceLink.label', default: 'WordDefinitionSourceLink'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
