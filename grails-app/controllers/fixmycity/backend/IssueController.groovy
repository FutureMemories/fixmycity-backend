package fixmycity.backend

import grails.rest.RestfulController

class IssueController extends RestfulController {
    static responseFormats = ['json', 'xml']

    IssueController() {
        super(Issue)
    }

    def save() {

        def img = request.getFile('image')
        println(img.bytes)

        def issue = new Issue(issueImage : img.bytes)
        issue.save()


        def res = ["asdddd" : 1]
        respond res
    }



    def index() {
        println("Listing")
        def issues = Issue.list()
        println(issues.size())
        respond issues.collect { i ->
            def pos = ["long": i.position.longitude, "lat": i.position.latitude]

            ["type"    : i.issueType,
             "category": i.issueCategory,
             "comment" : i.comment,
             "position": pos
            ]
        }

    }

}