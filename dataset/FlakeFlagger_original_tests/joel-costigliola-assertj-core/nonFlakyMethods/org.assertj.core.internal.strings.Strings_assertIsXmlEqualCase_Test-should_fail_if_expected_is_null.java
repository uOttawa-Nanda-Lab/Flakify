@Test public void should_fail_if_expected_is_null(){
  thrown.expectNullPointerException("The char sequence to look for should not be null");
  strings.assertXmlEqualsTo(someInfo(),"<jedi>yoda</jedi>",null);
}
