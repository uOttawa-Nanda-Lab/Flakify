@Test public void should_initialize_lazy_properties() throws Exception {
  Tweet tweet=new Tweet();
  tweet.setId(UUIDGen.getTimeUUID());
  tweet.setContent("welcome");
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("name").label("label").age(45L).addFriends("foo","bar").welcomeTweet(tweet).version(CounterBuilder.incr(11L)).buid();
  manager.persist(entity);
  CompleteBean foundEntity=manager.find(CompleteBean.class,entity.getId());
  CompleteBean rawEntity=manager.initAndRemoveProxy(foundEntity);
  assertThat(rawEntity.getName()).isEqualTo("name");
  assertThat(rawEntity.getLabel()).isEqualTo("label");
  assertThat(rawEntity.getAge()).isEqualTo(45L);
  assertThat(rawEntity.getFriends()).containsExactly("foo","bar");
  assertThat(rawEntity.getWelcomeTweet().getContent()).isEqualTo("welcome");
  assertThat(rawEntity.getVersion()).isInstanceOf(InternalCounterImpl.class);
  assertThat(rawEntity.getVersion().get()).isEqualTo(11L);
}
