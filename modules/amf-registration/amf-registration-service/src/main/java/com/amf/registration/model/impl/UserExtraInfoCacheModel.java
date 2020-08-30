/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.amf.registration.model.impl;

import com.amf.registration.model.UserExtraInfo;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing UserExtraInfo in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserExtraInfoCacheModel
	implements CacheModel<UserExtraInfo>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserExtraInfoCacheModel)) {
			return false;
		}

		UserExtraInfoCacheModel userExtraInfoCacheModel =
			(UserExtraInfoCacheModel)obj;

		if (extraInfoId == userExtraInfoCacheModel.extraInfoId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, extraInfoId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", extraInfoId=");
		sb.append(extraInfoId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", address=");
		sb.append(address);
		sb.append(", address2=");
		sb.append(address2);
		sb.append(", city=");
		sb.append(city);
		sb.append(", state=");
		sb.append(state);
		sb.append(", zip=");
		sb.append(zip);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserExtraInfo toEntityModel() {
		UserExtraInfoImpl userExtraInfoImpl = new UserExtraInfoImpl();

		if (uuid == null) {
			userExtraInfoImpl.setUuid("");
		}
		else {
			userExtraInfoImpl.setUuid(uuid);
		}

		userExtraInfoImpl.setExtraInfoId(extraInfoId);
		userExtraInfoImpl.setUserId(userId);

		if (address == null) {
			userExtraInfoImpl.setAddress("");
		}
		else {
			userExtraInfoImpl.setAddress(address);
		}

		if (address2 == null) {
			userExtraInfoImpl.setAddress2("");
		}
		else {
			userExtraInfoImpl.setAddress2(address2);
		}

		if (city == null) {
			userExtraInfoImpl.setCity("");
		}
		else {
			userExtraInfoImpl.setCity(city);
		}

		userExtraInfoImpl.setState(state);

		if (zip == null) {
			userExtraInfoImpl.setZip("");
		}
		else {
			userExtraInfoImpl.setZip(zip);
		}

		userExtraInfoImpl.resetOriginalValues();

		return userExtraInfoImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		extraInfoId = objectInput.readLong();

		userId = objectInput.readLong();
		address = objectInput.readUTF();
		address2 = objectInput.readUTF();
		city = objectInput.readUTF();

		state = objectInput.readInt();
		zip = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(extraInfoId);

		objectOutput.writeLong(userId);

		if (address == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (address2 == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(address2);
		}

		if (city == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(city);
		}

		objectOutput.writeInt(state);

		if (zip == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(zip);
		}
	}

	public String uuid;
	public long extraInfoId;
	public long userId;
	public String address;
	public String address2;
	public String city;
	public int state;
	public String zip;

}