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

package com.amf.registration.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserExtraInfoLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserExtraInfoLocalService
 * @generated
 */
public class UserExtraInfoLocalServiceWrapper
	implements ServiceWrapper<UserExtraInfoLocalService>,
			   UserExtraInfoLocalService {

	public UserExtraInfoLocalServiceWrapper(
		UserExtraInfoLocalService userExtraInfoLocalService) {

		_userExtraInfoLocalService = userExtraInfoLocalService;
	}

	@Override
	public com.liferay.portal.kernel.model.User addUserAndExtraInfo(
			long companyId, String password1, String password2, String userName,
			String email, String firstName, String lastName, boolean isMale,
			String phoneNumber, String homePhone, int birthdayMonth,
			int birthdayDay, int birthdayYear, String address, String address2,
			String city, int state, String zipcode,
			String reminderQueryQuestion, String reminderAnswer,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userExtraInfoLocalService.addUserAndExtraInfo(
			companyId, password1, password2, userName, email, firstName,
			lastName, isMale, phoneNumber, homePhone, birthdayMonth,
			birthdayDay, birthdayYear, address, address2, city, state, zipcode,
			reminderQueryQuestion, reminderAnswer, serviceContext);
	}

	@Override
	public com.amf.registration.model.UserExtraInfo addUserExtraInfo(
			long userId, String address, String address2, String city,
			int state, String zipcode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userExtraInfoLocalService.addUserExtraInfo(
			userId, address, address2, city, state, zipcode, serviceContext);
	}

	/**
	 * Adds the user extra info to the database. Also notifies the appropriate model listeners.
	 *
	 * @param userExtraInfo the user extra info
	 * @return the user extra info that was added
	 */
	@Override
	public com.amf.registration.model.UserExtraInfo addUserExtraInfo(
		com.amf.registration.model.UserExtraInfo userExtraInfo) {

		return _userExtraInfoLocalService.addUserExtraInfo(userExtraInfo);
	}

	/**
	 * Creates a new user extra info with the primary key. Does not add the user extra info to the database.
	 *
	 * @param extraInfoId the primary key for the new user extra info
	 * @return the new user extra info
	 */
	@Override
	public com.amf.registration.model.UserExtraInfo createUserExtraInfo(
		long extraInfoId) {

		return _userExtraInfoLocalService.createUserExtraInfo(extraInfoId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userExtraInfoLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user extra info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info that was removed
	 * @throws PortalException if a user extra info with the primary key could not be found
	 */
	@Override
	public com.amf.registration.model.UserExtraInfo deleteUserExtraInfo(
			long extraInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userExtraInfoLocalService.deleteUserExtraInfo(extraInfoId);
	}

	/**
	 * Deletes the user extra info from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userExtraInfo the user extra info
	 * @return the user extra info that was removed
	 */
	@Override
	public com.amf.registration.model.UserExtraInfo deleteUserExtraInfo(
		com.amf.registration.model.UserExtraInfo userExtraInfo) {

		return _userExtraInfoLocalService.deleteUserExtraInfo(userExtraInfo);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _userExtraInfoLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _userExtraInfoLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.amf.registration.model.impl.UserExtraInfoModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _userExtraInfoLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.amf.registration.model.impl.UserExtraInfoModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _userExtraInfoLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _userExtraInfoLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _userExtraInfoLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.amf.registration.model.UserExtraInfo fetchUserExtraInfo(
		long extraInfoId) {

		return _userExtraInfoLocalService.fetchUserExtraInfo(extraInfoId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _userExtraInfoLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _userExtraInfoLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userExtraInfoLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userExtraInfoLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user extra info with the primary key.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info
	 * @throws PortalException if a user extra info with the primary key could not be found
	 */
	@Override
	public com.amf.registration.model.UserExtraInfo getUserExtraInfo(
			long extraInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userExtraInfoLocalService.getUserExtraInfo(extraInfoId);
	}

	/**
	 * Returns a range of all the user extra infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.amf.registration.model.impl.UserExtraInfoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user extra infos
	 * @param end the upper bound of the range of user extra infos (not inclusive)
	 * @return the range of user extra infos
	 */
	@Override
	public java.util.List<com.amf.registration.model.UserExtraInfo>
		getUserExtraInfos(int start, int end) {

		return _userExtraInfoLocalService.getUserExtraInfos(start, end);
	}

	/**
	 * Returns the number of user extra infos.
	 *
	 * @return the number of user extra infos
	 */
	@Override
	public int getUserExtraInfosCount() {
		return _userExtraInfoLocalService.getUserExtraInfosCount();
	}

	/**
	 * Updates the user extra info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userExtraInfo the user extra info
	 * @return the user extra info that was updated
	 */
	@Override
	public com.amf.registration.model.UserExtraInfo updateUserExtraInfo(
		com.amf.registration.model.UserExtraInfo userExtraInfo) {

		return _userExtraInfoLocalService.updateUserExtraInfo(userExtraInfo);
	}

	@Override
	public UserExtraInfoLocalService getWrappedService() {
		return _userExtraInfoLocalService;
	}

	@Override
	public void setWrappedService(
		UserExtraInfoLocalService userExtraInfoLocalService) {

		_userExtraInfoLocalService = userExtraInfoLocalService;
	}

	private UserExtraInfoLocalService _userExtraInfoLocalService;

}