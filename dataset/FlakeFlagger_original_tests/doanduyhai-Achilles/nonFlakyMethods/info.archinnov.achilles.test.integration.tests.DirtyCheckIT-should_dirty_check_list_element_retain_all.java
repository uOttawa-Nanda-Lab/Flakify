@Test public void should_dirty_check_list_element_retain_all() throws Exception {
  bean.getFriends().retainAll(asList("foo","qux"));
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  List<String> friends=row.getList("friends",String.class);
  assertThat(friends).hasSize(1);
  assertThat(friends.get(0)).isEqualTo("foo");
}
