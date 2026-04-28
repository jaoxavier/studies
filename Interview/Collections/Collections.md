````md
# Java Collections Framework — Interview Cheat Sheet

---

## 🔹 1. Core Concepts

### Why is `Map` not part of `Collection`?
- `Map` does not represent a collection of single elements.
- It represents **key → value associations**.

---

### Difference between `List` and `Set`

| Feature        | List                  | Set                         |
|----------------|----------------------|------------------------------|
| Order          | Preserved            | Depends on implementation    |
| Duplicates     | Allowed              | Not allowed                  |
| Index access   | Yes                  | No                           |

---

### When to use what?

| Requirement                          | Structure     |
|-------------------------------------|--------------|
| Ordered + indexed                   | List         |
| No duplicates                       | Set          |
| Key-value access                    | Map          |
| FIFO processing                     | Queue        |
| Always smallest/highest priority    | PriorityQueue|

---

## 🔹 2. Queue Methods

### `add()` vs `offer()`

| Method | Behavior |
|--------|--------|
| add()  | Throws exception if fails |
| offer()| Returns false if fails |

---

## 🔹 3. HashMap Internals

### How `put()` works

1. Compute `hashCode()`
2. Apply hash function (spread)
3. Calculate index (bucket)
4. If empty → insert
5. If collision:
   - Traverse list/tree
   - Compare using `equals`
   - Replace if key exists

---

### What is a Bucket?

- Internal array position in `HashMap`
- Stores elements that share the same index
- Can contain:
  - Single node
  - Linked list
  - Tree (Java 8+)

---

### Collision Handling

- Before Java 8 → Linked List
- After Java 8 → Tree (if > 8 elements)

---

### Load Factor

- Defines resize threshold
- Default: **0.75**

---

### Rehash (Resize)

- Trigger: `size > capacity × loadFactor`
- Action:
  - Create bigger array
  - Redistribute elements

---

## 🔹 4. HashSet Internals

- Backed by `HashMap`
- Stores elements as keys
- Uses a dummy value internally

---

## 🔹 5. TreeMap / TreeSet

- Based on **Red-Black Tree**
- Always sorted
- Complexity: **O(log n)**

---

## 🔹 6. PriorityQueue

- Based on **Heap**
- Smallest (or highest priority) always on top

| Operation | Complexity |
|----------|-----------|
| add      | O(log n)  |
| poll     | O(log n)  |
| peek     | O(1)      |

---

## 🔹 7. ArrayList vs LinkedList

| Feature        | ArrayList       | LinkedList       |
|---------------|----------------|------------------|
| Structure     | Array          | Doubly Linked    |
| Access        | O(1)           | O(n)             |
| Insert middle | O(n)           | O(n)             |
| Memory        | Low            | High             |

⚠️ LinkedList is rarely better in real-world scenarios.

---

## 🔹 8. Equality & Hashing

### Rule:
- If `equals()` is true → `hashCode()` must be the same

---

### Dangerous Scenario

```java
Set<Cliente> set = new HashSet<>();

Cliente c = new Cliente(1L, "John");
set.add(c);

c.setId(2L);

set.contains(c); // false
````

### Why?

* Object hash changed
* It is now in the wrong bucket

---

### Rule:

❌ Never use mutable fields in `hashCode()` / `equals()`

---

## 🔹 9. merge vs putIfAbsent vs computeIfAbsent

### `merge`

```java
map.merge("A", 1, Integer::sum);
```

* If absent → insert
* If present → apply function

---

### `putIfAbsent`

* Only inserts if key is missing
* Does NOT compute value

---

### `computeIfAbsent`

```java
map.computeIfAbsent("A", k -> expensiveCall());
```

* Computes value only if absent

---

## 🔹 10. Concurrency

### Why `HashMap` is NOT thread-safe?

* Multiple threads can:

  * overwrite data
  * corrupt structure
  * cause infinite loops (rehash)

---

### What to use instead?

* `ConcurrentHashMap`

✔️ Allows concurrent access with better performance than synchronized maps

---

## 🔹 11. Fail-Fast vs Fail-Safe

### Fail-Fast

* Throws `ConcurrentModificationException`
* Example:

  * ArrayList
  * HashMap

---

### Fail-Safe

* Works on a copy
* No exception

Example:

* CopyOnWriteArrayList

---

## 🔹 12. Performance Impact of Bad hashCode

* Causes many collisions
* Degrades performance:

```text
O(1) → O(n) or O(log n)
```

---

## 🔹 13. Golden Rules (Interview Mindset)

* Always program to interfaces:

```java
List<String> list = new ArrayList<>();
```

* Choose based on:

  * Order?
  * Uniqueness?
  * Access pattern?
  * Performance?

---

## 🔹 14. Quick Summary

| Structure     | Best For                |
| ------------- | ----------------------- |
| ArrayList     | Fast read               |
| HashSet       | Unique elements         |
| HashMap       | Fast key-value access   |
| TreeMap       | Sorted keys             |
| PriorityQueue | Priority processing     |
| ArrayDeque    | Queue/Stack performance |

---

## 🔹 Final Tip

👉 Always think:

**"What data structure is underneath?"**

* Array?
* Hash table?
* Tree?
* Heap?

That’s what defines performance.

---
