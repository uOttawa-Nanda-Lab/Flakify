@Test public void isExpired(){
  when(this.entry.getLastAccessedTime()).thenReturn(null);
  Assert.assertFalse(this.bean.isExpired());
  long now=System.currentTimeMillis();
  when(this.entry.getLastAccessedTime()).thenReturn(new Date(now));
  Assert.assertFalse(this.bean.isExpired());
  when(this.entry.getLastAccessedTime()).thenReturn(new Date(now - this.timeout.convert(TimeUnit.MILLISECONDS) - 1));
  Assert.assertTrue(this.bean.isExpired());
}
