package vlinx.processutils.exception;

public class ProcessException extends Exception {

    private int exitValue = -1;
    private String[] command;

    public ProcessException(String message, int returnCode, String[] command) {
        super(message);
        this.exitValue = returnCode;
        this.command = command;
    }

    public int getExitValue() {
        return exitValue;
    }

    public String[] getCommand() {
        return command;
    }


}
