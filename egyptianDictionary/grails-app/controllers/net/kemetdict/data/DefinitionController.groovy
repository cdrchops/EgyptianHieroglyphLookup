package net.kemetdict.data

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DefinitionController {

    DefinitionService definitionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond definitionService.list(params), model:[definitionCount: definitionService.count()]
    }

    def show(Long id) {
        respond definitionService.get(id)
    }

    def create() {
        respond new Definition(params)
    }

    def save(Definition definition) {
        if (definition == null) {
            notFound()
            return
        }

        try {
            definitionService.save(definition)
        } catch (ValidationException e) {
            respond definition.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'definition.label', default: 'Definition'), definition.id])
                redirect definition
            }
            '*' { respond definition, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond definitionService.get(id)
    }

    def update(Definition definition) {
        if (definition == null) {
            notFound()
            return
        }

        try {
            definitionService.save(definition)
        } catch (ValidationException e) {
            respond definition.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'definition.label', default: 'Definition'), definition.id])
                redirect definition
            }
            '*'{ respond definition, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        definitionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'definition.label', default: 'Definition'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'definition.label', default: 'Definition'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
