@Test public void should_notify_listener_on_cas_error() throws Exception {
  wrapper=new RegularStatementWrapper(CompleteBean.class,rs,new Object[]{1},ONE,NO_LISTENER,NO_SERIAL_CONSISTENCY);
  wrapper.invoker=invoker;
  when(rs.getQueryString()).thenReturn("INSERT INTO table IF NOT EXISTS");
  when(session.execute(rs)).thenReturn(resultSet);
  when(resultSet.one()).thenReturn(row);
  when(row.getBool(CAS_RESULT_COLUMN)).thenReturn(false);
  when(row.getColumnDefinitions()).thenReturn(columnDefinitions);
  when(columnDefinitions.iterator().hasNext()).thenReturn(true,true,false);
  Definition col1=buildColumnDef("keyspace","table","[applied]",DataType.cboolean());
  Definition col2=buildColumnDef("keyspace","table","id",DataType.bigint());
  when(columnDefinitions.iterator().next()).thenReturn(col1,col2);
  when(invoker.invokeOnRowForType(row,DataType.cboolean().asJavaClass(),"[applied]")).thenReturn(false);
  when(invoker.invokeOnRowForType(row,DataType.bigint().asJavaClass(),"id")).thenReturn(10L);
  AchillesCASException caughtEx=null;
  try {
    wrapper.execute(session);
  }
 catch (  AchillesCASException ace) {
    caughtEx=ace;
  }
  verify(session).execute(rs);
  assertThat(caughtEx).isNotNull();
  assertThat(caughtEx.operation()).isEqualTo(INSERT);
  assertThat(caughtEx.currentValues()).contains(MapEntry.entry("[applied]",false),MapEntry.entry("id",10L));
}
