/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.clustering.jgroups;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Collections;

import org.jboss.as.network.ManagedServerSocketFactory;
import org.jboss.as.network.ManagedSocketFactory;
import org.jboss.as.network.SocketBinding;
import org.jboss.as.network.SocketBindingManager;
import org.jgroups.util.SocketFactory;
import org.junit.After;
import org.junit.Test;

/**
 * @author Paul Ferraro
 */
public class ManagedSocketFactoryTest {

    private SocketBindingManager manager = mock(SocketBindingManager.class);

    private SocketFactory subject = new org.jboss.as.clustering.jgroups.ManagedSocketFactory(this.manager, Collections.singletonMap("known-service", new SocketBinding("binding", 0, false, null, 0, null, this.manager, Collections.emptyList())));

    private void createSocket(String serviceName, String bindingName) throws IOException {
		ManagedSocketFactory factory = mock(ManagedSocketFactory.class);
		Socket socket1 = mock(Socket.class);
		Socket socket2 = mock(Socket.class);
		Socket socket3 = mock(Socket.class);
		Socket socket4 = mock(Socket.class);
		Socket socket5 = mock(Socket.class);
		InetAddress localhost = InetAddress.getLocalHost();
		when(this.manager.getSocketFactory()).thenReturn(factory);
		when(factory.createSocket(bindingName)).thenReturn(socket1);
		when(factory.createSocket(bindingName, localhost, 1)).thenReturn(socket2);
		when(factory.createSocket(bindingName, "host", 1)).thenReturn(socket3);
		when(factory.createSocket(bindingName, localhost, 1, localhost, 2)).thenReturn(socket4);
		when(factory.createSocket(bindingName, "host", 1, localhost, 2)).thenReturn(socket5);
		Socket result1 = this.subject.createSocket(serviceName);
		Socket result2 = this.subject.createSocket(serviceName, localhost, 1);
		Socket result3 = this.subject.createSocket(serviceName, "host", 1);
		Socket result4 = this.subject.createSocket(serviceName, localhost, 1, localhost, 2);
		Socket result5 = this.subject.createSocket(serviceName, "host", 1, localhost, 2);
		assertSame(socket1, result1);
		assertSame(socket2, result2);
		assertSame(socket3, result3);
		assertSame(socket4, result4);
		assertSame(socket5, result5);
	}
}
