@Test public void testWithoutDatasource(){
  Map<String,DataSource> map=new HashMap<String,DataSource>();
  given(applicationContext.getBeansOfType(eq(DataSource.class),anyBoolean(),anyBoolean())).willReturn(map);
  Status status=dataSourceStatusChecker.check();
  assertThat(status.getLevel(),is(Status.Level.UNKNOWN));
}
