# TooDoo
> “It’s not knowing what to do; it’s doing what you know.” – Tony Robbins [(source)](https://dansilvestre.com/productivity-quotes/)

TooDoo is your very own task management chatbot. It is developed
- **to be user-friendly**
- **to improve user productivity**
- **to have easy syntax**
- **to be ~charismatic~ sassy**

Here are the steps to help you get started,
1. Download the .jar file from [here](https://github.com/ChekaLowQiJun/ip/releases/tag/A-Jar).
2. Double-click the .jar file to execute it **OR** If you are a seasoned programmer, type this into your terminal, 
```bash
cd #path_to_directory_that_contains_jar_file
java -jar TooDoo.jar
```
3. Add your tasks.
4. Let TooDoo handle the rest for you! :hatched_chick:

And the best part...it is **100% FREE** :)

Current/Future features:
- [x] Adding ToDos, Deadlines, and Events (with date and time support)
- [x] Delete, mark, and unmark tasks
- [x] Find tasks that contain certain keywords
- [ ] Filtering tasks by date (coming soon)
- [ ] Adding reminders (coming soon)

If you want to play around with the existing code base, feel free to fork and clone the [repo](https://github.com/ChekaLowQiJun/ip) to your local device and start right now! Let me get you started by showing you the ```main``` method:
```java
public static void main(String[] args) {
        new TooDoo(STORAGE_PATH).run();
    }
```

### Acknowledgements
- DeepSeek Prompt: How can I make my User Guide have more personality and be more in depth?
- ChatGPT Prompt: What should I test for in my Parser Class?
- DeepSeek Prompt: How can I use Java Streams to make my code cleaner?
- DeepSeek Prompt: How should I go about creating a custom sort in Java?
- DeepSeek Prompt: How can I effectively use Java Varargs to write cleaner code?
