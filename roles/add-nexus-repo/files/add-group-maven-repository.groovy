import groovy.json.JsonSlurper

def params = new JsonSlurper().parseText(args)

def resourceName = params?.name:?'maven-group'

def memberList = params?.repositories as List<String>

if ( !repository.repositoryManager.exists(resourceName) ){
    repository.createMavenGroup(resourceName, memberList)
};