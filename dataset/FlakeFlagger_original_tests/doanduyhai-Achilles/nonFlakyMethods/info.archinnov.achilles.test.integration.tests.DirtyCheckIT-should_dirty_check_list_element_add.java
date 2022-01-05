@Test public void should_dirty_check_list_element_add() throws Exception {
  bean.getFriends().add("qux");
  bean.getFriends().addAll(asList("qux","qux"));
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  List<String> friends=row.getList("friends",String.class);
  assertThat(friends).hasSize(5);
  assertThat(friends.get(2)).isEqualTo("qux");
  assertThat(friends.get(3)).isEqualTo("qux");
  assertThat(friends.get(4)).isEqualTo("qux");
}
