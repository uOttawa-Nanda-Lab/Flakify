@Test public void should_refresh() throws Exception {
  Long userId=RandomUtils.nextLong();
  Long originalAuthorId=RandomUtils.nextLong();
  UUID tweetId=UUIDGen.getTimeUUID();
  Date creationDate=new Date();
  ClusteredTweetId id=new ClusteredTweetId(userId,tweetId,creationDate);
  ClusteredTweetEntity tweet=new ClusteredTweetEntity(id,"this is a tweet",userId,false);
  tweet=manager.persist(tweet);
  session.execute("update " + CLUSTERED_TWEET_TABLE + " set content='New tweet',original_author_id="+ originalAuthorId+ ",is_a_retweet=true where user_id="+ userId+ " and tweet_id="+ tweetId+ " and creation_date="+ creationDate.getTime());
  Thread.sleep(100);
  manager.refresh(tweet);
  assertThat(tweet.getContent()).isEqualTo("New tweet");
  assertThat(tweet.getOriginalAuthorId()).isEqualTo(originalAuthorId);
  assertThat(tweet.getIsARetweet()).isTrue();
}
