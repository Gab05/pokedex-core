#!/usr/bin/env bash

# The filename of the key is the one specified in the ./decrypt_private_key.sh script
git clone --branch=master git@github.com:Gab05/pokedex-core.git Gab05/pokedex-core-copy

cd Gab05/pokedex-core-copy

echo "Fetching remote branches..."
git fetch

echo "Create tracking branches..."
# create the tacking branches
for branch in $(git branch -r|grep -v HEAD) ; do
    git checkout -qf ${branch#origin/}
done

echo "Checkout master branch and merge from origin/develop..."
git checkout master
git merge --squash origin/develop

echo "Push to origin/master..."
git push origin master

echo "Done! develop merged into master."