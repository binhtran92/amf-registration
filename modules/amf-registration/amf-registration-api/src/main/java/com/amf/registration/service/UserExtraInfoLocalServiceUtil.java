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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for UserExtraInfo. This utility wraps
 * <code>com.amf.registration.service.impl.UserExtraInfoLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserExtraInfoLocalService
 * @generated
 */
public class UserExtraInfoLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.amf.registration.service.impl.UserExtraInfoLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.amf.registration.model.UserExtraInfo addUserExtraInfo(
			long userId, String address, String address2, String city,
			int state, String zipcode,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addUserExtraInfo(
			userId, address, address2, city, state, zipcode, serviceContext);
	}

	/**
	 * Adds the user extra info to the database. Also notifies the appropriate model listeners.
	 *
	 * @param userExtraInfo the user extra info
	 * @return the user extra info that was added
	 */
	public static com.amf.registration.model.UserExtraInfo addUserExtraInfo(
		com.amf.registration.model.UserExtraInfo userExtraInfo) {

		return getService().addUserExtraInfo(userExtraInfo);
	}

	/**
	 * Creates a new user extra info with the primary key. Does not add the user extra info to the database.
	 *
	 * @param extraInfoId the primary key for the new user extra info
	 * @return the new user extra info
	 */
	public static com.amf.registration.model.UserExtraInfo createUserExtraInfo(
		long extraInfoId) {

		return getService().createUserExtraInfo(extraInfoId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the user extra info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info that was removed
	 * @throws PortalException if a user extra info with the primary key could not be found
	 */
	public static com.amf.registration.model.UserExtraInfo deleteUserExtraInfo(
			long extraInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteUserExtraInfo(extraInfoId);
	}

	/**
	 * Deletes the user extra info from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userExtraInfo the user extra info
	 * @return the user extra info that was removed
	 */
	public static com.amf.registration.model.UserExtraInfo deleteUserExtraInfo(
		com.amf.registration.model.UserExtraInfo userExtraInfo) {

		return getService().deleteUserExtraInfo(userExtraInfo);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.amf.registration.model.UserExtraInfo fetchUserExtraInfo(
		long extraInfoId) {

		return getService().fetchUserExtraInfo(extraInfoId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the user extra info with the primary key.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info
	 * @throws PortalException if a user extra info with the primary key could not be found
	 */
	public static com.amf.registration.model.UserExtraInfo getUserExtraInfo(
			long extraInfoId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getUserExtraInfo(extraInfoId);
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
	public static java.util.List<com.amf.registration.model.UserExtraInfo>
		getUserExtraInfos(int start, int end) {

		return getService().getUserExtraInfos(start, end);
	}

	/**
	 * Returns the number of user extra infos.
	 *
	 * @return the number of user extra infos
	 */
	public static int getUserExtraInfosCount() {
		return getService().getUserExtraInfosCount();
	}

	/**
	 * Updates the user extra info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userExtraInfo the user extra info
	 * @return the user extra info that was updated
	 */
	public static com.amf.registration.model.UserExtraInfo updateUserExtraInfo(
		com.amf.registration.model.UserExtraInfo userExtraInfo) {

		return getService().updateUserExtraInfo(userExtraInfo);
	}

	public static UserExtraInfoLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<UserExtraInfoLocalService, UserExtraInfoLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			UserExtraInfoLocalService.class);

		ServiceTracker<UserExtraInfoLocalService, UserExtraInfoLocalService>
			serviceTracker =
				new ServiceTracker
					<UserExtraInfoLocalService, UserExtraInfoLocalService>(
						bundle.getBundleContext(),
						UserExtraInfoLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}