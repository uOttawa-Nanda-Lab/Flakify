@Test public void should_not_dirty_check_list_element_sub_list_remove() throws Exception {
  bean.getFriends().subList(0,1).remove(0);
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  List<String> friends=row.getList("friends",String.class);
  assertThat(friends).hasSize(2);
  assertThat(friends).contains("foo","bar");
}
