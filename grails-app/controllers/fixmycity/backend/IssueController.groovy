package fixmycity.backend
import grails.rest.RestfulController

class IssueController extends RestfulController {
    static responseFormats = ['json', 'xml']
    IssueController() {
        super(Issue)
    }

    def index() {
        def issues = Issue.list()

        respond issues
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
