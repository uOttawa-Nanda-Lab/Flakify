@Test public void testInvalidParse() throws Exception {
  assertInvalid("");
  assertInvalid("/");
  assertInvalid("/");
  assertInvalid("text");
  assertInvalid("text/");
  assertInvalid("te<t/plain");
  assertInvalid("text/pl@in");
  assertInvalid("text/plain;");
  assertInvalid("text/plain; ");
  assertInvalid("text/plain; a");
  assertInvalid("text/plain; a=");
  assertInvalid("text/plain; a=@");
  assertInvalid("text/plain; a=\"@");
  assertInvalid("text/plain; a=1;");
  assertInvalid("text/plain; a=1; ");
  assertInvalid("text/plain; a=1; b");
  assertInvalid("text/plain; a=1; b=");
  assertInvalid("text/plain; a=\u2025");
  assertInvalid(" text/plain");
  assertInvalid("te xt/plain");
  assertInvalid("text /plain");
  assertInvalid("text/ plain");
  assertInvalid("text/pl ain");
  assertInvalid("text/plain ");
  assertInvalid("text/plain ; a=1");
}
