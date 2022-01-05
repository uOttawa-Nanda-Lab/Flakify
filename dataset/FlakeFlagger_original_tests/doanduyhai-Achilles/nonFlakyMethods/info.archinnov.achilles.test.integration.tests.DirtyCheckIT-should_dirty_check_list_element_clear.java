@Test public void should_dirty_check_list_element_clear() throws Exception {
  bean.getFriends().clear();
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  assertThat(row.isNull("friends")).isTrue();
}
