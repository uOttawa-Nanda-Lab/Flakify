@Test public void should_get_bound_statement() throws Exception {
  wrapper=new BoundStatementWrapper(CompleteBean.class,bs,new Object[]{1},ConsistencyLevel.ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);
  final BoundStatement expectedBs=wrapper.getStatement();
  assertThat(expectedBs).isSameAs(bs);
}
