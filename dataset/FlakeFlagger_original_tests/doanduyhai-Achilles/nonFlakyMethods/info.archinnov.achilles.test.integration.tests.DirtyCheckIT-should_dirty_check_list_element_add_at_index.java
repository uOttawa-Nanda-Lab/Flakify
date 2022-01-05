@Test public void should_dirty_check_list_element_add_at_index() throws Exception {
  exception.expect(UnsupportedOperationException.class);
  exception.expectMessage("Append, Prepend, Remove, RemoveAll and SetValueAtIndex are the only supported operations for CQL3 lists");
  bean.getFriends().add(1,"qux");
}
