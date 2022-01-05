@Test public void maxRequestsEnforced() throws Exception {
  dispatcher.setMaxRequests(3);
  client.enqueue(newRequest("http://a/1"),receiver);
  client.enqueue(newRequest("http://a/2"),receiver);
  client.enqueue(newRequest("http://b/1"),receiver);
  client.enqueue(newRequest("http://b/2"),receiver);
  executor.assertJobs("http://a/1","http://a/2","http://b/1");
}
