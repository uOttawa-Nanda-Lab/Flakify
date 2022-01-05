@Test public void should_dirty_check_list_element_clear_then_append() throws Exception {
  bean.getFriends().clear();
  bean.getFriends().add("qux");
  manager.update(bean);
  Row row=session.execute("select friends from CompleteBean where id=" + bean.getId()).one();
  assertThat(row.getList("friends",String.class)).containsExactly("qux");
}
