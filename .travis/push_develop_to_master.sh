#!/usr/bin/env bash

git fetch
git checkout master
git merge origin develop
git push origin master