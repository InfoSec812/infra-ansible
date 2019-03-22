import org.sonatype.nexus.repository.maven.LayoutPolicy
import org.sonatype.nexus.repository.maven.VersionPolicy
import org.sonatype.nexus.repository.storage.WritePolicy
import org.sonatype.nexus.blobstore.api.BlobStoreManager
import groovy.json.JsonSlurper

def params = new JsonSlurper().parseText(args)

def writePolicyString = params?.writePolicy?:'allow'
def writePolicy = WritePolicy.valueOf(writePolicyString.toUpperCase())

def versionPolicyString = params?.versionPolicy?:'release'
def versionPolicy = VersionPolicy.valueOf(versionPolicyString.toUpperCase())

def layoutPolicyString = params?.layoutPolicy?:'strict'
def layoutPolicy = LayoutPolicy.valueOf(layoutPolicyString.toUpperCase())

def strictContentTypeValidation = params?.strictContentTypeValidation?:false as boolean

def resourceName = params?.name:?'maven-hosted'

if ( !repository.repositoryManager.exists(resourceName) ){
    repository.createMavenHosted(resourceName,
                                 BlobStoreManager.DEFAULT_BLOBSTORE_NAME, 
                                 strictContentTypeValidation, 
                                 versionPolicy,
                                 writePolicy,
                                 layoutPolicy)
}