@Test public void statusMustNotBeNull() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Status must not be null");
  new Health.Builder(null,null);
}
