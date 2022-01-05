@Test public void createShouldReturnANewInstanceOfIntegrationContextEntityImpl(){
  IntegrationContextEntity entity=manager.create();
  assertThat(entity).isInstanceOf(IntegrationContextEntityImpl.class);
}
