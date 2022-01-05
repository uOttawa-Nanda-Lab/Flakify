@Test public void increasingMaxPerHostPromotesJobsImmediately() throws Exception {
  dispatcher.setMaxRequestsPerHost(2);
  client.enqueue(newRequest("http://a/1"),receiver);
  client.enqueue(newRequest("http://a/2"),receiver);
  client.enqueue(newRequest("http://a/3"),receiver);
  client.enqueue(newRequest("http://a/4"),receiver);
  client.enqueue(newRequest("http://a/5"),receiver);
  dispatcher.setMaxRequestsPerHost(4);
  executor.assertJobs("http://a/1","http://a/2","http://a/3","http://a/4");
}
