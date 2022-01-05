@Test public void should_dirty_check_list_element_remove_element_only_once() throws Exception {
  bean.getFriends().addAll(asList("qux","tux","foo","bar"));
  bean.getFriends().remove("foo");
  bean.getFriends().removeAll(asList("bar"));
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  List<String> friends=row.getList("friends",String.class);
  assertThat(friends).hasSize(4);
  assertThat(friends.get(0)).isEqualTo("qux");
  assertThat(friends.get(1)).isEqualTo("tux");
  assertThat(friends.get(2)).isEqualTo("foo");
  assertThat(friends.get(3)).isEqualTo("bar");
}
