@Test public void should_batch_with_custom_consistency_level() throws Exception {
  Tweet tweet1=TweetTestBuilder.tweet().randomId().content("simple_tweet1").buid();
  Tweet tweet2=TweetTestBuilder.tweet().randomId().content("simple_tweet2").buid();
  Tweet tweet3=TweetTestBuilder.tweet().randomId().content("simple_tweet3").buid();
  manager.persist(tweet1);
  Batch batchEm=pmf.createBatch();
  batchEm.startBatch();
  batchEm.startBatch(QUORUM);
  logAsserter.prepareLogLevel();
  Tweet foundTweet1=batchEm.find(Tweet.class,tweet1.getId());
  assertThat(foundTweet1.getContent()).isEqualTo(tweet1.getContent());
  batchEm.persist(tweet2);
  batchEm.persist(tweet3);
  batchEm.endBatch();
  logAsserter.assertConsistencyLevels(QUORUM);
  assertThatBatchContextHasBeenReset(batchEm);
}
