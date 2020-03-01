# Lab 6: Building parse tree

In this lab, we proceed further to experiment with the parse tree construction using ANTLR.
In the previous lab, you have worked on recognizing token sequences using a grammar.  Going further, in this lab, not only do we want to recognize the validity of the input program, we also want to construct a specific parse tree.

## The syntax

The programs will be just a single arithmetic expression that can involve algebraic operators, trignometric functions, and a vardic `sum(...)` aggregation.  Here some examples and their expected parse trees.

### Basic algebraic operators

```
1 + 2 * 3
```

### Trig functions

We suppose `sin` and `cos` functions.  So, the following are valid programs:

```
sin(3.1415)
```

```
sin(10)*sin(10) + cos(10)*cos(10)
```

### Sum aggregation

The `sum` function can take arbitrary number of expressions as arguments.  So, the following are valid programs:

```
sum(1,2,3,4,5)
```

and

```
sin(
  3.1415 * sum(
            cos(1),
            cos(2),
            cos(3)))
```


## Parse trees

Consider the program: `1 + 2 * 3`.

The parse tree should look like:

```
    expr
  /   |   \
expr  +  expr
  |     /  |  \
  1   expr * expr
       |      |
       2      3
```

We choose to encode the tree as a string using a simple Lisp-style S-expression encoding.  For each intermediate node

```
    x
  / | \
 y1 y2 y3
```

we represent it with parenthesis: `(x y1 y2 y3)`.  So, the first element in `(...)` is the symbol of the parent node,
and subsequent elements are the encodings of its children.  Thus, the parse tree of `1 + 2 * 3` is represented as:

```
(expr (expr 1) + (expr (expr 2) * (expr 3)))
```

The `Check.java` program will generate parse tree using the S-expression encodings of parse trees for you.

## Your task

Author the ANTLR grammar file `Expr.g4` with grammar rules and lexical rules such that your grammar generates the desired parse trees for the input programs.

A makefile is provided to help you:

- `make g4 java` will compile the grammar file and generate the Java classes.
- `make check` will run the resulting parser and check if the parse trees are as expected.

