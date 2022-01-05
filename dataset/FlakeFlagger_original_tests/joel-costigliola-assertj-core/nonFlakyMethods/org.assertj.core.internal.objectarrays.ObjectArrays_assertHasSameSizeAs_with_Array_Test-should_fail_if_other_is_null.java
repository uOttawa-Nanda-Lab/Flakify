@Test public void should_fail_if_other_is_null(){
  thrown.expectAssertionError("Expecting an array but was:<null>");
  String[] actual=array("Solo","Leia");
  String[] other=null;
  arrays.assertHasSameSizeAs(someInfo(),actual,other);
}
