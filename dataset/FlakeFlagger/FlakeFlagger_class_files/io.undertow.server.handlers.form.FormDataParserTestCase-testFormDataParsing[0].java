package io.undertow.server.handlers.form;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class FormDataParserTestCase {
@Test public void testFormDataParsing() throws Exception {
  runTest(new BasicNameValuePair("name","A Value"));
  runTest(new BasicNameValuePair("name","A Value"),new BasicNameValuePair("A/name/with_special*chars","A $ value&& with=SomeCharacters"));
}

}