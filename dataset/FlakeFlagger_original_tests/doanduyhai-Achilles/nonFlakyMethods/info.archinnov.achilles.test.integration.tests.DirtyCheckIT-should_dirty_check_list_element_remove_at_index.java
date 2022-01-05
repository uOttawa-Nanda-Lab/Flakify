@Test public void should_dirty_check_list_element_remove_at_index() throws Exception {
  bean.getFriends().add("qux");
  bean.getFriends().remove(0);
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  List<String> friends=row.getList("friends",String.class);
  assertThat(friends).hasSize(2);
  assertThat(friends.get(0)).isEqualTo("bar");
  assertThat(friends.get(1)).isEqualTo("qux");
}
