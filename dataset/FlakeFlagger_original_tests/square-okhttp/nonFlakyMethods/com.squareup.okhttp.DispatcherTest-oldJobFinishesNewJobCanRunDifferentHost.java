@Test public void oldJobFinishesNewJobCanRunDifferentHost() throws Exception {
  dispatcher.setMaxRequests(1);
  client.enqueue(newRequest("http://a/1"),receiver);
  client.enqueue(newRequest("http://b/1"),receiver);
  executor.finishJob("http://a/1");
  executor.assertJobs("http://b/1");
}
