#!/usr/bin/env bash

cd ..
git clone --branch=master https://github.com/Gab05/pokedex-core.git Gab05/pokedex-core-copy
cd Gab05/pokedex-core-copy

git config --replace-all remote.origin.fetch +refs/heads/*:refs/remotes/origin/*
git fetch

# create the tacking branches
for branch in $(git branch -r|grep -v HEAD) ; do
    git checkout -qf ${branch#origin/}
done

git checkout master
git merge --squash origin develop
git push origin master
cd ..