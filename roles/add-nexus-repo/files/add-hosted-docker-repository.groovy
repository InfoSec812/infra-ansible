import org.sonatype.nexus.repository.storage.WritePolicy
import org.sonatype.nexus.blobstore.api.BlobStoreManager
import groovy.json.JsonSlurper

def params = new JsonSlurper().parseText(args)

def writePolicyString = params?.writePolicy?:'allow'
def writePolicy = WritePolicy.valueOf(writePolicyString.toUpperCase())

def strictContentTypeValidation = params?.strictContentTypeValidation?:false as boolean
def v1Enabled = params?.v1Enabled?:false as boolean

def httpPort = params?.httpPort?:null as Integer
def httpsPort = params?.httpsPort?:null as Integer

def resourceName = params?.name:?'docker-hosted'

if (!repository.repositoryManager.exists(resourceName) ){
    repository.createDockerHosted(resourceName,
                                httpPort,
                                httpsPort,
                                BlobStoreManager.DEFAULT_BLOBSTORE_NAME,
                                v1Enabled,
                                strictContentTypeValidation,
                                writePolicy)
}