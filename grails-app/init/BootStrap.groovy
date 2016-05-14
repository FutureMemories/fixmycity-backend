import fixmycity.backend.Issue
import fixmycity.backend.Location

class BootStrap {

    def init = { servletContext ->

        new Issue(issueType: "Problem",
                issueCategory: "Annat problem",
                comment: "Bänken är monterad på fel håll",
                position: new Location(latitude: 57.70350521, longitude: 11.97080612)
        ).save()

        new Issue(issueType: "Problem",
                issueCategory: "Nedskräpning",
                comment: "Nedskräpning i slottsskogen",
                position: new Location(latitude: 57.6875885, longitude: 11.94316864)
        ).save()


    }
    def destroy = {
    }
}
