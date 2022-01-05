@Test public void should_validate_options_for_update() throws Exception {
  CompleteBean entity=new CompleteBean();
  optionsValidator.validateOptionsForUpdate(entity,entityMetaMap,withTtl(10));
  verify(entityValidator).validateNotClusteredCounter(entity,entityMetaMap);
}
