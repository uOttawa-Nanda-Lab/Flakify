@Test public void should_not_dirty_check_map_entrySet_remove_entry() throws Exception {
  Set<Entry<Integer,String>> entrySet=bean.getPreferences().entrySet();
  Entry<Integer,String> entry=entrySet.iterator().next();
  entrySet.remove(entry);
  entry.setValue("test");
  manager.update(bean);
  Row row=session.execute("select preferences from CompleteBean where id=" + bean.getId()).one();
  Map<Integer,String> preferences=row.getMap("preferences",Integer.class,String.class);
  assertThat(preferences).hasSize(3);
  assertThat(preferences.get(1)).isEqualTo("FR");
  assertThat(preferences.get(2)).isEqualTo("Paris");
  assertThat(preferences.get(3)).isEqualTo("75014");
}
