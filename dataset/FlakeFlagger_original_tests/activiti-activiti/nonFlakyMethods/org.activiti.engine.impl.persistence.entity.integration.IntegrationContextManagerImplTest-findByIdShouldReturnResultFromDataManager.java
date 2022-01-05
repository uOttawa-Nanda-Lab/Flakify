@Test public void findByIdShouldReturnResultFromDataManager(){
  IntegrationContextEntity entity=mock(IntegrationContextEntity.class);
  given(dataManager.findById("id")).willReturn(entity);
  IntegrationContextEntity retrievedResult=manager.findById("id");
  assertThat(retrievedResult).isEqualTo(entity);
}
