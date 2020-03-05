# Assignment 2

## Build instructions

```bash
cd src
make
```

It should compile all the java source files that need to be re-built. The build has been tested with GNU compiler Linux (Fedora 26).

## Usage

From the Command Prompt, type "java" with a class name of the program.

```bash
java CPSC319W20A2
```


If you would like to redirect the System input stream (```System.in```) to a file, run:

```bash
java CPSC319W20A2 < path_to_input_file.txt
```


If you would like to redirect the System input stream (```System.in```) to a file, and the System output stream(```System.out```) to a file, run:

```bash
java CPSC319W20A2 < path_to_input_file.txt > path_to_output_file.txt
```

For more documentation, view the javadocs page at ```doc/CPSC319W20A2.html```after running
```
javadoc -d doc CPSC319W20A2.java
```
