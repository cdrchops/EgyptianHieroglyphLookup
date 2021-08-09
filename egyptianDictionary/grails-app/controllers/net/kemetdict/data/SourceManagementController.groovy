package net.kemetdict.data

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SourceManagementController {

    SourceManagementService sourceManagementService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond sourceManagementService.list(params), model:[sourceManagementCount: sourceManagementService.count()]
    }

    def show(Long id) {
        respond sourceManagementService.get(id)
    }

    def create() {
        respond new SourceManagement(params)
    }

    def save(SourceManagement sourceManagement) {
        if (sourceManagement == null) {
            notFound()
            return
        }

        try {
            sourceManagementService.save(sourceManagement)
        } catch (ValidationException e) {
            respond sourceManagement.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sourceManagement.label', default: 'SourceManagement'), sourceManagement.id])
                redirect sourceManagement
            }
            '*' { respond sourceManagement, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond sourceManagementService.get(id)
    }

    def update(SourceManagement sourceManagement) {
        if (sourceManagement == null) {
            notFound()
            return
        }

        try {
            sourceManagementService.save(sourceManagement)
        } catch (ValidationException e) {
            respond sourceManagement.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sourceManagement.label', default: 'SourceManagement'), sourceManagement.id])
                redirect sourceManagement
            }
            '*'{ respond sourceManagement, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        sourceManagementService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sourceManagement.label', default: 'SourceManagement'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sourceManagement.label', default: 'SourceManagement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
