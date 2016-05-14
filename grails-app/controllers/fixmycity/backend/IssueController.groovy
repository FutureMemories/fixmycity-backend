package fixmycity.backend

import grails.rest.RestfulController
import org.springframework.http.HttpStatus

class IssueController extends RestfulController {
    static responseFormats = ['json', 'xml']
    IssueController() {
        super(Issue)
    }

    def index() {

        respond Issue.list()
    }

    def update() {
        log.info("update")
    }

    def show() {
        log.info("show")
    }

    def save() {
        log.info("save")
    }

}
