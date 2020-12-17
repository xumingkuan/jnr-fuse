package ru.serce.jnrfuse.struct;


import org.junit.Test;
import ru.serce.jnrfuse.examples.MemoryFS;
import ru.serce.jnrfuse.EchoFS;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class EchoFSTest extends BaseFsTest {
    @Test
    public void testls() throws Exception {
        EchoFS stub = new EchoFS();

        Path tmpDir = tempPath();
        blockingMount(stub, tmpDir);
        String s;
        try {
            Runtime r = Runtime.getRuntime();
            String command = "cd " + tmpDir.toString() + "; cat a.txt";
            //String command = "ls " + tmpDir.toAbsolutePath().toString();// + "/Directory with files\"";
            System.out.println(command);
            Process p = r.exec(command);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
            System.out.println("exception: " + e.toString());
        } finally {
            stub.umount();
        }
        System.out.println("testls done");
    }

}
