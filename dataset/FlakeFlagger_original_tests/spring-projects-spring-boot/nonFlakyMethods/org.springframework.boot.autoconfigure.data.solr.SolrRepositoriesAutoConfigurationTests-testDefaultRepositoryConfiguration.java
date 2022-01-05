@Test public void testDefaultRepositoryConfiguration(){
  initContext(TestConfiguration.class);
  assertThat(this.context.getBean(CityRepository.class),notNullValue());
  assertThat(this.context.getBean(SolrServer.class),instanceOf(HttpSolrServer.class));
}
