package fixmycity.backend

import org.springframework.http.HttpStatus

class ImageController {

    def get() {

        Long id = params.long("id")


        def issue = Issue.get(id)

        if(issue?.issueImage) {
            byte[] bytes = issue.issueImage
            response.setContentType("image/png")
            response.setContentLength(bytes.length)
            response.setHeader("Content-Disposition", "attachment; filename=image.png")
            response.outputStream << bytes
        } else {
            render status: HttpStatus.NOT_FOUND.value()
            return
        }


    }
}
