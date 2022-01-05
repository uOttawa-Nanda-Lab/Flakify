@Test public void should_reinit_batch_context_and_consistency_after_exception() throws Exception {
  boolean exceptionCaught=false;
  Tweet tweet1=TweetTestBuilder.tweet().randomId().content("simple_tweet1").buid();
  Tweet tweet2=TweetTestBuilder.tweet().randomId().content("simple_tweet2").buid();
  manager.persist(tweet1);
  Batch batchEm=pmf.createBatch();
  batchEm.startBatch();
  batchEm.startBatch(TWO);
  batchEm.persist(tweet2);
  try {
    batchEm.endBatch();
  }
 catch (  Exception e) {
    assertThatBatchContextHasBeenReset(batchEm);
    exceptionCaught=true;
  }
  assertThat(exceptionCaught).isTrue();
  Thread.sleep(1000);
  logAsserter.prepareLogLevel();
  batchEm.persist(tweet2);
  batchEm.endBatch();
  logAsserter.assertConsistencyLevels(ONE);
}
