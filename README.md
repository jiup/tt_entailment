# Part I: Basic Model Checking

The first technique you must implement is the “truth-table enumeration method” described in AIMA Figure 7.10. The algorithm is conceptually straightforward you may not really even need their pseudo-code. We also discussed this in class, and it is presented in the lecture slides.

You will also need to represent a “model” (a.k.a. possible world, or assignment of truth values). And then you need to use that representation in the implementation of the algorithm. This is a very important design point. I suggest that you do something clear and correct but not necessarily efficient first, then improve it as time permits and your problems demand. If you use a language like Java, C++, or Python you may be particularly well served by the Set, Vector, or Hash-table/Dictionary data structures.

You must test your implementation on at least four of the problems described below.

# Part II: Advanced Propositional Inference

For the second part of this assignment, you must implement one of the other techniques for Propositional Inference described in class and in the textbook. Your choices are:

- The DPLL algorithm for checking satisfiability (AIMA Figure 7.17)
- The WalkSAT algorithm, also for SAT checking (AIMA Figure 7.18)
- A resolution-based theorem prover (AIMA Figure 7.12)
- A theorem prover based on other inference rules (if you do this, be sure to explain what you’re doing in detail in your writeup)

You can use the same classes for representing Propositional Logic sentences as you did for Part I.

Several of these techniques require the knowledge base to be converted to clauses (conjunctive normal form, CNF). You can write a program that converts arbitrary sentences to CNF. Or you can arrange for the problems that you solve to be expressed as clauses in the first place (which is probably much easier!)

As with Part I, you must test your implementation on at least four of the problems de- scribed below. Use the same problems and compare the techniques in your writeup. If you can’t use the same problems, explain why in your writeup.