# DNar Bot: Free Your Mind, Get Things Done

DNar helps you capture, organize, and manage your tasks so you can focus on what matters most. Stop using your brain as a to-do list and start getting things done!

**[Key Benefits]:**

* **[Simple and Intuitive]:** Easy to learn and use, with straightforward commands.
* **[Text-Based Interaction]:** No complex GUI to navigate – just type and go.
* **[Lightning Fast]:** Quick responses so you can add and manage tasks efficiently.
* **[Completely Free]:** No cost to download and use!

---

## **[Quick Start]**

### Ready to Experience the Freedom?

1. **[Download]:** Grab the latest release from [here](https://github.com/dnardnar/DNar).
2. **[Launch]:** Double-click the executable to start the application.
3. **[Add Tasks]:** Start adding your tasks using simple commands.
4. **[Relax]:** Let DNar handle the reminders so you can focus on your day.

It's that easy!

---

## **[Features]**

### Task Management Commands

- **Adding a Task:**  
  Format: `todo DESCRIPTION`  
  Example: `todo Buy groceries`

- **Adding a Deadline:**  
  Format: `deadline DESCRIPTION /by DATE`  
  Example: `deadline Submit assignment /by 2025-03-20`

- **Adding an Event:**  
  Format: `event DESCRIPTION /from DAY TIME /to TIME`  
  Example: `event project meeting /from Mon 2pm /to 4pm`

- **Listing All Tasks:**  
  Format: `list`  
  Example: `list`

- **Marking a Task as Done:**  
  Format: `mark INDEX`  
  Example: `mark 2`

- **Unmarking a Task:**  
  Format: `unmark INDEX`  
  Example: `unmark 2`

- **Editing a Task:**  
  Format: `edit INDEX description NEW DESCRIPTION`  
  Example: `edit 1 description Buy milk`

- **Finding Tasks by Keyword:**  
  Format: `find KEYWORD`  
  Example: `find groceries milk`

- **Deleting a Task:**  
  Format: `delete INDEX`  
  Example: `delete 3`

---

## **[Saving Data]**

DNar automatically saves your task data after every command that modifies it. The data is stored in a txt file in the application's directory.

---

## **[Upcoming Features]**

- [ ] **Reminders:** Receive timely notifications for deadlines and events (Coming Soon).

---

## **[Command Summary]**

| Action             | Format, Examples                                                                                  |
|--------------------|---------------------------------------------------------------------------------------------------| 
| Add Task (To-do)   | `todo DESCRIPTION`, e.g., `todo Buy milk`                                                         |
| Add Deadline       | `deadline DESCRIPTION /by DATE`, e.g., `deadline Submit report /by 2025-03-20`                    |
| Add Event          | `event DESCRIPTION /from DAY TIME /to TIME` , e.g., `event project meeting /from Mon 2pm /to 4pm` |
| List Tasks         | `list`, e.g., `list`                                                                              |
| Mark as Done       | `mark INDEX`, e.g., `mark 2`                                                                      |
| Unmark as Not Done | `unmark INDEX`, e.g., `unmark 2`                                                                  |
| Edit Task          | `edit INDEX description NEW DESCRIPTION`  , e.g., `edit 1 description Buy milk`                   |
| Find Tasks         | `find KEYWORD [MORE_KEYWORDS]`, e.g., `find groceries milk`                                       |
| Delete Task        | `delete INDEX`, e.g., `delete 3`                                                                  |

---

DNar is also a great project for Java developers to explore. Dive into the code and contribute!
```java
public class Main {
   public static void main(String[] args) {
      Application.launch(DNar.class, args);
   }
}
```

