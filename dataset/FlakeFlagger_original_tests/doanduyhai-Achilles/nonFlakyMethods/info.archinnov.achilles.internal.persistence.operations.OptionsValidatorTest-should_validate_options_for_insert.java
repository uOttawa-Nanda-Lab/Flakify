@Test public void should_validate_options_for_insert() throws Exception {
  CompleteBean entity=new CompleteBean();
  optionsValidator.validateOptionsForInsert(entity,entityMetaMap,withTtl(10));
  verify(entityValidator).validateNotClusteredCounter(entity,entityMetaMap);
}
