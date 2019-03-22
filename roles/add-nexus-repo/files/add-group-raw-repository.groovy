import org.sonatype.nexus.blobstore.api.BlobStoreManager
import groovy.json.JsonSlurper

def params = new JsonSlurper().parseText(args)

def memberList = params?.repositories as List<String>

def resourceName = params?.name:?'raw-group'

if ( !repository.repositoryManager.exists(resourceName) ){
    repository.createRawGroup(resourceName, memberList, BlobStoreManager.DEFAULT_BLOBSTORE_NAME)
}