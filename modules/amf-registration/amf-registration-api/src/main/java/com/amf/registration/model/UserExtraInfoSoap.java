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

package com.amf.registration.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserExtraInfoSoap implements Serializable {

	public static UserExtraInfoSoap toSoapModel(UserExtraInfo model) {
		UserExtraInfoSoap soapModel = new UserExtraInfoSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setExtraInfoId(model.getExtraInfoId());
		soapModel.setUserId(model.getUserId());
		soapModel.setAddress(model.getAddress());
		soapModel.setAddress2(model.getAddress2());
		soapModel.setCity(model.getCity());
		soapModel.setState(model.getState());
		soapModel.setZip(model.getZip());

		return soapModel;
	}

	public static UserExtraInfoSoap[] toSoapModels(UserExtraInfo[] models) {
		UserExtraInfoSoap[] soapModels = new UserExtraInfoSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserExtraInfoSoap[][] toSoapModels(UserExtraInfo[][] models) {
		UserExtraInfoSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserExtraInfoSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserExtraInfoSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserExtraInfoSoap[] toSoapModels(List<UserExtraInfo> models) {
		List<UserExtraInfoSoap> soapModels = new ArrayList<UserExtraInfoSoap>(
			models.size());

		for (UserExtraInfo model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserExtraInfoSoap[soapModels.size()]);
	}

	public UserExtraInfoSoap() {
	}

	public long getPrimaryKey() {
		return _extraInfoId;
	}

	public void setPrimaryKey(long pk) {
		setExtraInfoId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getExtraInfoId() {
		return _extraInfoId;
	}

	public void setExtraInfoId(long extraInfoId) {
		_extraInfoId = extraInfoId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getAddress2() {
		return _address2;
	}

	public void setAddress2(String address2) {
		_address2 = address2;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}

	public int getState() {
		return _state;
	}

	public void setState(int state) {
		_state = state;
	}

	public String getZip() {
		return _zip;
	}

	public void setZip(String zip) {
		_zip = zip;
	}

	private String _uuid;
	private long _extraInfoId;
	private long _userId;
	private String _address;
	private String _address2;
	private String _city;
	private int _state;
	private String _zip;

}