@Test public void should_dirty_check_map_put_element() throws Exception {
  bean.getPreferences().put(4,"test");
  manager.update(bean);
  Row row=session.execute("select preferences from CompleteBean where id=" + bean.getId()).one();
  Map<Integer,String> preferences=row.getMap("preferences",Integer.class,String.class);
  assertThat(preferences).hasSize(4);
  assertThat(preferences.get(4)).isEqualTo("test");
}
