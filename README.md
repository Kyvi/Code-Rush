🔐 5-Digit Code & Hint Generator (Code Rush Engine)

This project is a Java-based puzzle generator used in the mobile application Code-Rush: Battle of Brains.
It generates random 5-digit secret codes along with structured logical hints that allow players to deduce the correct answer.
The output is exported as JSON files grouped by difficulty level and language (English / French).

🧠 How It Works

The engine simulates a constraint-solving process:
It initializes the full search space of 100,000 possible 5-digit codes (00000 → 99999).
While more than one valid code remains:
It selects a candidate code from the remaining possibilities.
It applies a randomly generated hint (position, parity, sum, count conditions, etc.).
It filters the list of remaining possible codes according to the generated constraint.
The process continues until a unique solution remains.

This guarantees:
Logical consistency
A unique correct answer
Progressive difficulty scaling

The core generation logic is implemented in:
HintGenerator.java

All hint types are implemented in the:
hint/ package

📦 Example Generated Output
{
  "name": "Level 1",
  "hints": [
    {
      "hintText": "- The digit in 2nd position is 3.",
      "hintStructures": [
        {
          "hintType": "NUMBER_HINT",
          "concernedPositions": [1],
          "result": 3
        }
      ]
    },
    {...}
  ],
  "correctAnswer": "43642"
}


Each hint contains:

A human-readable text version
A structured representation used by the mobile app
A deterministic filtering rule

🏗 Architecture Overview

Language: Java
Pattern: Constraint filtering engine
Search strategy: Iterative elimination of invalid candidates
Output format: JSON (grouped by difficulty & locale)

The engine separates:

Hint generation logic
Hint constraint evaluation

Candidate filtering

Export formatting

🚀 How to Run

Open the project in your Java IDE.

Run:
main.java
