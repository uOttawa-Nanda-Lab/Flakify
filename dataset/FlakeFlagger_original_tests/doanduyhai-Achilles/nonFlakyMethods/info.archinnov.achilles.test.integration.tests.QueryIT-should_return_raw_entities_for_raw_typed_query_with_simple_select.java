@Test public void should_return_raw_entities_for_raw_typed_query_with_simple_select() throws Exception {
  Counter counter1=CounterBuilder.incr(15L);
  CompleteBean entity1=builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").version(counter1).buid();
  Counter counter2=CounterBuilder.incr(17L);
  CompleteBean entity2=builder().randomId().name("John DOO").age(34L).addFriends("qux","twix").addFollowers("Isaac","Lara").addPreference(1,"US").addPreference(2,"NewYork").version(counter2).buid();
  manager.persist(entity1);
  manager.persist(entity2);
  String queryString="  SELECT id, name, friends   FROM CompleteBean LIMIT 3";
  List<CompleteBean> actual=manager.rawTypedQuery(CompleteBean.class,queryString).get();
  assertThat(actual).hasSize(2);
  CompleteBean found1=actual.get(0);
  CompleteBean found2=actual.get(1);
  if (found1.getId().equals(entity1.getId())) {
    CompleteBean reference=entity1;
    assertThat(Factory.class.isAssignableFrom(found1.getClass())).isFalse();
    assertThat(found1.getId()).isEqualTo(reference.getId());
    assertThat(found1.getName()).isEqualTo(reference.getName());
    assertThat(found1.getFriends()).containsAll(reference.getFriends());
    assertThat(found1.getVersion()).isNull();
    reference=entity2;
    assertThat(Factory.class.isAssignableFrom(found2.getClass())).isFalse();
    assertThat(found2.getId()).isEqualTo(reference.getId());
    assertThat(found2.getName()).isEqualTo(reference.getName());
    assertThat(found2.getFriends()).containsAll(reference.getFriends());
    assertThat(found2.getVersion()).isNull();
  }
 else {
    CompleteBean reference=entity2;
    assertThat(Factory.class.isAssignableFrom(found1.getClass())).isFalse();
    assertThat(found1.getId()).isEqualTo(reference.getId());
    assertThat(found1.getName()).isEqualTo(reference.getName());
    assertThat(found1.getFriends()).containsAll(reference.getFriends());
    assertThat(found1.getVersion()).isNull();
    reference=entity1;
    assertThat(Factory.class.isAssignableFrom(found2.getClass())).isFalse();
    assertThat(found2.getId()).isEqualTo(reference.getId());
    assertThat(found2.getName()).isEqualTo(reference.getName());
    assertThat(found2.getFriends()).containsAll(reference.getFriends());
    assertThat(found2.getVersion()).isNull();
  }
}
