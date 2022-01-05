@Test public void assertLoad(){
  LiteJobConfiguration expected=LiteJobConfiguration.newBuilder(null).build();
  when(configService.load(true)).thenReturn(expected);
  assertThat(liteJobFacade.loadJobRootConfiguration(true),is(expected));
}
