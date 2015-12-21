<%=packageName ? "package ${packageName}" : ''%>

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ${className}Controller {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ${className}.list(params), model:[${propertyName}Count: ${className}.count()]
    }

    def show(${className} ${propertyName}) {
        respond ${propertyName}
    }

    @Transactional
    def save(${className} ${propertyName}) {
        if (${propertyName} == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (${propertyName}.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ${propertyName}.errors, view:'create'
            return
        }

        ${propertyName}.save flush:true

        respond ${propertyName}, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(${className} ${propertyName}) {
        if (${propertyName} == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (${propertyName}.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ${propertyName}.errors, view:'edit'
            return
        }

        ${propertyName}.save flush:true

        respond ${propertyName}, [status: OK, view:"show"]
    }

    @Transactional
    def delete(${className} ${propertyName}) {

        if (${propertyName} == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        ${propertyName}.delete flush:true

        render status: NO_CONTENT
    }
}
