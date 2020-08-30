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

package com.amf.registration.service.impl;

import com.amf.registration.model.UserExtraInfo;
import com.amf.registration.service.base.UserExtraInfoLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the user extra info local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.amf.registration.service.UserExtraInfoLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserExtraInfoLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.amf.registration.model.UserExtraInfo",
	service = AopService.class
)
public class UserExtraInfoLocalServiceImpl
	extends UserExtraInfoLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.amf.registration.service.UserExtraInfoLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.amf.registration.service.UserExtraInfoLocalServiceUtil</code>.
	 */

	@Override
	public UserExtraInfo addUserExtraInfo(long userId, String address, String address2, String city, int state, String zipcode, ServiceContext serviceContext) throws PortalException {

		long assignmentId = counterLocalService.increment(UserExtraInfo.class.getName());

		UserExtraInfo userExtraInfo = createUserExtraInfo(assignmentId);

		userExtraInfo.setUserId(userId);
		userExtraInfo.setAddress(address);
		userExtraInfo.setAddress(address2);
		userExtraInfo.setCity(city);
		userExtraInfo.setState(state);
		userExtraInfo.setZip(zipcode);

		return super.addUserExtraInfo(userExtraInfo);
	}

	@Override
	public UserExtraInfo addUserExtraInfo(UserExtraInfo userExtraInfo) {
		throw new UnsupportedOperationException("Not supported.");
	}

}