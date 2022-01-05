@Test public void should_merge() throws Exception {
  Long userId=RandomUtils.nextLong();
  Long originalAuthorId=RandomUtils.nextLong();
  UUID tweetId=UUIDGen.getTimeUUID();
  Date creationDate=new Date();
  ClusteredTweetId id=new ClusteredTweetId(userId,tweetId,creationDate);
  ClusteredTweetEntity tweet=new ClusteredTweetEntity(id,"this is a tweet",userId,false);
  tweet=manager.persist(tweet);
  tweet.setContent("this is a new tweet2");
  tweet.setIsARetweet(true);
  tweet.setOriginalAuthorId(originalAuthorId);
  manager.update(tweet);
  ClusteredTweetEntity found=manager.find(ClusteredTweetEntity.class,id);
  assertThat(found.getContent()).isEqualTo("this is a new tweet2");
  assertThat(found.getOriginalAuthorId()).isEqualTo(originalAuthorId);
  assertThat(found.getIsARetweet()).isTrue();
}
