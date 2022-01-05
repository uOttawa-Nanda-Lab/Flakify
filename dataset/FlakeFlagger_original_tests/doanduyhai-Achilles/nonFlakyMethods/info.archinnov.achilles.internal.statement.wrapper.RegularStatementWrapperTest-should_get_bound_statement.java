@Test public void should_get_bound_statement() throws Exception {
  wrapper=new RegularStatementWrapper(CompleteBean.class,rs,new Object[]{1},ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);
  final RegularStatement expectedRs=wrapper.getStatement();
  assertThat(expectedRs).isSameAs(rs);
}
