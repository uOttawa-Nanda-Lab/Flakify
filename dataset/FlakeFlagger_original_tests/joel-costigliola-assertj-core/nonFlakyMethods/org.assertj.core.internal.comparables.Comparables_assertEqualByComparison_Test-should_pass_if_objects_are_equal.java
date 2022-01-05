@Test public void should_pass_if_objects_are_equal(){
  BigDecimal a=new BigDecimal("10.0");
  BigDecimal e=new BigDecimal("10.000");
  assertFalse(a.equals(e));
  comparables.assertEqualByComparison(someInfo(),a,e);
}
