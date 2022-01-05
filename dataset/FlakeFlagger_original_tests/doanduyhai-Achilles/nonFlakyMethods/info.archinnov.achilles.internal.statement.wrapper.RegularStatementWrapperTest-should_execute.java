@Test public void should_execute() throws Exception {
  wrapper=new RegularStatementWrapper(CompleteBean.class,rs,new Object[]{1},ONE,NO_LISTENER,Optional.fromNullable(LOCAL_SERIAL));
  wrapper.execute(session);
  verify(session).execute(rs);
  verify(rs).setConsistencyLevel(ONE);
  verify(rs).setSerialConsistencyLevel(LOCAL_SERIAL);
}
