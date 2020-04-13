# Assignment 4

## Build instructions

```bash
$ cd src
$ make all
```

It should compile all the java source files that need to be re-built. The build has been tested with GNU compiler Linux (Fedora 26).

## Usage


The following was tested on Windows 10 WSL 2 (Ubuntu 18.04.3 LTS on Windows 10 x86_64).

 From the Command Prompt (assuming the steps from above are complete), type "java" with the class name of the program, and the path to the input file (relative to ```src```) as the only command line argument:

```bash
$ java CPSC319W20A4 path_to_input_file.txt
```

An example of proper program execution (with the input file in the parent directory) is below:

```
$ cd src

$ make all
javac Graph.java
javac CPSC319W20A4.java

$ java CPSC319W20A4 ../7x7_data.txt
Reading file from ../7x7_data.txt
Writing graph to: ../7x7_GRAPH.txt
Writing DFT to: ../7x7_GRAPH.txt
Writing MST to: ../7x7_GRAPH.txt

$

```

To check that the output files are correct, the following commands can be executed to show the output files are correct. (Answer files shown are from http://pages.cpsc.ucalgary.ca/~hudsonj/CPSC319W20/A4/).

```
$ diff ../7x7_GRAPH.txt ../7x7_GRAPHans.txt -s && diff ../7x7_DFT.txt ../7x7_DFTans.txt -s && diff ../7x7_MST.txt ../7x7_MSTans.txt -s

Files ../7x7_GRAPH.txt and ../7x7_GRAPHans.txt are identical
Files ../7x7_DFT.txt and ../7x7_DFTans.txt are identical
Files ../7x7_MST.txt and ../7x7_MSTans.txt are identical

$
```

For more documentation, view the javadocs page at ```doc/CPSC319W20A3.html```after running
```
javadoc -d doc CPSC319W20A4.java
```
