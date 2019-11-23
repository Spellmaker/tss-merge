# tss-merge

This project contains the solution for a recruiting exercise for Daimler TSS.

## Task

Implement a function **merge** which, given a list of intervals [a1, b1], [a2, b2]...
produces a list of intervals, where all overlapping intervals are merged and non-overlapping ones remain untouched.

_Example_:

Input: [25, 30], [2, 19], [14, 23], [4, 8]

Output: [2, 23], [25, 30]

## Assumptions 

The following assumptions were deduced from the task description:

1) The order of the intervals in the output need not to relate to the order of the intervals of the input
2) Intervals are inclusive - the interval [2, 5] includes 2 and 5
3) Interval bounds are natural numbers
4) For each provided interval [a1, b1], a1 <= b2 (which can also be achieved trivially in O(n))

## Theoretical considerations

Given two intervals [a1, b1] and [a2, b2], the following situations are possible (excluding symmetrical cases)

| # | graphical representation | properties |
|----|----------------------|------------|
| 1. | a1-----b1&nbsp;&nbsp;&nbsp; a2----b2 | a1 < a2, a1 < b2, b1 < a2, b1 < b2 |
| 2. | a1---- a2---- b1 ----b2 | a1 < a2, a1 < b2, b1 > a2, b1 < b2 |
| 3. | a1---- a2----b2 ----b1 | a1 < a2, a1 < b2, b1 > a2, b1 > b2 |

The following two statements hold (omitting proofs):
1) Given two intervals [a1, b1], [a2, b2] such that a1 <= a2. Than both intervals overlap if and only if b1 >= a2.
2) Given three intervals [a1, b1], [a2, b2], [a3, b3] such that a1 <= a2, a2 <= a3, it holds,
that if [a1, b1] overlaps [a3, b3], [a1, b2] also overlaps [a2, b2]

## Pseudocode

The previous studies yield a first algorithm

### Non-memory bounded algorithm

Input: List _L_ of tuples [_a1_, _b1_], ...

1) Sort _L_ with any optimal sorting procedure, considering only the lower bound of all intervals
2) Initialize _Current_ to the first entry
3) Initialize _Result_ as an empty list of intervals
3) For all entries _E_ in the list
    4) Let [_a1_, _b1_] = _Current_, [_a2_, _b2_] = _E_ (For notational purposes)
    5) If _b1_ >= _b2_, let _Current_ be the merged entry [_a1_, _b2_] 
    6) Otherwise, let _Current_ be [_a2_, _b2_], add _Current_ to _Result_
7) Add _Current_ to _Result_ and return _Result_
    
Runtime-wise, sorting using an optimal sort algorithm yields a complexity of O(n log n),
the actual interval merging processes each entry exactly once, leaving the overall complexity at
O(n log n).
For the sake of a simple algorithm, possible approaches of merging intervals directly while sorting remain unconsidered.
Mostly, since the performance gains would be subtle enough to require a very specific use-case.

Memory wise, the actual merging procedure requires almost no additional memory (at most allocation of counter variables
and space for one additional interval) and depends on the selected sort algorithm.

### Memory bounded algorithm

Assuming each single interval index fits into at least a long variable (with a maximum value of 2^63-1), intervals take
up constant space in memory (Larger numbers could e.g. be fit into BigIntegers and would require a whole different consideration,
but it seems safe to assume that the number of elements is the much bigger memory concern here).

To deal with limited amount of memory, we can again consider the sorting step and the actual interval merging separately.
For sorting, any kind of optimized external sorting procedure can be used.
The main interval merging loop can also very simply be modified to operate on separate chunks of the sorted input interval.
Only considering memory, this could be done as follows:

1) Write the sorted input to disk
2) Always keep the current interval _Current_ in memory
3) Load chunks of suitable size into the memory
4) After a number of entries have been added to _Result_, write out the current _Result_ buffer and reset it

If parallelization is also of interest, separate chunks of the input can be merged in parallel and a final task processes
the single result sets.