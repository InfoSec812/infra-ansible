import org.sonatype.nexus.repository.maven.LayoutPolicy
import org.sonatype.nexus.repository.maven.VersionPolicy
import org.sonatype.nexus.blobstore.api.BlobStoreManager
import groovy.json.JsonSlurper

def params = new JsonSlurper().parseText(args)

def versionPolicyString = params?.versionPolicy?:'release'
def versionPolicy = VersionPolicy.valueOf(versionPolicyString.toUpperCase())

def layoutPolicyString = params?.layoutPolicy?:'strict'
def layoutPolicy = LayoutPolicy.valueOf(layoutPolicyString.toUpperCase())

def strictContentTypeValidation = params?.strictContentTypeValidation?:false as boolean

def resourceName = params?.name:?'maven-proxied'

if ( !repository.repositoryManager.exists(resourceName) ){
    repository.createMavenProxy(resourceName,
                              params?.proxyUrl as String,
                              BlobStoreManager.DEFAULT_BLOBSTORE_NAME,
                              strictContentTypeValidation,
                              versionPolicy,
                              layoutPolicy)
}