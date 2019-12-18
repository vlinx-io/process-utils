import junit.framework.TestCase;
import org.junit.Test;
import vlinx.processutils.ProcessUtils;
import vlinx.processutils.exception.ProcessException;

import java.io.IOException;

public class ProcessUtilsTest extends TestCase {

    @Test
    public void testRun() {
        String command = "7z";

        try {
            ProcessUtils.run(command);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        } catch (ProcessException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testRunWithOutput() {
        String command = "7z";

        try {
            ProcessUtils.run(command, true);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        } catch (ProcessException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testRunAndCaptureOutput() {

        String command = "7z";

        StringBuilder outputBuilder = new StringBuilder();

        try {
            ProcessUtils.run(command, false, outputBuilder);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        } catch (ProcessException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        System.out.println("-------------------------Output------------------------");
        System.out.println(outputBuilder.toString());

    }
}
