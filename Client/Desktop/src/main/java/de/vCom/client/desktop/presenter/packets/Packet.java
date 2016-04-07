/**
 * vCom is a chat service consisting of client and server application that encrypts every message
 * send and thus allows a secure and for third parties not readable communication.
 * Copyright (C) 2016 Kai Brandenbusch, Björn Lange, Christian Langer, Daniel Theis
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If
 * not, see <http://www.gnu.org/licenses/>.
 */
package de.vCom.client.desktop.presenter.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import de.vCom.client.desktop.model.User;
import de.vCom.client.desktop.model.UserIdentifier;
import de.vCom.client.desktop.model.UserList;

/**
 * Abstract parent class for all client packets.
 * @author Björn Lange
 */
public abstract class Packet {
	/**
	 * Reads a list of {@code UserIsentifier}s from the given stream.
	 * @param input The stream to read from.
	 * @return The read list.
	 * @throws NullPointerException If {@code input} is {@code null}.
	 * @throws IOException If an I/O error occurs.
	 */
	protected List<UserIdentifier> readUserIdentifierList(DataInputStream input)
			throws IOException {
		if (input == null) {
			throw new NullPointerException();
		}
		
		List<UserIdentifier> list = new LinkedList<>();
		int length = input.readInt();
		for (int i = 0; i < length; i++) {
			int idLength = input.readInt();
			byte[] idData = new byte[idLength];
			input.readFully(idData);
			String strIdentifier = new String(idData, StandardCharsets.UTF_8);
			UserIdentifier identifier = new UserIdentifier(strIdentifier);
			list.add(identifier);
		}
		
		return list;
	}
	
	/**
	 * Writes a list of {@code UserIdentifier}s to the given stream. The source for the identifiers
	 * is a list of {@code User} objects. Every objects identifier is written.
	 * @param output The stream to write to.
	 * @param list The list to write.
	 * @throws NullPointerException If output or list is null.
	 * @throws IOException If an I/O error occurs.
	 */
	protected void writeUserIdentifierList(DataOutputStream output, UserList list)
			throws IOException {
		if (output == null || list == null) {
			throw new NullPointerException();
		}
		
		output.writeInt(list.size());
		for (User user : list) {
			String strIdentifier = user.getUserIdentifier().getStringRepresentation();
			byte[] idData = strIdentifier.getBytes(StandardCharsets.UTF_8);
			output.writeInt(idData.length);
			output.write(idData);
		}
	}
	
	/**
	 * Reads the packet body from the given input stream. The header int has already been read.
	 * @param input The input stream to read from.
	 * @throws IOException If an IO exception occurs.
	 */
	public abstract void readFrom(DataInputStream input) throws IOException;
	
	/**
	 * Writes the whole packet to the given output stream.
	 * @param output Output stream to write to.
	 * @throws IOException If an IO exception occurs.
	 */
	public abstract void writeTo(DataOutputStream output) throws IOException;
}
