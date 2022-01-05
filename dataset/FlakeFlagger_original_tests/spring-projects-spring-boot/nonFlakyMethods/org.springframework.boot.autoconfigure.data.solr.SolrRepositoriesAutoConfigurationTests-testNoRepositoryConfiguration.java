@Test public void testNoRepositoryConfiguration(){
  initContext(EmptyConfiguration.class);
  assertThat(this.context.getBean(SolrServer.class),instanceOf(HttpSolrServer.class));
}
