[![Build Status](https://travis-ci.org/epalrov/java-collections.svg?branch=master)](https://travis-ci.org/epalrov/java-collections)
[![Code Coverage](https://codecov.io/gh/epalrov/java-collections/branch/master/graph/badge.svg)](https://codecov.io/gh/epalrov/java-collections)

# java-collections

java-collections represents a unified library for storing and manipulating groups of object. This implementation offer a variety of representations, including:
 - arrays (ArrayList) - have the properties of random-access memory: very fast for accessing elements by position and for iterating over them, but slower for inserting and removing elements at arbitrary positions because require adjusting the position of other elements.
 - linked lists (LinkedList) - accessing elements by position is slow, because you have to follow the reference chain from the start of the list, but insertion and removal operations can be performed in constant time by rearranging the cell references.
 - hash tables (HashMap) - provide a way of storing elements indexed on their content rather than on an integer-valued index, as with lists. In contrast to arrays and linked lists, hash tables provide no support for accessing elements by position, but access by content is usally very fast, as are insertion and removal.
 - trees (TreeSet) - organize their elements by content, but with the important difference that they can store and retrieve them in sorted order. They are relatively fast for the operations of inserting and removing elements, accessing them by content and iterating over them.

A large variety of methods are implemented, all compliant to the interface of the standard Java Collection Framework (java.util):
 - java.util.Set
 - java.util.Map
 - java.util.List
 - java.util.Collection

Finally it would be a good way to learn about Java Generics :-)

## Building and Testing

To build, run `mvn install`.

To test, run `mvn test`.

## Contact

paolorovelli@yahoo.it

