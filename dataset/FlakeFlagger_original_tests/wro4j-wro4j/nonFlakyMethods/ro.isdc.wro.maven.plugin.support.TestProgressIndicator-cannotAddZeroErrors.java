@Test(expected=IllegalArgumentException.class) public void cannotAddZeroErrors(){
  victim.addFoundErrors(0);
}
