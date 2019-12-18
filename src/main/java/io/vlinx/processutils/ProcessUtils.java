package io.vlinx.processutils;

import org.apache.commons.exec.CommandLine;
import io.vlinx.processutils.exception.ProcessException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProcessUtils {
    private static String[] parseCommand(String command) {
        command = command.trim();
        String[] commandArr = CommandLine.parse(command).toStrings();

        for (int i = 0; i < commandArr.length; i++) {
            commandArr[i] = commandArr[i].replace("\"", "").replace("'", "");
        }

        return commandArr;
    }

    public static void run(String command) throws InterruptedException, ProcessException, IOException {
        run(parseCommand(command));
    }

    public static void run(String[] command) throws InterruptedException, ProcessException, IOException {
        run(command, false, null);
    }

    public static void run(String command, boolean showOutput) throws InterruptedException, ProcessException, IOException {
        run(parseCommand(command), showOutput);
    }

    public static void run(String[] command, boolean showOutput) throws InterruptedException, ProcessException, IOException {
        run(command, showOutput, null);
    }

    public static void run(String command, boolean showOutput, StringBuilder outputBuilder) throws InterruptedException, ProcessException, IOException {
        run(parseCommand(command), showOutput, outputBuilder);
    }

    public static void run(String[] command, final boolean showOutput, final StringBuilder outputBuilder) throws InterruptedException, ProcessException, IOException {
        Process process = Runtime.getRuntime().exec(command);

        final BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
        final BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        Thread threadStdout = new Thread(new Runnable() {

            public void run() {
                String line = null;

                try {
                    while ((line = stdout.readLine()) != null) {
                        if (showOutput) {
                            System.out.println(line);
                        }
                        if (outputBuilder != null) {
                            outputBuilder.append(line + "\n");
                        }
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        });

        Thread threadStderr = new Thread(new Runnable() {

            public void run() {
                String line = null;

                try {
                    while ((line = stderr.readLine()) != null) {
                        if (showOutput) {
                            System.out.println(line);
                        }

                        if (outputBuilder != null) {
                            outputBuilder.append(line);
                        }
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        });

        threadStdout.setDaemon(true);
        threadStdout.start();
        threadStderr.setDaemon(true);
        threadStderr.start();

        process.waitFor();

        int exit = process.exitValue();

        if (exit != 0) {
            throw new ProcessException(Arrays.toString(command) + " [exit: " + exit + "]", exit, command);
        }
    }
}
