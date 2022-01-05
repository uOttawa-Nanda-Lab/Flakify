@Test public void enqueuedJobsRunImmediately() throws Exception {
  client.enqueue(newRequest("http://a/1"),receiver);
  executor.assertJobs("http://a/1");
}
