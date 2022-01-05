@Test public void should_dirty_check_list_element_set() throws Exception {
  bean.getFriends().set(1,"qux");
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  List<String> friends=row.getList("friends",String.class);
  assertThat(friends).hasSize(2);
  assertThat(friends.get(1)).isEqualTo("qux");
}
