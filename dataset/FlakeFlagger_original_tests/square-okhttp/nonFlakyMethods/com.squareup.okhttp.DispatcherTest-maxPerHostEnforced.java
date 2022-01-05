@Test public void maxPerHostEnforced() throws Exception {
  dispatcher.setMaxRequestsPerHost(2);
  client.enqueue(newRequest("http://a/1"),receiver);
  client.enqueue(newRequest("http://a/2"),receiver);
  client.enqueue(newRequest("http://a/3"),receiver);
  executor.assertJobs("http://a/1","http://a/2");
}
