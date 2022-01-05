@Test public void should_dirty_check_map_put_all() throws Exception {
  Map<Integer,String> map=new HashMap<>();
  map.put(3,"75015");
  map.put(4,"test");
  bean.getPreferences().putAll(map);
  manager.update(bean);
  Row row=session.execute("select preferences from CompleteBean where id=" + bean.getId()).one();
  Map<Integer,String> preferences=row.getMap("preferences",Integer.class,String.class);
  assertThat(preferences).hasSize(4);
  assertThat(preferences.get(3)).isEqualTo("75015");
  assertThat(preferences.get(4)).isEqualTo("test");
}
