@Test public void should_pass_when_not_ignored_fields_are_equal(){
  Jedi actual=new Jedi("Yoda","Green");
  Jedi other=new Jedi("Yoda","Blue");
  objects.assertIsEqualToIgnoringGivenFields(someInfo(),actual,other,"lightSaberColor");
}
