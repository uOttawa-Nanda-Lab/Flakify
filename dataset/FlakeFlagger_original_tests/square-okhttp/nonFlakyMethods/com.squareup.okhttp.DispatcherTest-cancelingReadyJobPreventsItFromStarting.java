@Test public void cancelingReadyJobPreventsItFromStarting() throws Exception {
  dispatcher.setMaxRequestsPerHost(1);
  client.enqueue(newRequest("http://a/1"),receiver);
  client.enqueue(newRequest("http://a/2","tag1"),receiver);
  dispatcher.cancel("tag1");
  executor.finishJob("http://a/1");
  executor.assertJobs();
}
