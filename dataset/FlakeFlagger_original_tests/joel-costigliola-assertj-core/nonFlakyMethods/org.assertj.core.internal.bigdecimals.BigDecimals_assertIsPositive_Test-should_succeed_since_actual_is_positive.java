@Test public void should_succeed_since_actual_is_positive(){
  bigDecimals.assertIsPositive(someInfo(),BigDecimal.ONE);
}
