package fixmycity.backend

import grails.rest.RestfulController

class IssueController extends RestfulController {
    static responseFormats = ['json', 'xml']

    IssueController() {
        super(Issue)
    }

    def save() {

        def img = request.getFile('image')
        def type = request.getParameter("type")
        def category = request.getParameter("category")



        def issue = new Issue(issueType: type,
                issueCategory: category,
                issueImage: img.bytes,
                longitude: 11,
                latitude: 11
            )

        issue.save()



        def res = ["id": issue.id]
        respond res
    }


    def index() {
        println("Listing")
        def issues = Issue.list()
        println(issues.size())
        respond issues.collect { i ->
            def pos = ["long": i.longitude, "lat": i.latitude]

            ["type"    : i.issueType,
             "category": i.issueCategory,
             "comment" : i.comment,
             "position": pos
            ]
        }

    }

}
