@Test public void doesNotTriggerDefaultRepositoryDetectionIfCustomized(){
  initContext(CustomizedConfiguration.class);
  assertThat(this.context.getBean(CitySolrRepository.class),notNullValue());
}
