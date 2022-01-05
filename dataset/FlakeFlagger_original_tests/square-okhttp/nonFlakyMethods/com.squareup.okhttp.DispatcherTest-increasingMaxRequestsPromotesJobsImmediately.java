@Test public void increasingMaxRequestsPromotesJobsImmediately() throws Exception {
  dispatcher.setMaxRequests(2);
  client.enqueue(newRequest("http://a/1"),receiver);
  client.enqueue(newRequest("http://b/1"),receiver);
  client.enqueue(newRequest("http://c/1"),receiver);
  client.enqueue(newRequest("http://a/2"),receiver);
  client.enqueue(newRequest("http://b/2"),receiver);
  dispatcher.setMaxRequests(4);
  executor.assertJobs("http://a/1","http://b/1","http://c/1","http://a/2");
}
