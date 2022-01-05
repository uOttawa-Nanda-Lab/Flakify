@Test public void testResolvePrimitiveClassName() throws Exception {
  assertThat(resolvePrimitiveClassName("boolean") == boolean.class,is(true));
  assertThat(resolvePrimitiveClassName("byte") == byte.class,is(true));
  assertThat(resolvePrimitiveClassName("char") == char.class,is(true));
  assertThat(resolvePrimitiveClassName("double") == double.class,is(true));
  assertThat(resolvePrimitiveClassName("float") == float.class,is(true));
  assertThat(resolvePrimitiveClassName("int") == int.class,is(true));
  assertThat(resolvePrimitiveClassName("long") == long.class,is(true));
  assertThat(resolvePrimitiveClassName("short") == short.class,is(true));
  assertThat(resolvePrimitiveClassName("[Z") == boolean[].class,is(true));
  assertThat(resolvePrimitiveClassName("[B") == byte[].class,is(true));
  assertThat(resolvePrimitiveClassName("[C") == char[].class,is(true));
  assertThat(resolvePrimitiveClassName("[D") == double[].class,is(true));
  assertThat(resolvePrimitiveClassName("[F") == float[].class,is(true));
  assertThat(resolvePrimitiveClassName("[I") == int[].class,is(true));
  assertThat(resolvePrimitiveClassName("[J") == long[].class,is(true));
  assertThat(resolvePrimitiveClassName("[S") == short[].class,is(true));
}
