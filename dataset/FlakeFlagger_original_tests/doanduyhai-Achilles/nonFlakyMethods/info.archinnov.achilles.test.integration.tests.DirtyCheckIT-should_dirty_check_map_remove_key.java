/** 
 * Ignore because of https://datastax-oss.atlassian.net/browse/JAVA-271
 */
@Test public void should_dirty_check_map_remove_key() throws Exception {
  bean.getPreferences().remove(1);
  manager.update(bean);
  Row row=session.execute("select preferences from CompleteBean where id=" + bean.getId()).one();
  Map<Integer,String> preferences=row.getMap("preferences",Integer.class,String.class);
  assertThat(preferences).hasSize(2);
  assertThat(preferences.get(2)).isEqualTo("Paris");
  assertThat(preferences.get(3)).isEqualTo("75014");
}
