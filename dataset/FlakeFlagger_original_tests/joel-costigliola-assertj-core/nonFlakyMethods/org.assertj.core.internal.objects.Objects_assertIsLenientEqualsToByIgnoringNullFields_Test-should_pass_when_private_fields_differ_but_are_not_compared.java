@Test public void should_pass_when_private_fields_differ_but_are_not_compared(){
  boolean allowedToUsePrivateFields=FieldSupport.comparison().isAllowedToUsePrivateFields();
  Assertions.setAllowComparingPrivateFields(false);
  TestClassWithRandomId actual=new TestClassWithRandomId("1",1);
  TestClassWithRandomId other=new TestClassWithRandomId(null,1);
  objects.assertIsLenientEqualsToIgnoringNullFields(someInfo(),actual,other);
  Assertions.setAllowComparingPrivateFields(allowedToUsePrivateFields);
}
