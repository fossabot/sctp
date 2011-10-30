/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and/or its affiliates, and individual
 * contributors as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a full listing
 * of individual contributors.
 * 
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU General Public License, v. 2.0.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License,
 * v. 2.0 along with this distribution; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
package org.mobicents.protocols.api;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * {@link Management} class manages the underlying {@link Association} and
 * {@link Server}.
 * </p>
 * <p>
 * Management should persist the state of {@link Server} and {@link Association}
 * </p>
 * 
 * @author amit bhayani
 * 
 */
public interface Management {

	/**
	 * Returns the name of this {@link Management} instance
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Start the management. No management operation can be executed unless
	 * {@link Management} is started. If {@link Server} and {@link Association}
	 * were defined previously, Management should recreate those {@link Server}
	 * and {@link Association}.
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception;

	/**
	 * Stop the management. It should persist the state of {@link Server} and
	 * {@link Associtaion}.
	 * 
	 * @throws Exception
	 */
	public void stop() throws Exception;

	/**
	 * Add new {@link Server}.
	 * 
	 * @param serverName
	 *            name of the Server. Should be unique name
	 * @param hostAddress
	 *            IP address that this server will bind to
	 * @param port
	 *            port that this server will bind to
	 * @return new Server instance
	 * @throws Exception
	 *             Exception if management not started or server name already
	 *             taken or some other server already has same ip:port
	 */
	public Server addServer(String serverName, String hostAddress, int port) throws Exception;

	/**
	 * Remove existing {@link Server}
	 * 
	 * @param serverName
	 * @throws Exception
	 *             Exception if no Server with the passed name exist or Server
	 *             is started. Before removing server, it should be stopped
	 */
	public void removeServer(String serverName) throws Exception;

	/**
	 * Start the existing Server
	 * 
	 * @param serverName
	 *            name of the Server to be started
	 * @throws Exception
	 *             Exception if no Server found for given name or Server already
	 *             started
	 */
	public void startServer(String serverName) throws Exception;

	/**
	 * Stop the Server.
	 * 
	 * @param serverName
	 *            name of the Server to be stopped
	 * @throws Exception
	 *             Exception if no Server found for given name or any of the
	 *             {@link Association} within Server still started. All the
	 *             Association's must be stopped before stopping Server
	 */
	public void stopServer(String serverName) throws Exception;

	/**
	 * Get the list of Servers configured
	 * 
	 * @return
	 */
	public List<Server> getServers();

	/**
	 * Add server Association.
	 * 
	 * @param peerAddress
	 *            the peer IP address that this association will accept
	 *            connection from
	 * @param peerPort
	 *            the peer port that this association will accept connection
	 *            from
	 * @param serverName
	 *            the Server that this association belongs to
	 * @param assocName
	 *            unique name of Association
	 * @return
	 * @throws Exception
	 */
	public Association addServerAssociation(String peerAddress, int peerPort, String serverName, String assocName) throws Exception;

	/**
	 * Add Association
	 * 
	 * @param hostAddress
	 * @param hostPort
	 * @param peerAddress
	 * @param peerPort
	 * @param assocName
	 * @return
	 * @throws Exception
	 */
	public Association addAssociation(String hostAddress, int hostPort, String peerAddress, int peerPort, String assocName) throws Exception;

	/**
	 * Remove existing Association. Association should be stopped before
	 * removing
	 * 
	 * @param assocName
	 * @throws Exception
	 */
	public void removeAssociation(String assocName) throws Exception;

	/**
	 * Get existing Association for passed name
	 * 
	 * @param assocName
	 * @return
	 * @throws Exception
	 */
	public Association getAssociation(String assocName) throws Exception;

	/**
	 * Get configured Association map with name as key and Association instance
	 * as value
	 * 
	 * @return
	 */
	public Map<String, Association> getAssociations();

	/**
	 * Start the existing Association
	 * 
	 * @param assocName
	 * @throws Exception
	 */
	public void startAssociation(String assocName) throws Exception;

	/**
	 * Stop the existing Association
	 * 
	 * @param assocName
	 * @throws Exception
	 */
	public void stopAssociation(String assocName) throws Exception;

	/**
	 * Get connection delay. If the client side {@link Association} dies due to
	 * network failure or any other reason, it should attempt to reconnect after
	 * connectDelay interval
	 * 
	 * @return
	 */
	public int getConnectDelay();

	/**
	 * Set the connection delay for client side {@link Association}
	 * 
	 * @param connectDelay
	 */
	public void setConnectDelay(int connectDelay);

	/**
	 * Number of threads configured to callback {@link AssociationListener}
	 * methods.
	 * 
	 * @return
	 */
	public int getWorkerThreads();

	/**
	 * Number of threads configured for callback {@link AssociationListener}
	 * 
	 * @param workerThreads
	 */
	public void setWorkerThreads(int workerThreads);

	/**
	 * If set as single thread, number of workers thread set has no effect and
	 * entire protocol stack runs on single thread
	 * 
	 * @return
	 */
	public boolean isSingleThread();

	/**
	 * Set protocol stack as single thread
	 * 
	 * @param singleThread
	 */
	public void setSingleThread(boolean singleThread);
}