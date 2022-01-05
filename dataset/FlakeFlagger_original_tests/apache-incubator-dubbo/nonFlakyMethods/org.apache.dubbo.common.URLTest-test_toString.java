@Test public void test_toString() throws Exception {
  URL url1=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan");
  assertThat(url1.toString(),anyOf(equalTo("dubbo://10.20.130.230:20880/context/path?version=1.0.0&application=morgan"),equalTo("dubbo://10.20.130.230:20880/context/path?application=morgan&version=1.0.0")));
}
