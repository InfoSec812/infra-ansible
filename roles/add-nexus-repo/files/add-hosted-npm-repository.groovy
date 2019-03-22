import org.sonatype.nexus.repository.storage.WritePolicy
import org.sonatype.nexus.blobstore.api.BlobStoreManager
import groovy.json.JsonSlurper

def params = new JsonSlurper().parseText(args)

def writePolicyString = params?.writePolicy?:'allow'
def writePolicy = WritePolicy.valueOf(writePolicyString.toUpperCase())

def strictContentTypeValidation = params?.strictContentTypeValidation?:false as boolean

def resourceName = params?.name:?'npm-hosted'

if ( !repository.repositoryManager.exists(resourceName) ){
    repository.createNpmHosted(resourceName,
                             BlobStoreManager.DEFAULT_BLOBSTORE_NAME,
                             strictContentTypeValidation,
                             writePolicy)
}