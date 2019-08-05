#!/usr/bin/env bash

echo "\nCloning git repo..."
cd ..

# The filename of the key is the one specified in the ./decrypt_private_key.sh script
ssh-agent bash -c 'ssh-add ~/.ssh/github_deploy_key; git clone --branch=master git@github.com:Gab05/pokedex-core.git Gab05/pokedex-core-copy'

cd Gab05/pokedex-core-copy

echo "Fetching remote branches..."
git config --replace-all remote.origin.fetch +refs/heads/*:refs/remotes/origin/*
git fetch

echo "Create tracking branches..."
# create the tacking branches
for branch in $(git branch -r|grep -v HEAD) ; do
    git checkout -qf ${branch#origin/}
done

echo "Checkout master branch and merge from origin/develop..."
git checkout master
git merge --squash origin develop

echo "Push to origin/master..."
git push origin master

cd ..
echo "Done! develop merged into master."