/*
 * Copyright (c) 2014, 2017 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package org.glassfish.tyrus.tests.servlet.autobahn;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.core.TyrusWebSocketEngine;
import org.glassfish.tyrus.server.Server;
import org.glassfish.tyrus.test.tools.TestContainer;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 */
public class StandaloneServer extends TestContainer {

    public StandaloneServer() {
        setContextPath("/autobahn");
        final HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put(TyrusWebSocketEngine.INCOMING_BUFFER_SIZE, 17000000);
        setServerProperties(stringObjectHashMap);
    }

    @Ignore("Manual testing.")
    @Test
    public void testServer() throws IOException, DeploymentException {
        final Server server = startServer(AutobahnApplicationConfig.class);

        try {
            System.in.read();
        } finally {
            stopServer(server);
        }
    }
}
