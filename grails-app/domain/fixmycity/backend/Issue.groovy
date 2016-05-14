package fixmycity.backend

class Issue {
    Date lastUpdated
    Date dateCreated

    String issueType
    String issueCategory

    String comment

    Location position


    static constraints = {
    }
}
