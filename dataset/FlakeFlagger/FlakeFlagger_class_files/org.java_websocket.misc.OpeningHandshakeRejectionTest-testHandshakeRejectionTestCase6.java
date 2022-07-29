/*
 * Copyright (c) 2010-2018 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package org.java_websocket.misc;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.util.Charsetfunctions;
import org.java_websocket.util.SocketUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.Scanner;

import static org.junit.Assert.fail;

public class OpeningHandshakeRejectionTest {

	private static final String additionalHandshake = "Upgrade: websocket\r\nConnection: Upgrade\r\n\r\n";
	private static int counter = 0;
	private static Thread thread;
	private static ServerSocket serverSocket;

	private static boolean debugPrintouts = false;

	private static int port;

	@BeforeClass
	public static void startServer() throws Exception {
		port = SocketUtil.getAvailablePort();
		thread = new Thread(
				new Runnable() {
					public void run() {
						try {
							serverSocket = new ServerSocket( port );
							serverSocket.setReuseAddress( true );
							while( true ) {
								Socket client = null;
								try {
									client = serverSocket.accept();
									Scanner in = new Scanner( client.getInputStream() );
									String input = in.nextLine();
									String testCase = input.split( " " )[1];
									OutputStream os = client.getOutputStream();
									if( "/0".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP/1.1 100 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/1".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP/1.0 100 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/2".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP 100 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/3".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP/1.1 200 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/4".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP 101 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/5".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP/1.1 404 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/6".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP/2.0 404 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/7".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP/1.1 500 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/8".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "GET 302 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/9".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "GET HTTP/1.1 101 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/10".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP/1.1 101 Switching Protocols\r\n" + additionalHandshake ) );
										os.flush();
									}
									if( "/11".equals( testCase ) ) {
										os.write( Charsetfunctions.asciiBytes( "HTTP/1.1 101 Websocket Connection Upgrade\r\n" + additionalHandshake ) );
										os.flush();
									}
								} catch ( IOException e ) {
									//
								}
							}
						} catch ( Exception e ) {
							fail( "There should be no exception" );
						}
					}
				} );
		thread.start();
	}

	@Test(timeout=5000) public void testHandshakeRejectionTestCase6() throws Exception{testHandshakeRejection(6);}
}
