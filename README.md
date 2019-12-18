# VLINX ProcessUtils

Process Utilities for Java

## Run command

```java
import io.vlinx.processutils.ProcessUtils;
 
String command = "ls -lh";
ProcessUtils.run(command)
```
or
```java
import io.vlinx.processutils.ProcessUtils;
 
String[] command = new String[]{"ls","-lh"};
ProcessUtils.run(command)
```


## Run command and show output instantly

```java
import io.vlinx.processutils.ProcessUtils;

String command = "ls -lh";
// showOutput = true
ProcessUtils.run(command, true);
```
or
```java
import io.vlinx.processutils.ProcessUtils;

String[] command = new String[]{"ls","-lh"};
// showOutput = true
ProcessUtils.run(command, true);
```

## Run command and capture the output

```java
import io.vlinx.processutils.ProcessUtils; 

String command = "ls -lh";
StringBuilder outputBuilder = new StringBuilder();
// showOutput = false
ProcessUtils.run(command, false, outputBuilder);
String output = outputBuilder.toString();
```
or
```java
import io.vlinx.processutils.ProcessUtils; 

String[] command = new String[]{"ls","-lh"};
StringBuilder outputBuilder = new StringBuilder();
// showOutput = false
ProcessUtils.run(command, false, outputBuilder);
String output = outputBuilder.toString();
```
## Handle Exception

if the process exit value is non-zero, it will cause a ProcessException

```java
import io.vlinx.processutils.ProcessUtils;
import io.vlinx.processutils.exception.ProcessException;
 
String command = "ls -lh";

try{
    ProcessUtils.run(command)
}catch(ProcessException e){
   // get exit value
   int exitValue = e.getExitValue();
   // get command
   String[] command = e.getCommand();
   // handle exception
   ......
}

```

