@Test public void test_toFullString() throws Exception {
  URL url1=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan");
  assertThat(url1.toFullString(),anyOf(equalTo("dubbo://admin:hello1234@10.20.130.230:20880/context/path?version=1.0.0&application=morgan"),equalTo("dubbo://admin:hello1234@10.20.130.230:20880/context/path?application=morgan&version=1.0.0")));
}
