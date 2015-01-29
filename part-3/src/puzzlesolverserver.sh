#!/bin/bash

make
cd server && java PuzzleSolverServer $1
