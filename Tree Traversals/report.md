# HW 4 Grading

## Score: 69 out of 80 points

## Feedback from code review

### contributions.txt
> Oh we thought it was out of 80 points so we distributed it 40-40 but if it’s suppose to be out of 100 then it would be 50-50.

### JavaDoc Comments: 9 out of 10 points
* Good, but some descriptions are too brief. 

### Code quality: 22 out of 30 points

#### iterative pre order traversal
* Good

#### iterative post order traversal
* Ok
* This can be done with a single stack
* **Incorrectly assumes tree has Integer objects**, thus not satisfying requirements. This is due to the cast of the data to an Integer and the second stack defined to store integers. The data is of type E, so the stack should be declared as `Stack<E>`
* Logic can be simplified by using just one stack.

#### level order traversal
* Does not work when tree is empty, error at line 229
* This can be done with a single queue
* Logic can be simplified by using just one queue.
* Unnecessary use of variable `counter`. It's never used, other than to declare it and add it as the first value in the queue

#### create tree from traversal(s)
* Good
* Use disjunction (the || operator) to combine first two if statements into one
* Logic can be improved

#### Complete set of unit tests
* Incomplete tests
* not tested on trees storing Strings (or anything other than Integer)
* not tested on empty trees

### Running evaluation unit tests: 38 out of 40 points
* Level order is correct, the format of the String is the problem

```
-------------------------------------------------------------------------------
Test set: cecs274.BinaryTreeEvaluation
-------------------------------------------------------------------------------
Tests run: 6, Failures: 2, Errors: 0, Skipped: 0, Time elapsed: 0.341 s <<< FAILURE! - in cecs274.BinaryTreeEvaluation
testTraversalEmptyTree  Time elapsed: 0.024 s  <<< FAILURE!
org.opentest4j.MultipleFailuresError: 
Multiple Failures (1 failure)
	java.lang.StringIndexOutOfBoundsException: begin 0, end -2, length 0
	at cecs274.BinaryTreeEvaluation.testTraversalEmptyTree(BinaryTreeEvaluation.java:31)

testLevelOrderRandomTree  Time elapsed: 0.073 s  <<< FAILURE!
org.opentest4j.MultipleFailuresError: 
Multiple Failures (3 failures)
	org.opentest4j.AssertionFailedError: expected: <290 (1), 463 (2), 132 (2), 405 (3), 458 (3), 92 (4), 170 (4), 399 (4)> but was: <290(1), 463(2), 132(2), 405(3), 458(3), 92(4), 170(4), 399(4)>
	org.opentest4j.AssertionFailedError: expected: <486 (1), 245 (2), 466 (2), 114 (3), 441 (3), 127 (3), 248 (3), 161 (4), 119 (4), 424 (4), 102 (4), 59 (4), 89 (4), 347 (4)> but was: <486(1), 245(2), 466(2), 114(3), 441(3), 127(3), 248(3), 161(4), 119(4), 424(4), 102(4), 59(4), 89(4), 347(4)>
	org.opentest4j.AssertionFailedError: expected: <455 (1), 182 (2), 44 (2), 200 (3), 217 (3), 71 (3), 375 (4)> but was: <455(1), 182(2), 44(2), 200(3), 217(3), 71(3), 375(4)>
	at cecs274.BinaryTreeEvaluation.testLevelOrderRandomTree(BinaryTreeEvaluation.java:79)

```
