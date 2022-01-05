@Test public void doesNotTriggerDefaultRepositoryDetectionIfCustomized(){
  prepareApplicationContext(CustomizedConfiguration.class);
  assertNotNull(this.context.getBean(CityMongoDbRepository.class));
}
