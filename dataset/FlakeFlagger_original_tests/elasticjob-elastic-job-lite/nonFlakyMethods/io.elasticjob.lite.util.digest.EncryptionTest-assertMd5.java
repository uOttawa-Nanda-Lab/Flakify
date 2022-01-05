@Test public void assertMd5(){
  assertThat(Encryption.md5("test"),is("98f6bcd4621d373cade4e832627b4f6"));
}
