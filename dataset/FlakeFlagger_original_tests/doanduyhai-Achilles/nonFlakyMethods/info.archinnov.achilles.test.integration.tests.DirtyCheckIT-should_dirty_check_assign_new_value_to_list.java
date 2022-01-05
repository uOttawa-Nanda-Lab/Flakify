@Test public void should_dirty_check_assign_new_value_to_list() throws Exception {
  bean.setFriends(asList("qux","tux"));
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  List<String> friends=row.getList("friends",String.class);
  assertThat(friends).containsOnly("qux","tux");
}
