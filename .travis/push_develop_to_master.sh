#!/usr/bin/env bash

git fetch origin
git checkout master
git merge origin develop
git push origin master