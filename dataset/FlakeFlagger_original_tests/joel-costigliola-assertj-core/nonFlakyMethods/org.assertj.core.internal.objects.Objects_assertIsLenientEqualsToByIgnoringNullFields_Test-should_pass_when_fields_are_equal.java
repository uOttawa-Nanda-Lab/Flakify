@Test public void should_pass_when_fields_are_equal(){
  Jedi actual=new Jedi("Yoda","Green");
  Jedi other=new Jedi("Yoda","Green");
  objects.assertIsLenientEqualsToIgnoringNullFields(someInfo(),actual,other);
}
