/** 
 * Test the case where a given writer is used amongst several threads where each thread is updating the same set of metrics. This would be an example case of the writer being used with the MetricsFilter handling several requests/sec to the same URL.
 * @throws Exception if an error occurs
 */
@Test public void testParallism() throws Exception {
  List<WriterThread> threads=new ArrayList<WriterThread>();
  ThreadGroup group=new ThreadGroup("threads");
  for (int i=0; i < 10; i++) {
    WriterThread thread=new WriterThread(group,i,this.writer);
    threads.add(thread);
    thread.start();
  }
  while (group.activeCount() > 0) {
    Thread.sleep(1000);
  }
  for (  WriterThread thread : threads) {
    assertFalse("expected thread caused unexpected exception",thread.isFailed());
  }
}
