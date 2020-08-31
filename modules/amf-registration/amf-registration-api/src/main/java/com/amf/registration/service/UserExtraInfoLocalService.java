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

import com.amf.registration.model.UserExtraInfo;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for UserExtraInfo. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserExtraInfoLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface UserExtraInfoLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserExtraInfoLocalServiceUtil} to access the user extra info local service. Add custom service methods to <code>com.amf.registration.service.impl.UserExtraInfoLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public User addUserAndExtraInfo(
			long companyId, String password1, String password2, String userName,
			String email, String firstName, String lastName, boolean isMale,
			String phoneNumber, String homePhone, int birthdayMonth,
			int birthdayDay, int birthdayYear, String address, String address2,
			String city, int state, String zipcode,
			String reminderQueryQuestion, String reminderAnswer,
			ServiceContext serviceContext)
		throws PortalException;

	public UserExtraInfo addUserExtraInfo(
			long userId, String address, String address2, String city,
			int state, String zipcode, ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Adds the user extra info to the database. Also notifies the appropriate model listeners.
	 *
	 * @param userExtraInfo the user extra info
	 * @return the user extra info that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public UserExtraInfo addUserExtraInfo(UserExtraInfo userExtraInfo);

	/**
	 * Creates a new user extra info with the primary key. Does not add the user extra info to the database.
	 *
	 * @param extraInfoId the primary key for the new user extra info
	 * @return the new user extra info
	 */
	@Transactional(enabled = false)
	public UserExtraInfo createUserExtraInfo(long extraInfoId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the user extra info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info that was removed
	 * @throws PortalException if a user extra info with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public UserExtraInfo deleteUserExtraInfo(long extraInfoId)
		throws PortalException;

	/**
	 * Deletes the user extra info from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userExtraInfo the user extra info
	 * @return the user extra info that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public UserExtraInfo deleteUserExtraInfo(UserExtraInfo userExtraInfo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserExtraInfo fetchUserExtraInfo(long extraInfoId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the user extra info with the primary key.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info
	 * @throws PortalException if a user extra info with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserExtraInfo getUserExtraInfo(long extraInfoId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserExtraInfo> getUserExtraInfos(int start, int end);

	/**
	 * Returns the number of user extra infos.
	 *
	 * @return the number of user extra infos
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserExtraInfosCount();

	/**
	 * Updates the user extra info in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userExtraInfo the user extra info
	 * @return the user extra info that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public UserExtraInfo updateUserExtraInfo(UserExtraInfo userExtraInfo);

}