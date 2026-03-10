# SigmaBoy User Guide

SigmaBoy is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, SigmaBoy can help you manage your tasks faster than traditional GUI apps.

---

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `SigmaBoy.jar` from the releases page.
3. Copy the file to the folder you want to use as the home folder for SigmaBoy.
4. Open a command terminal and `cd` into the folder you put the jar file in.
5. Run the command `java -jar SigmaBoy.jar` to start the app.

---

## Features

### Adding a Todo Task: `todo`

Adds a simple task without any date or time constraints.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo Buy groceries
```

**Expected output:**
```
Got it. I've added this task:
 [T][ ] Buy groceries
Now you have 1 tasks in the list.
```

---

### Adding a Deadline Task: `deadline`

Adds a task with a specific due date.

**Format:** `deadline DESCRIPTION /by DATE`

**Example:**
```
deadline Submit assignment /by 10-02-2026
```

**Expected output:**
```
Got it. I've added this task:
 [D][ ] Submit assignment (by: 10-02-2026)
Now you have 2 tasks in the list.
```

---

### Adding an Event Task: `event`

Adds a task with a start time and end time.

**Format:** `event DESCRIPTION /from START /to END`

**Example:**
```
event Team meeting /from 10:00 /to 12:00
```

**Expected output:**
```
Got it. I've added this task:
 [E][ ] Team meeting (from: 10:00 to: 12:00)
Now you have 3 tasks in the list.
```

---

### Listing All Tasks: `list`

Displays all tasks with their index numbers and completion status.

**Format:** `list`

**Example:**
```
list
```

**Expected output:**
```
1. [T][ ] Buy groceries
2. [D][ ] Submit assignment (by: 10-02-2026)
3. [E][ ] Team meeting (from: 10:00 to: 12:00)
```

---

### Marking a Task as Done: `mark`

Marks a specific task as completed.

**Format:** `mark INDEX`

**Example:**
```
mark 1
```

**Expected output:**
```
Nice! I've marked this task as done:
 [T][X] Buy groceries
```

---

### Unmarking a Task: `unmark`

Marks a specific task as not completed.

**Format:** `unmark INDEX`

**Example:**
```
unmark 1
```

**Expected output:**
```
Ok! I've marked this task as not done yet:
 [T][ ] Buy groceries
```

---

### Deleting a Task: `delete`

Removes a task from the list permanently.

**Format:** `delete INDEX`

**Example:**
```
delete 2
```

**Expected output:**
```
Noted. I've removed this task:
 [D][ ] Submit assignment (by: 10-02-2026)
Now you have 2 tasks in the list.
```

---

### Finding Tasks: `find`

Searches for tasks containing a keyword. The search is case-insensitive.

**Format:** `find KEYWORD`

**Example:**
```
find meeting
```

**Expected output:**
```
Here are the matching tasks in your list:
1. [E][ ] Team meeting (from: 10:00 to: 12:00)
```

---

### Exiting the Program: `bye`

Exits the application.

**Format:** `bye`

**Example:**
```
bye
```

**Expected output:**
```
Bye. Hope to see you again soon!
```

---

### Saving the Data

SigmaBoy saves your tasks automatically after each command that modifies the list. Data is stored in `data/SigmaBoy.txt` in the same folder as the jar file. There is no need to manually save.

---

## Command Summary

| Action   | Format                                  | Example                              |
|----------|-----------------------------------------|--------------------------------------|
| Todo     | `todo DESCRIPTION`                      | `todo Buy groceries`                 |
| Deadline | `deadline DESCRIPTION /by DATE`         | `deadline Submit /by 10-02-2026`     |
| Event    | `event DESCRIPTION /from START /to END` | `event Meeting /from 10:00 /to 12:00`|
| List     | `list`                                  | `list`                               |
| Mark     | `mark INDEX`                            | `mark 1`                             |
| Unmark   | `unmark INDEX`                          | `unmark 1`                           |
| Delete   | `delete INDEX`                          | `delete 2`                           |
| Find     | `find KEYWORD`                          | `find meeting`                       |
| Exit     | `bye`                                   | `bye`                                |

---