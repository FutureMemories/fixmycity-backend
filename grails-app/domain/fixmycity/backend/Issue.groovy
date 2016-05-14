package fixmycity.backend

class Issue {
    Date lastUpdated
    Date dateCreated

    String issueType
    String issueCategory

    String comment

    Float longitude
    Float latitude

    byte[] issueImage

    static constraints = {
        issueImage(nullable: true, maxSize: Integer.MAX_VALUE /* 16K */)
        comment(nullable: true)

    }

    def toJson() {
        return "{lat : ${latitude}, lng : ${longitude}, comment : '${comment}', type : '${issueType}', category : '${issueCategory}', imageUrl : 'http://192.81.222.241/issues/${id}/image'}"
    }
}
