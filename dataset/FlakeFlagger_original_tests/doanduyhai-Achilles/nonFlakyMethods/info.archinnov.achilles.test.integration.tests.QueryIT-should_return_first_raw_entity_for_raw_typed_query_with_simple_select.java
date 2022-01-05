@Test public void should_return_first_raw_entity_for_raw_typed_query_with_simple_select() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").version(CounterBuilder.incr(15L)).buid();
  manager.persist(entity);
  String queryString="SELECT id,name,friends FROM CompleteBean LIMIT 3";
  CompleteBean actual=manager.rawTypedQuery(CompleteBean.class,queryString).getFirst();
  assertThat(Factory.class.isAssignableFrom(actual.getClass())).isFalse();
  assertThat(actual.getId()).isEqualTo(entity.getId());
  assertThat(actual.getName()).isEqualTo(entity.getName());
  assertThat(actual.getLabel()).isNull();
  assertThat(actual.getAge()).isNull();
  assertThat(actual.getFriends()).containsAll(entity.getFriends());
  assertThat(actual.getFollowers()).isNull();
  assertThat(actual.getPreferences()).isNull();
  assertThat(actual.getVersion()).isNull();
  assertThat(actual.getWelcomeTweet()).isNull();
}
