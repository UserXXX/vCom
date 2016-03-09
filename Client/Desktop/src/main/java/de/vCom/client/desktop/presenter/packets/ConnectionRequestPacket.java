/**
 * vCom is a chat service consisting of client and server application that encrypts every message send and thus allows a secure and for third parties not readable communication.
 * Copyright (C) 2016 Kai Brandenbusch, Björn Lange, Christian Langer, Daniel Theis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.vCom.client.desktop.presenter.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Initial package that requests to open a connection. Send after a secure connection has been established.
 * @author Björn Lange
 */
public class ConnectionRequestPacket extends Packet {
	/**
	 * Protocol identifier for this packet.
	 */
	public static final int PROTOCOL_IDENTIFIER = 256;
	
	// TODO: UserIdentifier attribute
	
	private byte[] serverPassword;
	
	/**
	 * Default constructor.
	 */
	public ConnectionRequestPacket() { }
	
	// TODO: Packet constructor
	
	// TODO: UserIdentifier getter/setter
	
	@Override
	public void readFrom(DataInputStream input) throws IOException {
		throw new UnsupportedOperationException("Client is not allowed to receive a ConnectionRequestPackage!");
	}

	/**
	 * Gets the server password to send to the server.
	 * @return The server password to send.
	 */
	public byte[] getServerPassword() {
		return serverPassword;
	}

	/**
	 * Sets the server password to send.
	 * @param serverPassword The server password to send.
	 */
	public void setServerPassword(byte[] serverPassword) {
		this.serverPassword = serverPassword;
	}

	@Override
	public void writeTo(DataOutputStream output) throws IOException {
		output.writeInt(PROTOCOL_IDENTIFIER);
		// TODO: write UserIdentifier
		output.writeInt(serverPassword.length);
		output.write(serverPassword);
	}
}
