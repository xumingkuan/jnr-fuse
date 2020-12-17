## Project 3 report

### EchoFS

We implement EchoFS (an echo file system) using the memoryFS in the example. For each function, we concatenate the mount point with the path passed to it to convert the relative path to the absolute path, and print out the parameters to a file `log.txt` created in the constructor function of EchoFS. Some parameters are of the `long` type, and we need to convert it to `Long` and then invoke the `toString()` method.

To test this file system, we implement `EchoFSTest.java` in `src/test/java/ru/serce/jnrfuse/struct/`, which tests an `ls` call.

After running `EchoFSTest` in IntelliJ IDEA (using the default configurations), `log.txt` should have something like

```
access, /tmp/memfuse2281410085815878219, 4
getattr, /tmp/memfuse2281410085815878219/.Trash, (stat)
getattr, /tmp/memfuse2281410085815878219/.Trash-1000, (stat)
getattr, /tmp/memfuse2281410085815878219, (stat)
readdir, /tmp/memfuse2281410085815878219, jnr.ffi.provider.jffi.DirectMemoryIO[address=0x7fb840022730], (filter), 0, 0
releasedir, /tmp/memfuse2281410085815878219, 0
```

where `/tmp/memfuse2281410085815878219` is a temporary directory EchoFS mounts at.

The first three lines are generated at the beginning, and the other three lines (getattr, readdir, releasedir) are called in the `ls` call. In the log, `(stat)` and `(filter)` denote `FileStat` and `FuseFillDir` parameters that cannot be easily pretty-printed.

