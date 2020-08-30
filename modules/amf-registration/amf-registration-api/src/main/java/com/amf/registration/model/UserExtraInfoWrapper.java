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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserExtraInfo}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserExtraInfo
 * @generated
 */
public class UserExtraInfoWrapper
	extends BaseModelWrapper<UserExtraInfo>
	implements ModelWrapper<UserExtraInfo>, UserExtraInfo {

	public UserExtraInfoWrapper(UserExtraInfo userExtraInfo) {
		super(userExtraInfo);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("extraInfoId", getExtraInfoId());
		attributes.put("userId", getUserId());
		attributes.put("address", getAddress());
		attributes.put("address2", getAddress2());
		attributes.put("city", getCity());
		attributes.put("state", getState());
		attributes.put("zip", getZip());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long extraInfoId = (Long)attributes.get("extraInfoId");

		if (extraInfoId != null) {
			setExtraInfoId(extraInfoId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String address2 = (String)attributes.get("address2");

		if (address2 != null) {
			setAddress2(address2);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		Integer state = (Integer)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		String zip = (String)attributes.get("zip");

		if (zip != null) {
			setZip(zip);
		}
	}

	/**
	 * Returns the address of this user extra info.
	 *
	 * @return the address of this user extra info
	 */
	@Override
	public String getAddress() {
		return model.getAddress();
	}

	/**
	 * Returns the address2 of this user extra info.
	 *
	 * @return the address2 of this user extra info
	 */
	@Override
	public String getAddress2() {
		return model.getAddress2();
	}

	/**
	 * Returns the city of this user extra info.
	 *
	 * @return the city of this user extra info
	 */
	@Override
	public String getCity() {
		return model.getCity();
	}

	/**
	 * Returns the extra info ID of this user extra info.
	 *
	 * @return the extra info ID of this user extra info
	 */
	@Override
	public long getExtraInfoId() {
		return model.getExtraInfoId();
	}

	/**
	 * Returns the primary key of this user extra info.
	 *
	 * @return the primary key of this user extra info
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the state of this user extra info.
	 *
	 * @return the state of this user extra info
	 */
	@Override
	public int getState() {
		return model.getState();
	}

	/**
	 * Returns the user ID of this user extra info.
	 *
	 * @return the user ID of this user extra info
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this user extra info.
	 *
	 * @return the user uuid of this user extra info
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this user extra info.
	 *
	 * @return the uuid of this user extra info
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns the zip of this user extra info.
	 *
	 * @return the zip of this user extra info
	 */
	@Override
	public String getZip() {
		return model.getZip();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the address of this user extra info.
	 *
	 * @param address the address of this user extra info
	 */
	@Override
	public void setAddress(String address) {
		model.setAddress(address);
	}

	/**
	 * Sets the address2 of this user extra info.
	 *
	 * @param address2 the address2 of this user extra info
	 */
	@Override
	public void setAddress2(String address2) {
		model.setAddress2(address2);
	}

	/**
	 * Sets the city of this user extra info.
	 *
	 * @param city the city of this user extra info
	 */
	@Override
	public void setCity(String city) {
		model.setCity(city);
	}

	/**
	 * Sets the extra info ID of this user extra info.
	 *
	 * @param extraInfoId the extra info ID of this user extra info
	 */
	@Override
	public void setExtraInfoId(long extraInfoId) {
		model.setExtraInfoId(extraInfoId);
	}

	/**
	 * Sets the primary key of this user extra info.
	 *
	 * @param primaryKey the primary key of this user extra info
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the state of this user extra info.
	 *
	 * @param state the state of this user extra info
	 */
	@Override
	public void setState(int state) {
		model.setState(state);
	}

	/**
	 * Sets the user ID of this user extra info.
	 *
	 * @param userId the user ID of this user extra info
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this user extra info.
	 *
	 * @param userUuid the user uuid of this user extra info
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this user extra info.
	 *
	 * @param uuid the uuid of this user extra info
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Sets the zip of this user extra info.
	 *
	 * @param zip the zip of this user extra info
	 */
	@Override
	public void setZip(String zip) {
		model.setZip(zip);
	}

	@Override
	protected UserExtraInfoWrapper wrap(UserExtraInfo userExtraInfo) {
		return new UserExtraInfoWrapper(userExtraInfo);
	}

}