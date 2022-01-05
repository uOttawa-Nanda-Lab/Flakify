@Test(expected=NoSuchBeanDefinitionException.class) public void autoConfigurationShouldNotKickInEvenIfManualConfigDidNotCreateAnyRepositories(){
  initContext(SortOfInvalidCustomConfiguration.class);
  this.context.getBean(CityRepository.class);
}
