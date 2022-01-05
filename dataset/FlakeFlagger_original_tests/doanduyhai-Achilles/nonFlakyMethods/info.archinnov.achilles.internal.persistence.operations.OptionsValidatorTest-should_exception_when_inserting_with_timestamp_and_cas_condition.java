@Test public void should_exception_when_inserting_with_timestamp_and_cas_condition() throws Exception {
  CompleteBean entity=new CompleteBean();
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot provide custom timestamp for CAS insert operations");
  optionsValidator.validateOptionsForInsert(entity,entityMetaMap,ifNotExists().withTimestamp(100L));
}
