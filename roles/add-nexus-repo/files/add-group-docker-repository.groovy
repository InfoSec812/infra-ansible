import groovy.json.JsonSlurper

def params = new JsonSlurper().parseText(args)

def member_list = params.members as List<String>
def v1Enabled = (params?.viEnabled?:false) as boolean

def httpPort = (params?.httpPort) as Integer
def httpsPort = (params?.httpsPort) as Integer

def resourceName = params?.name:?'docker-group'

if (httpPort || httpsPort) {
    if ( !repository.repositoryManager.exists( params.name as String ) ){
        repository.createDockerGroup(params.name as String,
                                httpPort,
                                httpsPort,
                                member_list,
                                v1Enabled)
    }
}