@Test public void should_dirty_check_assign_new_value_to_map() throws Exception {
  bean.setPreferences(ImmutableMap.of(4,"test",5,"again"));
  manager.update(bean);
  Row row=session.execute("select preferences from CompleteBean where id=" + bean.getId()).one();
  Map<Integer,String> preferences=row.getMap("preferences",Integer.class,String.class);
  assertThat(preferences).hasSize(2);
  assertThat(preferences).containsKey(4);
  assertThat(preferences.get(4)).isEqualTo("test");
  assertThat(preferences).containsKey(5);
  assertThat(preferences.get(5)).isEqualTo("again");
}
