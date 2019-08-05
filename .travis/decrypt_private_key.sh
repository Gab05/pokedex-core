#!/usr/bin/env bash

openssl aes-256-cbc -K $encrypted_4bcfaff873d9_key -iv $encrypted_4bcfaff873d9_iv -in github_deploy_key.enc -out github_deploy_key -d
ls