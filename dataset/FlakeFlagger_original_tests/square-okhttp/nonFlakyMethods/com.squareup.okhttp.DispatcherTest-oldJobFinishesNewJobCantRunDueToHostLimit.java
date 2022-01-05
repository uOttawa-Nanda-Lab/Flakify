@Test public void oldJobFinishesNewJobCantRunDueToHostLimit() throws Exception {
  dispatcher.setMaxRequestsPerHost(1);
  client.enqueue(newRequest("http://a/1"),receiver);
  client.enqueue(newRequest("http://b/1"),receiver);
  client.enqueue(newRequest("http://a/2"),receiver);
  executor.finishJob("http://b/1");
  executor.assertJobs("http://a/1");
}
