### Jolie Checker Toolchain (JCT): A Toolchain for Checking Domain- and Model-driven Properties of Microservices

This repository comprises the source code of a code generator that transforms [JCT](https://github.com/pwizenty/JCT).

There are basically two possibilities to invoke Jolie checker.

### Docker-Based Invocation
1. Clone the repository, `cd` into the cloned folder and run the `docker-build.sh` Bash script. The script will build the JCT checker container image `jct:latest`.
2. Run the `docker-run-D3PC-sample.sh` or `docker-run-LEMMA4Jolie-sample.sh` Bash scripts to start a container based on the `jct:latest` image and invoke the JCT on the `example-1.ol` or `example-3.ol` Jolie files in the cloned repository folder. 
3. The terminal output shows the results for the analyzed Jolie source code.


