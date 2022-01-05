@Test public void should_batch_counters() throws Exception {
  Batch batchEm=pmf.createBatch();
  batchEm.startBatch();
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("name").buid();
  entity=batchEm.persist(entity);
  entity.setLabel("label");
  Tweet welcomeTweet=TweetTestBuilder.tweet().randomId().content("welcomeTweet").buid();
  entity.setWelcomeTweet(welcomeTweet);
  entity.getVersion().incr(10L);
  batchEm.update(entity);
  Map<String,Object> result=manager.nativeQuery("SELECT label from CompleteBean where id=" + entity.getId()).first();
  assertThat(result).isNull();
  result=manager.nativeQuery("SELECT counter_value from achilles_counter_table where fqcn='" + CompleteBean.class.getCanonicalName() + "' and primary_key='"+ entity.getId()+ "' and property_name='version'").first();
  assertThat(result).isNull();
  batchEm.endBatch();
  Statement statement=new SimpleStatement("SELECT label from CompleteBean where id=" + entity.getId());
  Row row=manager.getNativeSession().execute(statement).one();
  assertThat(row.getString("label")).isEqualTo("label");
  result=manager.nativeQuery("SELECT counter_value from achilles_counter_table where fqcn='" + CompleteBean.class.getCanonicalName() + "' and primary_key='"+ entity.getId()+ "' and property_name='version'").first();
  assertThat(result.get("counter_value")).isEqualTo(10L);
  assertThatBatchContextHasBeenReset(batchEm);
}
