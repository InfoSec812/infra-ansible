# Overview

An Ansible role to start/stop an OpenShift port-forwarding configuration. This role
can be invoked before and after another role to provide access to services exposed
inside of the target Pod.

# Usage
1. Create an inventory
```
[local]
localhost ansible_connection=local
```
1. Create a playbook
```yaml
---
# This test assumes access to a running OpenShift cluster
- name: 'Wrap action with port forward'
  hosts: local
  vars:
    depoyment_name: nexus
  tasks:
  - name: Deploy Nexus
```
3. Ensure that you are logged in to an OpenShift cluster
```
```
4. Execute the playbook
```
```