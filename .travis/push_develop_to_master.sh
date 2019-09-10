#!/usr/bin/env bash

echo "Setting up Travis user..."
git config user.email "travis@travis-ci.org"
git config user.name "travis-ci"
echo "Done setting up Travis user!"

echo "Cloning..."
git clone https://travis-ci:${GH_TOKEN}@github.com/Gab05/pokedex-core.git --branch=master
cd pokedex-core
git remote rm origin
git remote add origin https://travis-ci:${GH_TOKEN}@github.com/Gab05/pokedex-core.git
echo "Done cloning!"

echo "Fetching remote branches..."
git fetch
echo "Done fetching remote branches!"

echo "Merging develop into master..."
git merge origin/develop -m "Travis build: $TRAVIS_BUILD_NUMBER" --squash
echo "Done merging develop into master!"

echo "Pushing refs to remote..."
git remote add origin https://${GH_TOKEN}@github.com/Gab05/pokedex-core.git > /dev/null 2>&1
git push --set-upstream origin master
echo "Done!"

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