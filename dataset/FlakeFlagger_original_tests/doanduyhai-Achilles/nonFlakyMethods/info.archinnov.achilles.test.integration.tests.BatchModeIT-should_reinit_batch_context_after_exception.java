@Test public void should_reinit_batch_context_after_exception() throws Exception {
  User user=UserTestBuilder.user().id(123456494L).firstname("firstname").lastname("lastname").buid();
  Tweet tweet=TweetTestBuilder.tweet().randomId().content("simple_tweet").creator(user).buid();
  Batch batchEm=pmf.createBatch();
  batchEm.startBatch();
  try {
    batchEm.persist(tweet,OptionsBuilder.withConsistency(ConsistencyLevel.EACH_QUORUM));
  }
 catch (  AchillesException e) {
    batchEm.cleanBatch();
    assertThatBatchContextHasBeenReset(batchEm);
    assertThat(batchEm.find(Tweet.class,tweet.getId())).isNull();
  }
  batchEm.persist(user);
  batchEm.endBatch();
  User foundUser=batchEm.find(User.class,user.getId());
  assertThat(foundUser.getFirstname()).isEqualTo("firstname");
  assertThat(foundUser.getLastname()).isEqualTo("lastname");
  batchEm.persist(tweet);
  batchEm.endBatch();
  Tweet foundTweet=batchEm.find(Tweet.class,tweet.getId());
  assertThat(foundTweet.getContent()).isEqualTo("simple_tweet");
  assertThat(foundTweet.getCreator().getId()).isEqualTo(foundUser.getId());
  assertThat(foundTweet.getCreator().getFirstname()).isEqualTo("firstname");
  assertThat(foundTweet.getCreator().getLastname()).isEqualTo("lastname");
  assertThatBatchContextHasBeenReset(batchEm);
}
