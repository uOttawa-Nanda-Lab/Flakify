@Test public void should_fail_when_selected_field_does_not_exist(){
  thrown.expect(IntrospectionError.class,"Unable to obtain the value of <'age'> field/property from <Yoda the Jedi>, expecting a public field or getter");
  Jedi actual=new Jedi("Yoda","Green");
  Jedi other=new Jedi("Yoda","Blue");
  objects.assertIsEqualToComparingOnlyGivenFields(someInfo(),actual,other,"age");
}
