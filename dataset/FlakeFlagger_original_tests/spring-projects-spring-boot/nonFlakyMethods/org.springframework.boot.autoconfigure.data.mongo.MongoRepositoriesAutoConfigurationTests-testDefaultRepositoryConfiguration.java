@Test public void testDefaultRepositoryConfiguration() throws Exception {
  prepareApplicationContext(TestConfiguration.class);
  assertNotNull(this.context.getBean(CityRepository.class));
  Mongo mongo=this.context.getBean(Mongo.class);
  assertThat(mongo,is(instanceOf(MongoClient.class)));
}
