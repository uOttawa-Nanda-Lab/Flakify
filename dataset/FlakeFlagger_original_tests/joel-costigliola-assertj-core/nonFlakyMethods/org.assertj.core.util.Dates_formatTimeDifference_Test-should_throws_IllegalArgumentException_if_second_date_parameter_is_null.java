@Test public void should_throws_IllegalArgumentException_if_second_date_parameter_is_null(){
  thrown.expectIllegalArgumentException("Expecting date parameter not to be null");
  formatTimeDifference(null,new Date());
}
