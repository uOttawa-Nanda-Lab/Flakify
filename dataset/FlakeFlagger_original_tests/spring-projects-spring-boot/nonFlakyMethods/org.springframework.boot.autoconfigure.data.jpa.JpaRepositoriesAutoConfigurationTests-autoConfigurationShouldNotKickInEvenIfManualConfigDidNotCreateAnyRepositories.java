@Test(expected=NoSuchBeanDefinitionException.class) public void autoConfigurationShouldNotKickInEvenIfManualConfigDidNotCreateAnyRepositories(){
  prepareApplicationContext(SortOfInvalidCustomConfiguration.class);
  this.context.getBean(CityRepository.class);
}
