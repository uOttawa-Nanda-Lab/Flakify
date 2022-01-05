@Test public void oldJobFinishesNewJobWithSameHostStarts() throws Exception {
  dispatcher.setMaxRequests(2);
  dispatcher.setMaxRequestsPerHost(1);
  client.enqueue(newRequest("http://a/1"),receiver);
  client.enqueue(newRequest("http://b/1"),receiver);
  client.enqueue(newRequest("http://b/2"),receiver);
  client.enqueue(newRequest("http://a/2"),receiver);
  executor.finishJob("http://a/1");
  executor.assertJobs("http://b/1","http://a/2");
}
