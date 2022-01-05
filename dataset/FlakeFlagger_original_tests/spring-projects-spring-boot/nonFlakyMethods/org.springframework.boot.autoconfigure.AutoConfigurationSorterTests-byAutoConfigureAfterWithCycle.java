@Test public void byAutoConfigureAfterWithCycle() throws Exception {
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("AutoConfigure cycle detected");
  this.sorter.getInPriorityOrder(Arrays.asList(A,B,C,D));
}
