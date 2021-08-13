package net.kemetdict.views

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class GardinerViewController {

    GardinerViewService gardinerViewService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond gardinerViewService.list(params), model:[gardinerViewCount: gardinerViewService.count()]
    }

    def show(Long id) {
        respond gardinerViewService.get(id)
    }

    def create() {
        respond new GardinerView(params)
    }

    def save(GardinerView gardinerView) {
        if (gardinerView == null) {
            notFound()
            return
        }

        try {
            gardinerViewService.save(gardinerView)
        } catch (ValidationException e) {
            respond gardinerView.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gardinerView.label', default: 'GardinerView'), gardinerView.id])
                redirect gardinerView
            }
            '*' { respond gardinerView, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond gardinerViewService.get(id)
    }

    def update(GardinerView gardinerView) {
        if (gardinerView == null) {
            notFound()
            return
        }

        try {
            gardinerViewService.save(gardinerView)
        } catch (ValidationException e) {
            respond gardinerView.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'gardinerView.label', default: 'GardinerView'), gardinerView.id])
                redirect gardinerView
            }
            '*'{ respond gardinerView, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        gardinerViewService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'gardinerView.label', default: 'GardinerView'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gardinerView.label', default: 'GardinerView'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
