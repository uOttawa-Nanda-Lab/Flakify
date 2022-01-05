@Test(expected=IllegalArgumentException.class) public void cannotAddNegativeNumberOfErrors(){
  victim.addFoundErrors(-1);
}
