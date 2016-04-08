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
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package de.vCom.client.desktop.presenter.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

import de.vCom.client.desktop.model.UserIdentifier;

/**
 * Transfers a message from a single sender to the client.
 * @author Björn Lange
 */
public class UnicastMessageSCPacket extends Packet {
	/**
	 * Protocol identifier for this packet.
	 */
	public static final int PROTOCOL_IDENTIFIER = 264;
	
	private UserIdentifier senderIdentifier;
	private boolean read;
	private Date mailingDate;
	private boolean isTextMessage;
	private byte[] data;
	
	/**
	 * Gets the {@code UserIdentifier} of the message sender.
	 * @return The {@code UserIdentifier} of the message sender.
	 */
	public UserIdentifier getSenderIdentifier() {
		return senderIdentifier;
	}
	
	/**
	 * Gets whether the message has already been read on any device.
	 * @return Whether the message has already been read on any device.
	 */
	public boolean isRead() {
		return read;
	}
	
	/**
	 * Gets the mailing date of the message. This is equal to the mailing date from the
	 * {@code UnicastMessageCSPacket}.
	 * @return The mailing date of the message.
	 */
	public Date getMailingDate() {
		return mailingDate;
	}
	
	/**
	 * Gets whether the message is a text message. If this is {@code false}, the {@code data}
	 * array contains image data.
	 * @return Whether the message is a text message.
	 */
	public boolean isTextMessage() {
		return isTextMessage;
	}
	
	/**
	 * Gets the data that was wrapped in this packet.
	 * @return The data that was wrapped in this packet.
	 */
	public byte[] getData() {
		return data;
	}
	
	/**
	 * Creates a new {@code UnicastMessageSCPacket}.
	 */
	public UnicastMessageSCPacket() { }

	@Override
	public void readFrom(DataInputStream input) throws IOException {
		senderIdentifier = readUserIdentifier(input);
		read = input.readBoolean();
		mailingDate = new Date(input.readLong());
		isTextMessage = input.readBoolean();
		data = readByteArray(input);
	}

	@Override
	public void writeTo(DataOutputStream output) throws IOException {
		throw new UnsupportedOperationException(
				"Client is not allowed to send a UnicastMessageSCPacket!");
	}
}
