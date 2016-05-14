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
        def longitude = request.getParameter("long")
        def latitude = request.getParameter("lat")
        def comment = request.getParameter("comment")

        def issue = new Issue(issueType: type,
                issueCategory: category,
                issueImage: img.bytes,
                longitude: longitude,
                latitude: latitude,
                comment: comment

        )

        issue.save()



        def res = ["id": issue.id]
        respond res
    }


    def index() {
        def issues = Issue.list()



        respond issues.collect { i ->
            def pos = ["long": i.longitude, "lat": i.latitude]

            def imageUrl = null
            if (i.issueImage != null && i.issueImage.length > 0) {
                imageUrl = "http://192.81.222.241:8080/issues/" + i.id + "/image"
            }

            ["type"    : i.issueType,
             "category": i.issueCategory,
             "comment" : i.comment,
             "position": pos,
             "imageUrl": imageUrl

            ]
        }

    }

}
