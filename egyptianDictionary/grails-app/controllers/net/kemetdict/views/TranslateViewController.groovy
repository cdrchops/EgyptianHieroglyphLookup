package net.kemetdict.views

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TranslateViewController {

    TranslateViewService translateViewService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond translateViewService.list(params), model:[translateViewCount: translateViewService.count()]
    }

    def show(Long id) {
        respond translateViewService.get(id)
    }

    def create() {
        respond new TranslateView(params)
    }

    def save(TranslateView translateView) {
        if (translateView == null) {
            notFound()
            return
        }

        try {
            translateViewService.save(translateView)
        } catch (ValidationException e) {
            respond translateView.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'translateView.label', default: 'TranslateView'), translateView.id])
                redirect translateView
            }
            '*' { respond translateView, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond translateViewService.get(id)
    }

    def update(TranslateView translateView) {
        if (translateView == null) {
            notFound()
            return
        }

        try {
            translateViewService.save(translateView)
        } catch (ValidationException e) {
            respond translateView.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'translateView.label', default: 'TranslateView'), translateView.id])
                redirect translateView
            }
            '*'{ respond translateView, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        translateViewService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'translateView.label', default: 'TranslateView'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'translateView.label', default: 'TranslateView'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
