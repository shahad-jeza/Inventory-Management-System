# Stock Management System

## Project Overview
This project is a simple stock management system implemented in Java. It includes:

- **Product Management**: Tracks stock levels and suggests restocking based on sales trends.
- **Order Processing**: Manages customer orders with automatic status updates using multithreading.
- **Multi-Threaded Background Order Processing**: Uses Java's ExecutorService to handle multiple orders concurrently.
- **Merge Conflict Demonstration**: Simulates and resolves merge conflicts in Git.

## Features
- **Product Class**: Stores product details such as ID, name, price, stock level, and reorder threshold.
- **Stock Predictor**: Estimates when stock will run out and suggests a restocking strategy.
- **Order Class**: Represents customer orders with unique IDs and statuses.
- **Order Manager**: Processes orders and updates their statuses in separate threads.
- **Multi-Threaded Background Order Processing**: 
  - Uses ExecutorService to process multiple orders concurrently.
  - Ensures proper synchronization for correct order status updates.
- **Merge Conflict Handling**: Demonstrates a Git merge conflict and its resolution.

## Setup Instructions
1. **Clone the Repository**
   ```bash
   git clone <repository_url>
   cd stock-management-system
   ```

2. **Compile and Run the Application**
   ```bash
   javac Main.java
   java Main
   ```



## Example Output
```
Enter average daily sales for Laptop: 4
Enter average daily sales for Smartphone: 2

Product ID: 123e4567-e89b-12d3-a456-426614174000
Name: Laptop
Price: $1200.0
Stock Level: 20
Reorder Threshold: 5
----------------------------
Product ID: 321a4567-e89b-12d3-a456-426614174111
Name: Smartphone
Price: $800.0
Stock Level: 10
Reorder Threshold: 3
----------------------------
Stock Prediction for Laptop:
Days until stock out: 5
Restock Suggestion: Moderate: Consider restocking soon.
Stock Prediction for Smartphone:
Days until stock out: 5
Restock Suggestion: Moderate: Consider restocking soon.
```

## License
This project is open-source and available for personal and educational use.

---

Now you can upload this `README.md` to your repository! 🚀

