@Test public void should_remove() throws Exception {
  Long userId=RandomUtils.nextLong();
  UUID tweetId=UUIDGen.getTimeUUID();
  Date creationDate=new Date();
  ClusteredTweetId id=new ClusteredTweetId(userId,tweetId,creationDate);
  ClusteredTweetEntity tweet=new ClusteredTweetEntity(id,"this is a tweet",userId,false);
  tweet=manager.persist(tweet);
  manager.remove(tweet);
  ClusteredTweetEntity found=manager.find(ClusteredTweetEntity.class,id);
  assertThat(found).isNull();
}
