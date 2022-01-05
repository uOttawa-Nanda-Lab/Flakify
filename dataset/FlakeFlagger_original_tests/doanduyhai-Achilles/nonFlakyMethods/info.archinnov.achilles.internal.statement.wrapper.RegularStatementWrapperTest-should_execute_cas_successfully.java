@Test public void should_execute_cas_successfully() throws Exception {
  final AtomicBoolean casSuccess=new AtomicBoolean(false);
  CASResultListener listener=new CASResultListener(){
    @Override public void onCASSuccess(){
      casSuccess.compareAndSet(false,true);
    }
    @Override public void onCASError(    CASResult casResult){
    }
  }
;
  when(rs.getQueryString()).thenReturn("INSERT INTO table IF NOT EXISTS");
  wrapper=new RegularStatementWrapper(CompleteBean.class,rs,new Object[]{1},ONE,Optional.fromNullable(listener),NO_SERIAL_CONSISTENCY);
  when(session.execute(rs)).thenReturn(resultSet);
  when(resultSet.one().getBool(CAS_RESULT_COLUMN)).thenReturn(true);
  wrapper.execute(session);
  verify(session).execute(rs);
  assertThat(casSuccess.get()).isTrue();
}
