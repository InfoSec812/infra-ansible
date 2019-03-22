import org.sonatype.nexus.blobstore.api.BlobStoreManager
import groovy.json.JsonSlurper

def params = new JsonSlurper().parseText(args)

def strictContentTypeValidation = params?.strictContentTypeValidation?:false as boolean

def resourceName = params?.name:?'raw-proxied'

if ( !repository.repositoryManager.exists(resourceName) ){
    repository.createRawProxy(resourceName,
                              params?.proxyUrl as String,
                              BlobStoreManager.DEFAULT_BLOBSTORE_NAME,
                              strictContentTypeValidation)
}