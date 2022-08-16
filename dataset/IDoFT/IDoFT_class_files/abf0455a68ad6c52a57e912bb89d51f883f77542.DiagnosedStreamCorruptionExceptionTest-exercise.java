package hudson.remoting;

import hudson.remoting.Channel.Mode;
import org.apache.commons.io.output.NullOutputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * @author Kohsuke Kawaguchi
 */
public class DiagnosedStreamCorruptionExceptionTest {
    byte[] payload = {
            0,0,0,0, /* binary stream preamble*/
            (byte)0xAC, (byte)0xED, 0x00, 0x05, /* object input stream header */
            1, 2, 3, 4, 5 /* bogus data */
    };

    @Test
    public void exercise() throws Exception {
        ClassicCommandTransport ct = (ClassicCommandTransport)
                new ChannelBuilder("dummy",null)
                    .withMode(Mode.BINARY)
                    .withBaseLoader(getClass().getClassLoader())
                    .negotiate(new ByteArrayInputStream(payload), new NullOutputStream());

        verify(ct);
    }

    private void verify(ClassicCommandTransport ct) throws IOException, ClassNotFoundException {
        try {
            ct.read();
            fail();
        } catch (DiagnosedStreamCorruptionException e) {
            StringWriter s = new StringWriter();
            PrintWriter w = new PrintWriter(s);
            e.printStackTrace(w);
            w.close();

            String msg = s.toString();
            assertTrue(msg,msg.contains("Read ahead: 0x02 0x03 0x04 0x05"));
            assertTrue(msg,msg.contains("invalid type code: 01"));
            assertSame(StreamCorruptedException.class, e.getCause().getClass());
        }
    }

    /**
     * This tests the behaviour of the diagnosis blocking on a non-completed stream, as the writer end is kept open.
     */
    @Test(timeout=3000)
    public void blockingStreamShouldNotPreventDiagnosis() throws Exception {
        try (FastPipedInputStream in = new FastPipedInputStream();
            FastPipedOutputStream out = new FastPipedOutputStream(in)) {
            out.write(payload);

            ClassicCommandTransport ct = (ClassicCommandTransport)
                    new ChannelBuilder("dummy",null)
                        .withMode(Mode.BINARY)
                        .withBaseLoader(getClass().getClassLoader())
                        .negotiate(in, new NullOutputStream());

            verify(ct);
        }
    }
}
