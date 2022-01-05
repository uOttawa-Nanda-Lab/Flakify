@Test public void assertGetFromServer(){
  assertThat(zkRegCenter.get("/test"),is("test"));
  assertThat(zkRegCenter.get("/test/deep/nested"),is("deepNested"));
}
