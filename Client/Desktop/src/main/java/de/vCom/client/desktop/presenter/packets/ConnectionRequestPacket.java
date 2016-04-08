/**
 * vCom is a chat service consisting of client and server application that encrypts every message
 * send and thus allows a secure and for third parties not readable communication.
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

import de.vCom.client.desktop.model.UserIdentifier;

/**
 * Initial package that requests to open a connection. Send after a secure connection has been
 * established.
 * @author Björn Lange
 */
public class ConnectionRequestPacket extends Packet {
	/**
	 * Protocol identifier for this packet.
	 */
	public static final int PROTOCOL_IDENTIFIER = 256;
	
	private UserIdentifier userIdentifier;
	private int deviceIdentifier;
	private byte[] serverPassword;

	/**
	 * Gets the identifier of the user to log in.
	 * @return The identifier of the user to log in.
	 */
	public UserIdentifier getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * Sets the identifier of the user to log in.
	 * @param userIdentifier The identifier of the user to log in.
	 */
	public void setUserIdentifier(UserIdentifier userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	/**
	 * Gets the identifier of the device to log in.
	 * @return The identifier of the device to log in.
	 */
	public int getDeviceIdentifier() {
		return deviceIdentifier;
	}

	/**
	 * Sets the identifier of the device to log in.
	 * @param deviceIdentifier The identifier of the device to log in.
	 */
	public void setDeviceIdentifier(int deviceIdentifier) {
		this.deviceIdentifier = deviceIdentifier;
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
	
	/**
	 * Default constructor.
	 */
	public ConnectionRequestPacket() { }
	
	/**
	 * Creates a new ConnectionRequestPacket.
	 * @param userIdentifier The user identifier to log in with.
	 * @param deviceIdentifier The device identifier to log in with.
	 * @param serverPassword The password for the server.
	 */
	public ConnectionRequestPacket(UserIdentifier userIdentifier, int deviceIdentifier,
			byte[] serverPassword) {
		super();
		this.userIdentifier = userIdentifier;
		this.deviceIdentifier = deviceIdentifier;
		this.serverPassword = serverPassword;
	}

	@Override
	public void readFrom(DataInputStream input) throws IOException {
		throw new UnsupportedOperationException(
				"Client is not allowed to receive a ConnectionRequestPacket!");
	}

	@Override
	public void writeTo(DataOutputStream output) throws IOException {
		output.writeInt(PROTOCOL_IDENTIFIER);
		writeUserIdentifier(output, userIdentifier);
		output.writeInt(deviceIdentifier);
		writeByteArray(output, serverPassword);
	}
}
