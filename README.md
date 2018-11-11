## AI Project 2: Automated Reasoning

For more detailed documentation, please see [readme.pdf](README.pdf)

#### Requirements

JDK 1.8+



#### Structure of directory

```shell
tt_entailment
├── README.md *
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   ├── ModelChecking.java [Part I]
    │   │   ├── Resolution.java    [Part II]
    │   │   ├── SampleTest.java
    │   │   ├── KnowledgeBases.java
    │   │   ├── domain
    │   │   │   ├── Sentence.java
    │   │   │   └── propositional
    │   │   │       ├── AtomicSentence.java
    │   │   │       ├── ComplexSentence.java
    │   │   │       ├── Connective.java
    │   │   │       └── Sentence.java
    │   │   ├── knowledgebase
    │   │   │   ├── KnowledgeBase.java
    │   │   │   ├── PLAlgorithms.java
    │   │   │   └── PLKnowledgeBase.java
    │   │   └── util
    │   │       └── propositional
    │   │           └── SentenceUtil.java
    │   └── resources
    └── test
        └── java
            ├── CNFConversionTest.java
            ├── ModelCheckingTest.java
            ├── RecursiveModelCheckingTest.java
            └── ResolutionTest.java 
```



#### Compile & Run

- **Compile:**

  1. `cd /path/to/tt_entailment`
  2. `javac -d out/ $(find ./src/main/ -name '*.java')`




- **Run (we did sample 5 & 6 for *bonus*):**


  1. ***Part I: Basic Model Checking***

     - Move to directory:					`$ cd ./out`

     - Sample1 Modus Ponens: 				`$ java ModelChecking 1`

     - Sample2 Wumpus World (Simple):		`$ java ModelChecking 2`

     - Sample3a Mythical unicorn:			`$ java ModelChecking 3a`

     - Sample3b Magical unicorn: 			`$ java ModelChecking 3b`

     - Sample3c Horned unicorn:			`$ java ModelChecking 3c`

     - Sample4a OSSMB 82-12:				`$ java ModelChecking 4a`

     - Sample4b OSSMB 83-11:				`$ java ModelChecking 4b`

     - Sample5 More Liars and Truth-tellers:  `$ java ModelChecking 5`

     - Sample6a Smullyan's problem:			`$ java ModelChecking 6a`

     - Sample6b Liu's problem: 				`$ java ModelChecking 6b`


  2. ***Part II: Advanced Propositional Inference***

     - Sample1 Modus Ponens:				`$ java Resolution 1`

     - Sample2 Wumpus World (Simple):		`$ java Resolution 2`

     - Sample3a Mythical unicorn:			`$ java Resolution 3a`

     - Sample3b Magical unicorn:			`$ java Resolution 3b`

     - Sample3c Horned unicorn:			`$ java Resolution 3c`

     - Sample4a OSSMB 82-12:				`$ java Resolution 4a`

     - Sample4b OSSMB 83-11:				`$ java Resolution 4b`

     - Sample5 More Liars and Truth-tellers:	`$ java Resolution 5`

     - Sample6a Smullyan's problem:			`$ java Resolution 6a`

     - Sample6b Liu's problem:				`$ java Resolution 6b`

