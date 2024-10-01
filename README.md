# Milagro: A Financial app to become millionaire

This project is a tool designed to help users manage their finances by tracking expenses, income, budgets, and savings goals.

### **Project Overview:**

The App will allow users to:

- Track income and expenses by categories (e.g., food, rent, entertainment).
- Set monthly budgets and get alerts when they are close to exceeding them.
- Visualize spending trends over time using charts and reports.
- Manage savings goals (e.g., saving for a vacation or a new car).
- Generate financial reports (e.g., monthly summaries, top expense categories).

```mermaid
erDiagram
    User {
        int id PK
        string name
        string email
        string password
        boolean active
    }
    
    Entry {
        int id PK
        string type  "INCOME or OUTCOME"
        float value
        int categoryId FK
    }
    
    Category {
        int id PK
        string name
    }

    User ||--o{ Entry: "has many"
    Category ||--o{ Entry: "used in"
```
