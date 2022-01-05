@Test public void testExclusive(){
  int lower=0;
  ParameterValidator validator=new DoubleRangeValidatorBuilder().lowerBoundExclusive(lower).build();
  assertFalse(isValid(validator,new ModelNode(0)));
  assertTrue(isValid(validator,new ModelNode(0.1)));
  assertTrue(isValid(validator,new ModelNode(Double.MAX_VALUE)));
  int upper=1;
  validator=new DoubleRangeValidatorBuilder().upperBoundExclusive(upper).build();
  assertTrue(isValid(validator,new ModelNode(Double.MIN_VALUE)));
  assertTrue(isValid(validator,new ModelNode(0.99)));
  assertFalse(isValid(validator,new ModelNode(1)));
}
