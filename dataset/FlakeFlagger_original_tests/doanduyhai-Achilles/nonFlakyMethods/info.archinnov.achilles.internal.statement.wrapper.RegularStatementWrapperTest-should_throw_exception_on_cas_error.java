@Test public void should_throw_exception_on_cas_error() throws Exception {
  final AtomicReference<CASResult> atomicCASResult=new AtomicReference<>(null);
  CASResultListener listener=new CASResultListener(){
    @Override public void onCASSuccess(){
    }
    @Override public void onCASError(    CASResult casResult){
      atomicCASResult.compareAndSet(null,casResult);
    }
  }
;
  wrapper=new RegularStatementWrapper(CompleteBean.class,rs,new Object[]{1},ONE,Optional.fromNullable(listener),NO_SERIAL_CONSISTENCY);
  wrapper.invoker=invoker;
  when(rs.getQueryString()).thenReturn("UPDATE table IF name='John' SET");
  when(session.execute(rs)).thenReturn(resultSet);
  when(resultSet.one()).thenReturn(row);
  when(row.getBool(CAS_RESULT_COLUMN)).thenReturn(false);
  when(row.getColumnDefinitions()).thenReturn(columnDefinitions);
  when(columnDefinitions.iterator().hasNext()).thenReturn(true,true,false);
  Definition col1=buildColumnDef("keyspace","table","[applied]",DataType.cboolean());
  Definition col2=buildColumnDef("keyspace","table","name",DataType.text());
  when(columnDefinitions.iterator().next()).thenReturn(col1,col2);
  when(invoker.invokeOnRowForType(row,DataType.cboolean().asJavaClass(),"[applied]")).thenReturn(false);
  when(invoker.invokeOnRowForType(row,DataType.text().asJavaClass(),"name")).thenReturn("Helen");
  wrapper.execute(session);
  verify(session).execute(rs);
  final CASResult actual=atomicCASResult.get();
  assertThat(actual).isNotNull();
  assertThat(actual.operation()).isEqualTo(UPDATE);
  assertThat(actual.currentValues()).contains(MapEntry.entry("[applied]",false),MapEntry.entry("name","Helen"));
}
