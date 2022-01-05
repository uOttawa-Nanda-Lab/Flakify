@Test public void should_not_dirty_check_list_element_list_iterator_remove() throws Exception {
  Iterator<String> iter=bean.getFriends().listIterator();
  iter.next();
  iter.remove();
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  List<String> friends=row.getList("friends",String.class);
  assertThat(friends).hasSize(2);
  assertThat(friends).contains("foo","bar");
}
