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
import com.amf.registration.validator.AMFRegistrationValidator;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.ListTypeModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.GuestOrUserUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;

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

	@Override
	public User addUserAndExtraInfo(
			long companyId, String password1, String password2, String userName,
			String email, String firstName, String lastName, boolean isMale,
			String phoneNumber, String homePhone, int birthdayMonth,
			int birthdayDay, int birthdayYear, String address, String address2,
			String city, int state, String zipcode,
			String reminderQueryQuestion, String reminderAnswer,
			ServiceContext serviceContext)
		throws PortalException {

		final ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			serviceContext.getLocale(), getClass());

		AMFRegistrationValidator.validateBasicInfo(
			firstName, lastName, userName, email, birthdayDay, birthdayMonth,
			birthdayYear, password1, password2, resourceBundle);

		User user = userLocalService.addUser(
			GuestOrUserUtil.getGuestOrUserId(), companyId, false, password1,
			password2, false, userName, email, _DEFAULT_INT, _EMPTY_STRING,
			serviceContext.getLocale(), firstName, _EMPTY_STRING, lastName,
			_DEFAULT_INT, _DEFAULT_INT, true, birthdayMonth, birthdayDay,
			birthdayYear, _EMPTY_STRING, null, null, null, null, false,
			serviceContext);

		_addReminder(reminderQueryQuestion, reminderAnswer, user);

		_addPhoneNumber(
			user, homePhone, phoneNumber, resourceBundle, serviceContext);

		userExtraInfoLocalService.addUserExtraInfo(
			user.getUserId(), address, address2, city, state, zipcode,
			serviceContext);

		return user;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.amf.registration.service.UserExtraInfoLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.amf.registration.service.UserExtraInfoLocalServiceUtil</code>.
	 */
	@Override
	public UserExtraInfo addUserExtraInfo(
			long userId, String address, String address2, String city,
			int state, String zipcode, ServiceContext serviceContext)
		throws PortalException {

		final ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			serviceContext.getLocale(), getClass());

		AMFRegistrationValidator.validateAddressInfo(
			address, address2, city, state, zipcode, resourceBundle);

		long assignmentId = counterLocalService.increment(
			UserExtraInfo.class.getName());

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
		throw new UnsupportedOperationException("Not supported");
	}

	private void _addPhoneNumber(
			User user, String homePhone, String mobilePhone,
			ResourceBundle resourceBundle, ServiceContext serviceContext)
		throws PortalException {

		if (Validator.isNotNull(homePhone)) {
			AMFRegistrationValidator.isValidPhoneNumber(
				homePhone, resourceBundle);
			phoneLocalService.addPhone(
				user.getUserId(), Contact.class.getName(), user.getContactId(),
				homePhone, null, _getPhoneListTypeId("other"), false,
				serviceContext);
		}

		if (Validator.isNotNull(mobilePhone)) {
			AMFRegistrationValidator.isValidPhoneNumber(
				mobilePhone, resourceBundle);
			phoneLocalService.addPhone(
				user.getUserId(), Contact.class.getName(), user.getContactId(),
				homePhone, null, _getPhoneListTypeId("mobile-phone"), false,
				serviceContext);
		}
	}

	private void _addReminder(
		String reminderQueryQuestion, String reminderQueryAnswer, User user) {

		user.setReminderQueryQuestion(reminderQueryQuestion);
		user.setReminderQueryAnswer(reminderQueryAnswer);

		userLocalService.updateUser(user);
	}

	private long _getPhoneListTypeId(String phoneTypeName) {
		List<ListType> listTypes = listTypeLocalService.getListTypes(
			ListTypeConstants.CONTACT_PHONE);

		final Stream<ListType> listTypeStream = listTypes.stream();

		final Optional<ListType> result = listTypeStream.filter(
			e -> phoneTypeName.equals(e.getName())
		).findFirst();

		return result.map(
			ListTypeModel::getListTypeId
		).orElse(
			0L
		);
	}

	private static final int _DEFAULT_INT = 0;

	private static final String _EMPTY_STRING = "";

}