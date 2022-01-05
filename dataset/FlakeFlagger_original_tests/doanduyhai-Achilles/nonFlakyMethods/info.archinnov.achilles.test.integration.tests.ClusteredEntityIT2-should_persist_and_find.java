@Test public void should_persist_and_find() throws Exception {
  Long userId=RandomUtils.nextLong();
  UUID tweetId=UUIDGen.getTimeUUID();
  Date creationDate=new Date();
  ClusteredTweetId id=new ClusteredTweetId(userId,tweetId,creationDate);
  ClusteredTweetEntity tweet=new ClusteredTweetEntity(id,"this is a tweet",userId,false);
  manager.persist(tweet);
  ClusteredTweetEntity found=manager.find(ClusteredTweetEntity.class,id);
  assertThat(found.getContent()).isEqualTo("this is a tweet");
  assertThat(found.getOriginalAuthorId()).isEqualTo(userId);
  assertThat(found.getIsARetweet()).isFalse();
}
