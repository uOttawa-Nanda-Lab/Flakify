@Test public void should_return_entities_for_typed_query_with_select_star() throws Exception {
  CompleteBean entity1=builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").buid();
  CompleteBean entity2=builder().randomId().name("John DOO").age(34L).addFriends("qux","twix").addFollowers("Isaac","Lara").addPreference(1,"US").addPreference(2,"NewYork").buid();
  manager.persist(entity1);
  manager.persist(entity2);
  String queryString="SELECT * FROM CompleteBean LIMIT 3";
  List<CompleteBean> actual=manager.typedQuery(CompleteBean.class,queryString).get();
  assertThat(actual).hasSize(2);
  CompleteBean found1=actual.get(0);
  CompleteBean found2=actual.get(1);
  Factory factory1=(Factory)found1;
  @SuppressWarnings("unchecked") EntityInterceptor<CompleteBean> interceptor1=(EntityInterceptor<CompleteBean>)factory1.getCallback(0);
  CompleteBean target1=(CompleteBean)interceptor1.getTarget();
  assertThat(target1.getLabel()).isNull();
  assertThat(target1.getWelcomeTweet()).isNull();
  Factory factory2=(Factory)found1;
  @SuppressWarnings("unchecked") EntityInterceptor<CompleteBean> interceptor2=(EntityInterceptor<CompleteBean>)factory2.getCallback(0);
  CompleteBean target2=(CompleteBean)interceptor2.getTarget();
  assertThat(target2.getLabel()).isNull();
  assertThat(target2.getWelcomeTweet()).isNull();
  if (found1.getId().equals(entity1.getId())) {
    CompleteBean reference=entity1;
    assertThat(Factory.class.isAssignableFrom(found1.getClass())).isTrue();
    assertThat(found1.getId()).isEqualTo(reference.getId());
    assertThat(found1.getName()).isEqualTo(reference.getName());
    assertThat(found1.getAge()).isEqualTo(reference.getAge());
    assertThat(found1.getFriends()).containsAll(reference.getFriends());
    assertThat(found1.getFollowers()).containsAll(reference.getFollowers());
    assertThat(found1.getPreferences().get(1)).isEqualTo("FR");
    assertThat(found1.getPreferences().get(2)).isEqualTo("Paris");
    assertThat(found1.getPreferences().get(3)).isEqualTo("75014");
    reference=entity2;
    assertThat(Factory.class.isAssignableFrom(found2.getClass())).isTrue();
    assertThat(found2.getId()).isEqualTo(reference.getId());
    assertThat(found2.getName()).isEqualTo(reference.getName());
    assertThat(found2.getAge()).isEqualTo(reference.getAge());
    assertThat(found2.getFriends()).containsAll(reference.getFriends());
    assertThat(found2.getFollowers()).containsAll(reference.getFollowers());
    assertThat(found2.getPreferences().get(1)).isEqualTo("US");
    assertThat(found2.getPreferences().get(2)).isEqualTo("NewYork");
  }
 else {
    CompleteBean reference=entity2;
    assertThat(Factory.class.isAssignableFrom(found1.getClass())).isTrue();
    assertThat(found1.getId()).isEqualTo(reference.getId());
    assertThat(found1.getName()).isEqualTo(reference.getName());
    assertThat(found1.getFriends()).containsAll(reference.getFriends());
    assertThat(found1.getFollowers()).containsAll(reference.getFollowers());
    assertThat(found1.getPreferences().get(1)).isEqualTo("US");
    assertThat(found1.getPreferences().get(2)).isEqualTo("NewYork");
    reference=entity1;
    assertThat(Factory.class.isAssignableFrom(found2.getClass())).isTrue();
    assertThat(found2.getId()).isEqualTo(reference.getId());
    assertThat(found2.getName()).isEqualTo(reference.getName());
    assertThat(found2.getFriends()).containsAll(reference.getFriends());
    assertThat(found2.getFollowers()).containsAll(reference.getFollowers());
    assertThat(found2.getPreferences().get(1)).isEqualTo("FR");
    assertThat(found2.getPreferences().get(2)).isEqualTo("Paris");
    assertThat(found2.getPreferences().get(3)).isEqualTo("75014");
  }
}
