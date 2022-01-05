@Test public void should_apply_null_heap_byte_buffer(){
  Tweet entity=TweetTestBuilder.tweet().randomId().content("label").buid();
  manager.persist(entity);
  final Select.Where select=QueryBuilder.select().from("Tweet").where(QueryBuilder.eq("id",entity.getId()));
  final String queryString=select.getQueryString();
  final ByteBuffer[] values=select.getValues();
  final TypedQuery<Tweet> queryBuilder=manager.typedQuery(Tweet.class,queryString,values);
  final Tweet actual=queryBuilder.getFirst();
  assertThat(actual).isNotNull();
}
