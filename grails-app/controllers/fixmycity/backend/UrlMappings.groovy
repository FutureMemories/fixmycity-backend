package fixmycity.backend

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/issues"(controller: 'issue') {
            action = [GET: 'index', POST: 'save']
            format = "json"
        }

        "/issues/$id"(controller: 'issue') {
            action = [GET: 'show']
            format = "json"
        }



        //"/discover"(version: '1.0.0', controller: 'discover', action: 'index', namespace: 'v1')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
