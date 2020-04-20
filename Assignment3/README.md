# Assignment 3

## Build instructions

```bash
$ cd src
$ make all
```

It should compile all the java source files that need to be re-built. The build has been tested with GNU compiler Linux (Fedora 26).

## Usage


The following was tested on Windows 10 WSL 2 (Ubuntu 18.04.3 LTS on Windows 10 x86_64).

 From the Command Prompt, type "java" with a class name of the program:

```bash
$ java CPSC319W20A3
```

When the program asks for the input file name path, input the path relative to ```src```.  An example of proper program execution (with the input file in the parent directory) is below:

```
$ cd src

$ make all
javac TraversalMethods.java
javac CPSC319W20A3.java

$ java CPSC319W20A3
Enter the input filename:
../example03.txt
Total words in ../example03.txt = 44
Number of unique words in ../example03.txt = 37
The word(s) which occur(s) most often and the number of times they occur(s) =
the = 9 times
Maximum height of the tree = 11
Enter the word you are looking for in ../example03.txt ?
the
Found! It appears 9 times in the input text file
Enter the word you are looking for in ../example03.txt ?
jungle
Found! It appears 3 times in the input text file
Enter the word you are looking for in ../example03.txt ?
test
Word not found!
Enter the word you are looking for in ../example03.txt ?
word not here
Word not found!
Enter the word you are looking for in ../example03.txt ?
quit
Word not found!
Enter the word you are looking for in ../example03.txt ?

Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER for ../example03.txt?
1
IN-ORDER output: all and are bat book brings byre call claw dawn for free good hear herds home hour hunting hut in is jungle keep kite law loosed mang night now of oh power pride rann sets shut song talon that the this till tush we
Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER for ../example03.txt?
2
PRE-ORDER output: the jungle book bat are and all brings home free byre for dawn claw call herds hear good in hut hour hunting is now kite keep night mang loosed law rann of pride power oh that sets shut talon song till this we tush
Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER for ../example03.txt?
3
POST-ORDER output: all and are bat call claw dawn for byre good hear herds free hunting hour hut is in home brings book keep law loosed mang night kite oh power pride of song talon shut sets that rann now jungle this tush we till the
Enter the BST traversal method (1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER for ../example03.txt?

$      


```


For more documentation, view the javadocs page at ```doc/CPSC319W20A3.html```after running
```
javadoc -d doc CPSC319W20A3.java
```
