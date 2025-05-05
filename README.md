# Capstone 1
DS Bank
A simple CLI banking app built in Java that allows users to make deposits, payments, and view transaction history. This project was built for a capstone at Year Up United to explore the backend processes behind user interactions.

Purpose
The purpose of this project was to gain real experience with backend development. I didn’t want to just write the code and make it work. I wanted to understand what each part was doing, how user input travels through the system, where data is stored, and how it’s later brought back and shown to the user. This wasn’t just about building something functional; it was about learning how everything connects and why it matters. I approached each step with the goal of building not only a working program, but also my understanding and confidence as a developer.

Key Features
Make deposits and payments

View all transactions

Filter to view only deposits or only payments

Generate simple reports (total deposits, payments, net balance)

Transactions are sorted from newest to oldest

Data stored in a CSV file and read into memory using ArrayList

Organized with encapsulation and object-oriented structure

How It Works (Under the Hood)
This was the part that mattered most to me:

Transactions are created by the user in the Main class through input prompts.

A new Transaction object is made and written to transactions.csv using file I/O (BufferedWriter).

The Ledger class reads from that file, parses each line, and returns a sorted list of Transaction objects.

The user can choose to view deposits, payments, or reports — and the app calls on Ledger methods to present that data.

This cycle — from user input to file writing, to reading and showing back to the user — was the biggest learning experience for me.

Technologies Used
Java 17

IntelliJ IDEA

Standard Java libraries (java.io, java.util)

Getting Started
To run DS Bank locally:

Clone or download this repository

Open it in IntelliJ IDEA

Make sure you're using Java 17

Run Main.java

Follow the prompts in the terminal

All transaction data is stored in transactions.csv under src/main/resources.

What I Learned
This project helped me grow as a beginner developer. I learned how to:

Prompt the user and validate input

Store and access data using ArrayList and HashMap

Use encapsulation to organize code in a clean, reusable way

Read and write to files

Sort transactions using a Comparator

Think about how programs feel to users while building them behind the scenes



