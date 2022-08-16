package hudson.remoting;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.AntClassLoader;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * @author Kohsuke Kawaguchi
 */
public class PrefetchingTest extends RmiTestBase implements Serializable {
    private transient AntClassLoader cl;
    private File dir;

    // checksum of the jar files to force loading
    private Checksum sum1,sum2;

    @Override
    protected void setUp() throws Exception {        
        super.setUp();

        URL jar1 = getClass().getClassLoader().getResource("remoting-test-client.jar");
        URL jar2 = getClass().getClassLoader().getResource("remoting-test-client-tests.jar");

        cl = new AntClassLoader(this.getClass().getClassLoader(),true);
        cl.addPathComponent(toFile(jar1));
        cl.addPathComponent(toFile(jar2));

        dir = File.createTempFile("remoting", "cache");
        dir.delete();
        dir.mkdirs();

        channel.setJarCache(new FileSystemJarCache(dir, true));
        channel.call(new JarCacherCallable());
        sum1 = channel.jarLoader.calcChecksum(jar1);
        sum2 = channel.jarLoader.calcChecksum(jar2);
    }

    private File toFile(URL url) {
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            return new File(url.getPath());
        }
    }

    @Override
    protected void tearDown() throws Exception {
        cl.cleanup();
        super.tearDown();

        if (Launcher.isWindows()) {
            // Current Resource loader implementation keep files open even if we close the classloader.
            // This check has been never working correctly in Windows.
            // TODO: Fix it as a part of JENKINS-38696
            return;
        }
        
        // because the dir is used by FIleSystemJarCache to asynchronously load stuff
        // we might fail to shut it down right away
        for (int i=0; ; i++) {
            try {
                FileUtils.deleteDirectory(dir);
                return;
            } catch (IOException e) {
                if (i==3)   throw e;
                Thread.sleep(1000);
            }
        }
    }

    /**
     * This should cause the jar file to be sent to the other side
     */
    public void testJarLoadingTest() throws Exception {
        channel.call(new ForceJarLoad(sum1));
        channel.call(new ForceJarLoad(sum2));

        Callable<Void,IOException> sc = (Callable<Void, IOException>)cl.loadClass("test.ClassLoadingFromJarTester").newInstance();
        ((Function<Function<Object, Object>, Void>)sc).apply(new Verifier());
        assertNull(channel.call(sc));
    }

    private static class Verifier implements Function<Object,Object>, Serializable {
        @Override
        public Object apply(Object o) {
            try {
                // verify that 'o' is loaded from a jar file
                String loc = Which.classFileUrl(o.getClass()).toExternalForm();
                System.out.println(loc);
                assertTrue(loc, loc.startsWith("jar:"));
                return null;
            } catch (IOException e) {
                throw new Error(e);
            }
        }
        private static final long serialVersionUID = 1L;
    }

    public void testGetResource() throws Exception {
        channel.call(new ForceJarLoad(sum1));
        channel.call(new ForceJarLoad(sum2));

        Callable<String,IOException> c = (Callable<String,IOException>)cl.loadClass("test.HelloGetResource").newInstance();
        String v = channel.call(c);
        System.out.println(v);

        verifyResource(v);
    }

    public void testGetResource_precache() throws Exception {
        Callable<String,IOException> c = (Callable<String,IOException>)cl.loadClass("test.HelloGetResource").newInstance();
        String v = channel.call(c);
        System.out.println(v);

        verifyResourcePrecache(v);
    }

    public void testGetResourceAsStream() throws Exception {
        Callable<String,IOException> c = (Callable<String,IOException>)cl.loadClass("test.HelloGetResourceAsStream").newInstance();
        String v = channel.call(c);
        assertEquals("hello",v);
    }

    /**
     * Validates that the resource is coming from a jar.
     */
    private void verifyResource(String v) {
        Assert.assertThat(v, allOf(startsWith("jar:file:"), 
                                   containsString(dir.toURI().getPath()), 
                                   endsWith("::hello")));
    }

    /**
     * Validates that the resource is coming from a file path.
     */
    private void verifyResourcePrecache(String v) {
        assertTrue(v, v.startsWith("file:"));
        assertTrue(v, v.endsWith("::hello"));
    }

    /**
     * Once the jar files are cached, ClassLoader.getResources() should return jar URLs.
     */
    public void testGetResources() throws Exception {
        channel.call(new ForceJarLoad(sum1));
        channel.call(new ForceJarLoad(sum2));

        Callable<String,IOException> c = (Callable<String,IOException>)cl.loadClass("test.HelloGetResources").newInstance();
        String v = channel.call(c);
        System.out.println(v);  // should find two resources

        String[] lines = v.split("\n");

        verifyResource(lines[0]);

        Assert.assertThat(lines[1], allOf(startsWith("jar:file:"), 
                                          containsString(dir.toURI().getPath()), 
                                          endsWith("::hello2")));
    }

    /**
     * Unlike {@link #testGetResources()}, the URL should begin with file:... before the jar file gets cached
     */
    public void testGetResources_precache() throws Exception {
        Callable<String,IOException> c = (Callable<String,IOException>)cl.loadClass("test.HelloGetResources").newInstance();
        String v = channel.call(c);
        System.out.println(v);  // should find two resources

        String[] lines = v.split("\n");

        assertTrue(lines[0], lines[0].startsWith("file:"));
        assertTrue(lines[1], lines[1].startsWith("file:"));
        assertTrue(lines[0], lines[0].endsWith("::hello"));
        assertTrue(lines[1], lines[1].endsWith("::hello2"));
    }

    public void testInnerClass() throws Exception {
        Echo<Object> e = new Echo<>();
        e.value = cl.loadClass("test.Foo").newInstance();
        Object r = channel.call(e);

        ((Predicate<Void>)r).apply(null); // this verifies that the object is still in a good state
    }

    private static final class Echo<V> extends CallableBase<V,IOException> implements Serializable {
        V value;

        @Override
        public V call() {
            return value;
        }
        private static final long serialVersionUID = 1L;
    }

    /**
     * Force the remote side to fetch the retrieval of the specific jar file.
     */
    private static final class ForceJarLoad extends CallableBase<Void,IOException> implements Serializable{
        private final long sum1,sum2;

        private ForceJarLoad(Checksum sum) {
            this.sum1 = sum.sum1;
            this.sum2 = sum.sum2;
        }

        @Override
        public Void call() throws IOException {
            try {
                final Channel ch = Channel.currentOrFail();
                final JarCache jarCache = ch.getJarCache();
                if (jarCache == null) {
                    throw new IOException("Cannot Force JAR load, JAR cache is disabled");
                }
                jarCache.resolve(ch,sum1,sum2).get();
                return null;
            } catch (InterruptedException | ExecutionException e) {
                throw new IOException(e);
            }
        }
        private static final long serialVersionUID = 1L;
    }

    private class JarCacherCallable extends CallableBase<Void, IOException> {
        @Override
        public Void call() {
            Channel.currentOrFail().setJarCache(new FileSystemJarCache(dir, true));
            return null;
        }
        private static final long serialVersionUID = 1L;
    }
    private static final long serialVersionUID = 1L;
}
