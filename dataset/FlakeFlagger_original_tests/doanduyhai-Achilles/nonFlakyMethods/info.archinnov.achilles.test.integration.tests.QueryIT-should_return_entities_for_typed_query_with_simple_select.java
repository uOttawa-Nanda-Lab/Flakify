@Test public void should_return_entities_for_typed_query_with_simple_select() throws Exception {
  CompleteBean entity1=builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").buid();
  CompleteBean entity2=builder().randomId().name("John DOO").age(34L).addFriends("qux","twix").addFollowers("Isaac","Lara").addPreference(1,"US").addPreference(2,"NewYork").buid();
  manager.persist(entity1);
  manager.persist(entity2);
  String queryString="SELECT id,name,friends FROM CompleteBean LIMIT 3";
  List<CompleteBean> actual=manager.typedQuery(CompleteBean.class,queryString).get();
  assertThat(actual).hasSize(2);
  CompleteBean found1=actual.get(0);
  CompleteBean found2=actual.get(1);
  Factory factory1=(Factory)found1;
  @SuppressWarnings("unchecked") EntityInterceptor<CompleteBean> interceptor1=(EntityInterceptor<CompleteBean>)factory1.getCallback(0);
  CompleteBean target1=(CompleteBean)interceptor1.getTarget();
  assertThat(target1.getAge()).isNull();
  assertThat(target1.getFollowers()).isNull();
  assertThat(target1.getLabel()).isNull();
  assertThat(target1.getPreferences()).isNull();
  assertThat(target1.getWelcomeTweet()).isNull();
  Factory factory2=(Factory)found1;
  @SuppressWarnings("unchecked") EntityInterceptor<CompleteBean> interceptor2=(EntityInterceptor<CompleteBean>)factory2.getCallback(0);
  CompleteBean target2=(CompleteBean)interceptor2.getTarget();
  assertThat(target2.getAge()).isNull();
  assertThat(target2.getFollowers()).isNull();
  assertThat(target2.getLabel()).isNull();
  assertThat(target2.getPreferences()).isNull();
  assertThat(target2.getWelcomeTweet()).isNull();
  if (found1.getId().equals(entity1.getId())) {
    CompleteBean reference=entity1;
    assertThat(Factory.class.isAssignableFrom(found1.getClass())).isTrue();
    assertThat(found1.getId()).isEqualTo(reference.getId());
    assertThat(found1.getName()).isEqualTo(reference.getName());
    assertThat(found1.getFriends()).containsAll(reference.getFriends());
    reference=entity2;
    assertThat(Factory.class.isAssignableFrom(found2.getClass())).isTrue();
    assertThat(found2.getId()).isEqualTo(reference.getId());
    assertThat(found2.getName()).isEqualTo(reference.getName());
    assertThat(found2.getFriends()).containsAll(reference.getFriends());
  }
 else {
    CompleteBean reference=entity2;
    assertThat(Factory.class.isAssignableFrom(found1.getClass())).isTrue();
    assertThat(found1.getId()).isEqualTo(reference.getId());
    assertThat(found1.getName()).isEqualTo(reference.getName());
    assertThat(found1.getFriends()).containsAll(reference.getFriends());
    reference=entity1;
    assertThat(Factory.class.isAssignableFrom(found2.getClass())).isTrue();
    assertThat(found2.getId()).isEqualTo(reference.getId());
    assertThat(found2.getName()).isEqualTo(reference.getName());
    assertThat(found2.getFriends()).containsAll(reference.getFriends());
  }
}
