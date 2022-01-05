@Test public void should_fail_when_asked_to_ignore_non_existent_fields(){
  thrown.expect(RuntimeException.class,"Fields to ignore <[field1, field2]> not defined for type <org.assertj.core.test.Jedi>");
  AssertionInfo info=someInfo();
  Jedi actual=new Jedi("Yoda","Green");
  Jedi other=new Jedi("Yoda","Green");
  objects.assertIsEqualToIgnoringGivenFields(info,actual,other,"field1","field2");
}
