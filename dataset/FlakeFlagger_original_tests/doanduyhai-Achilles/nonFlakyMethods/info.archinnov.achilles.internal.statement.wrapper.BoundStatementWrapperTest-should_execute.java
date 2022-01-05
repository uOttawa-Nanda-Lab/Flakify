@Test public void should_execute() throws Exception {
  wrapper=new BoundStatementWrapper(CompleteBean.class,bs,new Object[]{1},ConsistencyLevel.ALL,NO_LISTENER,Optional.fromNullable(LOCAL_SERIAL));
  when(bs.preparedStatement()).thenReturn(ps);
  when(ps.getQueryString()).thenReturn("SELECT");
  wrapper.execute(session);
  verify(session).execute(bs);
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  verify(bs).setSerialConsistencyLevel(ConsistencyLevel.LOCAL_SERIAL);
}
