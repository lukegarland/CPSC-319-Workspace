# Assignment 1

## Build instructions

```bash
cd src
make
```

It should compile all the java source files that need to be re-built. The build has been tested with GNU compiler Linux (Fedora 26).

## Usage

From the Command Prompt, type "java" with a class name of the program.

```bash
java FibonacciGenerator
```

If you want to test the output of each algorithm, in the ```main``` method of ```FibonacciGenerator.java```, run the static method ```testAlgorithms()```.

To print a table of the average running time of each algorithm after a number of function calls up to a certain value of n, run the method ```runExperimentalStudyTableFormat(int maxValue, int numberOfCalls)```.

For more documentation, view the javadocs page at ```doc/FibonacciGenerator.html```after running
```
javadoc -d doc FibonacciGenerator.java
```
