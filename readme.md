Developer Notes

* Concurrent hashmap is used to store the association. These are inherently thread safe for gets and puts.
* Concurrent hashmap is used to track the number of times each key is searched for. This enables to remove the least used entry from the association map.

Running the project

 ``` gradle clean test ```