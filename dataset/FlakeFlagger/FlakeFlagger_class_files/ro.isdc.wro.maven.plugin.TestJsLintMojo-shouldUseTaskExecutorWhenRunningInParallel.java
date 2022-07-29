package ro.isdc.wro.maven.plugin;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TestJsLintMojo {
@Test public void shouldUseTaskExecutorWhenRunningInParallel() throws Exception {
  final AtomicBoolean invoked=new AtomicBoolean();
  final TaskExecutor<Void> taskExecutor=new TaskExecutor<Void>(){
    @Override public void submit(    final Collection<Callable<Void>> callables) throws Exception {
      invoked.set(true);
      super.submit(callables);
    }
  }
;
  mojo.setFailNever(true);
  mojo.setTaskExecutor(taskExecutor);
  mojo.setIgnoreMissingResources(true);
  mojo.setParallelProcessing(false);
  mojo.execute();
  assertFalse(invoked.get());
  mojo.setParallelProcessing(true);
  mojo.execute();
  assertTrue(invoked.get());
}

}