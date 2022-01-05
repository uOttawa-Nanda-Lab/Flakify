@Test public void testNoRepositoryConfiguration() throws Exception {
  prepareApplicationContext(EmptyConfiguration.class);
  Mongo mongo=this.context.getBean(Mongo.class);
  assertThat(mongo,is(instanceOf(MongoClient.class)));
}
