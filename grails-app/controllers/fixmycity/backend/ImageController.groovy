package fixmycity.backend

import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus

class ImageController {

    def assetResourceLocator

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

    def generate() {


        def num = params.int("num", 50)

        log.error("Generating $num issues.")





        Issue.list().each {
            it.delete()
        }

        final def images = ["sample/bench.jpg", "sample/bikelane.jpg", "sample/slottskogen.jpg", "sample/wrongturn.jpg", "sample/elskap.jpg"]
        final def types = ["Problem", "Fråga", "Beröm"]

        def comments = [
                "Promenerade med ett par goda vänner från Stockholm och fick se det här. Inte vart jag stolt över min kära stad när denna scen uppenbarades för sällskapet. Åtgärda!",
                "Vad jag kan minnas har det sett ut så här sen svärfar for till Amerika. Vad säger ni, skall vi kavla upp ärmarna och reda upp detta?",
                "Fix. It. Fix. IT. FIX. IT. FIX IT! FIX IT FIX IT!\n\n /Otålig.",
                "Vad jag kan se är det enda som behövs är ett kniptång, svets, kornsalt och en rotelsvarv. I övrigt ser jag inga problem. Lösa?",
                "Vi diskuterade detta i samfälligheten och vi var väl överens om att det är hög tid att åtgärda.",
                "Denna höll på att ta livet av vår huskatt Snussa. Jag tycker det är dags att laga.",
                "Jag hållt på detta ett tag, men jag tror att det är jag som är orsaken till detta. Det minsta jag kunde göra var väl att rapportera det här. "
        ]

        final def latMin = 57.710728d
        final def latMax = 57.689355d
        final def lonMin = 11.943296d
        final def lonMax = 12.003549d

        def random = new Random();

        num.times {

            double randomLat = latMin + (latMax - latMin) * random.nextDouble();
            double randomLon = lonMin + (lonMax - lonMin) * random.nextDouble();

            log.error("Random lat: $randomLat, random lon: $randomLon")

            def image = images[random.nextInt(images.size())]
            def type = types[random.nextInt(types.size())]
            def comment = comments[random.nextInt(comments.size())]


            log.error("Using image: $image, type: $type")

            def resource = this.class.classLoader.getResource('sample/bench.jpg')
            def file = new File(resource.file)

            new Issue(issueType: type,
                    issueCategory: "Annat problem",
                    comment: comment,
                    latitude: randomLat,
                    longitude: randomLon,
                    issueImage: file.bytes

            ).save()
        }

        render "Generated $num issues."

    }
}
