# Customer Service Ticket Management System - Java Project

**CENG112 - Data Structures Homework 2**  
**Izmir Institute of Technology (IZTECH) – Spring 2025**  

**Seyyid Öter, Emir Kağan Yiğit**

---

## 📌 PROJECT DESCRIPTION

This project is a basic simulation of a **Customer Service Ticket Management System**, developed in Java.

The system processes service requests by reading commands from a CSV file.  
Requests (tickets) can be created, resolved, and displayed in different formats.  
It uses custom **priority queue** and **history list** structures built from scratch using **Java generics**.

---

## 🧠 GOAL

Help customer support agents manage incoming tickets by:
- Sorting them based on priority
- Displaying them in custom order
- Resolving them one by one (highest priority first)
- Keeping track of resolved tickets

---

## 📁 PROJECT STRUCTURE

```
src/
├── command/         → Command classes & processor logic
│   ├── Command.java
│   └── CommandProcessor.java
├── model/           → Core data structures
│   ├── Ticket.java
│   ├── GenericPriorityQueue.java
│   ├── GenericHistory.java
│   └── TicketNameComparator.java
├── util/            → File reader utility
│   └── FileIO.java
└── Main.java        → Entry point

example_commands.csv → Input command file
```

---

## 🔑 MAIN CLASSES & ROLES

- **Main:** Starts the program and reads commands from file.
- **CommandProcessor:** Executes all operations (create, resolve, display, history).
- **Ticket:** Represents a support request with name, issue, priority and timestamp.
- **GenericPriorityQueue:** Custom queue for sorting tickets based on priority.
- **GenericHistory:** Custom list to store resolved tickets.
- **TicketNameComparator:** Compares tickets alphabetically by customer name.
- **FileIO:** Reads commands from a `.csv` file.

---

## 📜 SUPPORTED COMMANDS

- **new,name,issue,priority** → Add a new ticket  
- **resolve** → Resolve one ticket (highest priority)  
- **display,[asc|desc|priority]** → Show current (active) tickets  
- **history,[asc|desc]** → Show resolved tickets

---

## 🧮 SAMPLE TICKET

```text
new,John Doe,Internet not working,High
```

Creates a ticket with **High** priority for John Doe.

---

## 🧾 EXAMPLE OUTPUT

```text
Added: John Doe - Internet not working [High]
--- Active Tickets (PRIORITY) ---
1. John Doe - Internet not working [High]
Resolving Ticket...
Resolved: John Doe - Internet not working [High]
--- History (by name) ---
1. John Doe - Internet not working [High]
```

---

## 💬 NOTES

- No Java built-in queue or list structures (e.g., PriorityQueue, LinkedList) are used.
- All core structures were built using generic classes.
- The project follows object-oriented design principles.
- Outputs are fully aligned with the official assignment PDF.

---

## 👥 AUTHORS

Seyyid Öter
Emir Kağan Yiğit  
CENG112 – Spring 2025
