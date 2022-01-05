@Test public void should_exception_when_updating_with_timestamp_and_cas_condition() throws Exception {
  CompleteBean entity=new CompleteBean();
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot provide custom timestamp for CAS update operations");
  optionsValidator.validateOptionsForUpdate(entity,entityMetaMap,ifConditions(new Options.CASCondition("name","John")).withTimestamp(100L));
}
