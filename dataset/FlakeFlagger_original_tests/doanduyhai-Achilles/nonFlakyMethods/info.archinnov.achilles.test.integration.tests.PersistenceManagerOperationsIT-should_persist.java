@Test public void should_persist() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").age(35L).addFriends("foo","bar").addFollowers("George","Paul").addPreference(1,"FR").addPreference(2,"Paris").addPreference(3,"75014").version(CounterBuilder.incr(15L)).buid();
  manager.persist(entity);
  Row row=session.execute("select name,age_in_years,friends,followers,preferences from completebean where id = " + entity.getId()).one();
  assertThat(row.getLong("age_in_years")).isEqualTo(35L);
  assertThat(row.getList("friends",String.class)).containsExactly("foo","bar");
  assertThat(row.getSet("followers",String.class)).containsOnly("George","Paul");
  Map<Integer,String> preferences=row.getMap("preferences",Integer.class,String.class);
  assertThat(preferences).containsKey(1);
  assertThat(preferences).containsKey(2);
  assertThat(preferences).containsKey(3);
  assertThat(preferences).containsValue("FR");
  assertThat(preferences).containsValue("Paris");
  assertThat(preferences).containsValue("75014");
  row=session.execute("select counter_value from achilles_counter_table where fqcn = '" + CompleteBean.class.getCanonicalName() + "' and primary_key='"+ entity.getId()+ "' and property_name='version'").one();
  assertThat(row.getLong("counter_value")).isEqualTo(15L);
}
