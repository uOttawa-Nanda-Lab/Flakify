@Test public void assertUpdate(){
  zkRegCenter.persist("/update","before_update");
  zkRegCenter.update("/update","after_update");
  assertThat(zkRegCenter.getDirectly("/update"),is("after_update"));
}
