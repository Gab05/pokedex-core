#!/usr/bin/env bash

echo "Decrypting private key..."
openssl aes-256-cbc -K $encrypted_4bcfaff873d9_key -iv $encrypted_4bcfaff873d9_iv -in github_deploy_key.enc -out github_deploy_key -d
chmod 400 github_deploy_key

echo "Moving key to ssh folder..."
mv github_deploy_key ~/.ssh/

echo "Adding key to ssh configs..."
ssh-add ~/.ssh/github_deploy_key

echo "Done!"