@Test public void should_dirty_check_list_element_prepend() throws Exception {
  bean.getFriends().add(0,"one");
  bean.getFriends().addAll(0,asList("two","three"));
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  List<String> friends=row.getList("friends",String.class);
  assertThat(friends).hasSize(5);
  assertThat(friends.get(0)).isEqualTo("three");
  assertThat(friends.get(1)).isEqualTo("two");
  assertThat(friends.get(2)).isEqualTo("one");
}
