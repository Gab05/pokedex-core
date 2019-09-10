#!/usr/bin/env bash

git config --global user.email "travis@travis-ci.org"
git config --global user.name "Travis CI"

git fetch

git checkout master

git merge develop -m "Travis build: $TRAVIS_BUILD_NUMBER" --squash

git remote add origin https://${GH_TOKEN}@github.com/Gab05/pokedex-core.git > /dev/null 2>&1
git push --set-upstream origin master

#
## The filename of the key is the one specified in the ./decrypt_private_key.sh script
#git clone --branch=master git@github.com:Gab05/pokedex-core.git Gab05/pokedex-core-copy
#
#cd Gab05/pokedex-core-copy
#
#echo "Fetching remote branches..."
#git fetch
#
#echo "Create tracking branches..."
## create the tacking branches
#for branch in $(git branch -r|grep -v HEAD) ; do
#    git checkout -qf ${branch#origin/}
#done
#
#echo "Checkout master branch and merge from origin/develop..."
#git checkout master
#git merge --squash origin/develop
#
#echo "Push to origin/master..."
#git push origin master