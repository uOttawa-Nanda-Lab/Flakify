@Test public void should_return_first_entity_for_typed_query_with_simple_select() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").version(CounterBuilder.incr(15L)).buid();
  manager.persist(entity);
  String queryString="SELECT id,name,friends FROM CompleteBean LIMIT 3";
  CompleteBean actual=manager.typedQuery(CompleteBean.class,queryString).getFirst();
  Factory factory1=(Factory)actual;
  @SuppressWarnings("unchecked") EntityInterceptor<CompleteBean> interceptor1=(EntityInterceptor<CompleteBean>)factory1.getCallback(0);
  CompleteBean target1=(CompleteBean)interceptor1.getTarget();
  assertThat(target1.getAge()).isNull();
  assertThat(target1.getFollowers()).isNull();
  assertThat(target1.getLabel()).isNull();
  assertThat(target1.getPreferences()).isNull();
  assertThat(target1.getWelcomeTweet()).isNull();
  assertThat(Factory.class.isAssignableFrom(actual.getClass())).isTrue();
  assertThat(actual.getId()).isEqualTo(entity.getId());
  assertThat(actual.getName()).isEqualTo(entity.getName());
  assertThat(actual.getFriends()).containsAll(entity.getFriends());
  assertThat(actual.getVersion().get()).isEqualTo(15L);
}
