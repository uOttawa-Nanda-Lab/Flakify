@Test public void assertPersist(){
  zkRegCenter.persist("/test","test_update");
  zkRegCenter.persist("/persist/new","new_value");
  assertThat(zkRegCenter.get("/test"),is("test_update"));
  assertThat(zkRegCenter.get("/persist/new"),is("new_value"));
}
