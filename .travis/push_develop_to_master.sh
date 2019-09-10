#!/usr/bin/env bash

echo "INFO - setting up Travis user..."
git config user.email "travis@travis-ci.org"
git config user.name "travis-ci"
echo "INFO - done setting up Travis user!"

echo "INFO - cloning repo..."
git clone https://travis-ci:${GH_TOKEN}@github.com/Gab05/pokedex-core.git --branch=master
cd pokedex-core
git remote rm origin
git remote add origin https://travis-ci:${GH_TOKEN}@github.com/Gab05/pokedex-core.git
echo "INFO - done cloning repo!"

echo "INFO - fetching remote branches..."
git fetch
echo "INFO - done fetching remote branches!"

echo "INFO - merging develop into master..."
git merge origin/develop --squash
git commit -m "Travis build: $TRAVIS_BUILD_NUMBER"
echo "INFO - done merging develop into master!"

echo "INFO - pushing refs to remote..."
git remote add origin https://${GH_TOKEN}@github.com/Gab05/pokedex-core.git > /dev/null 2>&1
git push --set-upstream origin master
echo "Done! Merged develop into master."

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