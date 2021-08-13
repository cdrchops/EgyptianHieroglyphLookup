package net.kemetdict.views

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TransliterationViewController {

    TransliterationViewService transliterationViewService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond transliterationViewService.list(params), model:[transliterationViewCount: transliterationViewService.count()]
    }

    def show(Long id) {
        respond transliterationViewService.get(id)
    }

    def create() {
        respond new TransliterationView(params)
    }

    def save(TransliterationView transliterationView) {
        if (transliterationView == null) {
            notFound()
            return
        }

        try {
            transliterationViewService.save(transliterationView)
        } catch (ValidationException e) {
            respond transliterationView.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'transliterationView.label', default: 'TransliterationView'), transliterationView.id])
                redirect transliterationView
            }
            '*' { respond transliterationView, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond transliterationViewService.get(id)
    }

    def update(TransliterationView transliterationView) {
        if (transliterationView == null) {
            notFound()
            return
        }

        try {
            transliterationViewService.save(transliterationView)
        } catch (ValidationException e) {
            respond transliterationView.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'transliterationView.label', default: 'TransliterationView'), transliterationView.id])
                redirect transliterationView
            }
            '*'{ respond transliterationView, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        transliterationViewService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'transliterationView.label', default: 'TransliterationView'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'transliterationView.label', default: 'TransliterationView'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
