@Test public void getDataManagerShouldReturnIntegrationContextDataManager(){
  DataManager<IntegrationContextEntity> retrievedDataManager=manager.getDataManager();
  assertThat(retrievedDataManager).isEqualTo(dataManager);
}
