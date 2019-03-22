import org.sonatype.nexus.blobstore.api.BlobStoreManager
import groovy.json.JsonSlurper

def params = new JsonSlurper().parseText(args)

def v1Enabled = (params?.viEnabled?:false) as boolean

def httpPort = (params?.httpPort) as Integer
def httpsPort = (params?.httpsPort) as Integer

def strictContentTypeValidation = params?.strictContentTypeValidation?:false as boolean

def resourceName = params?.name:?'docker-proxied'

if (httpPort == 'null' || httpsPort == 'null') {
    if ( !repository.repositoryManager.exists(resourceName) ){
        repository.createDockerProxy(resourceName,
                                params?.proxyUrl as String,
                                params?.indexType?:'REGISTRY' as String,
                                params?.indexUrl as String,
                                httpPort,
                                httpsPort,
                                BlobStoreManager.DEFAULT_BLOBSTORE_NAME,
                                strictContentTypeValidation,
                                v1Enabled)
    }
}