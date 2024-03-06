### Jolie Checker Toolchain (JCT): A Toolchain for Checking Domain- and Model-driven Properties of Microservices

This repository comprises the source code of a code generator that transforms [JCT](https://github.com/pwizenty/JCT).

There are basically two possibilities to invoke Jolie checker.

### Docker-Based Invocation
1. Clone the repository, `cd` into the cloned folder and run the `docker-build.sh` Bash script. The script will build the JCT checker container image `jct:latest`.
2. In order to execute the commands in our paper, we prepared the `docker-run-D3PC-sample.sh` or `docker-run-LEMMA4Jolie-sample.sh` run scripts to execute the JCT tool with the corresponding plugin.
3. The scripts ease the execution of our tool by maping the Jolie programming into a docker container and start a container based on the `jct:latest` image. The invoked JCT tool uses the `example-1.ol` and`example-3.ol` Jolie programs from the paper, that are contained in this repository.
4. The terminal output shows the results for the analyzed Jolie source code.


