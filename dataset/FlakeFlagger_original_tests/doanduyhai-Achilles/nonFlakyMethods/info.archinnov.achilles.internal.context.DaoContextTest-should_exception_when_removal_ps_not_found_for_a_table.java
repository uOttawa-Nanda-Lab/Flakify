@Test public void should_exception_when_removal_ps_not_found_for_a_table() throws Exception {
  when(removePSs.get(CompleteBean.class)).thenReturn(of("some_table",ps));
  when(context.getConsistencyLevel()).thenReturn(fromNullable(EACH_QUORUM));
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot find prepared statement for deletion for table 'table'");
  daoContext.bindForRemoval(context,"table");
}
