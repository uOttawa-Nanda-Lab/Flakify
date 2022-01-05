@Test public void cancelingRunningJobTakesNoEffectUntilJobFinishes() throws Exception {
  dispatcher.setMaxRequests(1);
  client.enqueue(newRequest("http://a/1","tag1"),receiver);
  client.enqueue(newRequest("http://a/2"),receiver);
  dispatcher.cancel("tag1");
  executor.assertJobs("http://a/1");
  executor.finishJob("http://a/1");
  executor.assertJobs("http://a/2");
}
