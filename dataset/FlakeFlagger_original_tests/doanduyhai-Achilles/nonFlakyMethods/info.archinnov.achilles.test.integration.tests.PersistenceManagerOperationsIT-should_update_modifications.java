@Test public void should_update_modifications() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("Jonathan").age(40L).addFriends("bob","alice").addFollowers("Billy","Stephen","Jacky").addPreference(1,"US").addPreference(2,"New York").buid();
  manager.persist(entity);
  CompleteBean found=manager.find(CompleteBean.class,entity.getId());
  found.setAge(100L);
  found.getFriends().add("eve");
  found.getPreferences().put(1,"FR");
  manager.update(found);
  Row row=session.execute("select * from completebean where id=" + entity.getId()).one();
  assertThat(row.getLong("age_in_years")).isEqualTo(100L);
  assertThat(row.getList("friends",String.class)).containsExactly("bob","alice","eve");
  Map<Integer,String> preferences=row.getMap("preferences",Integer.class,String.class);
  assertThat(preferences.get(1)).isEqualTo("FR");
  assertThat(preferences.get(2)).isEqualTo("New York");
}
