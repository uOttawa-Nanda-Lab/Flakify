@Test public void getManagedEntityClassShouldReturnIntegrationContextEntityImpl(){
  Class<? extends IntegrationContextEntity> managedEntityClass=manager.getManagedEntityClass();
  assertThat(managedEntityClass).isEqualTo(IntegrationContextEntityImpl.class);
}
