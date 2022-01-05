@Test public void defaults() throws Exception {
  assertEquals(this.sanitizer.sanitize("password","secret"),"******");
  assertEquals(this.sanitizer.sanitize("my-password","secret"),"******");
  assertEquals(this.sanitizer.sanitize("my-OTHER.paSSword","secret"),"******");
  assertEquals(this.sanitizer.sanitize("somesecret","secret"),"******");
  assertEquals(this.sanitizer.sanitize("somekey","secret"),"******");
  assertEquals(this.sanitizer.sanitize("find","secret"),"secret");
}
