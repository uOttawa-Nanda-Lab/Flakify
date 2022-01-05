@Test public void isCloseable(){
  when(this.entry.totalUsage()).thenReturn(1);
  Assert.assertFalse(this.group.isCloseable());
  when(this.entry.totalUsage()).thenReturn(0);
  Assert.assertTrue(this.group.isCloseable());
}
