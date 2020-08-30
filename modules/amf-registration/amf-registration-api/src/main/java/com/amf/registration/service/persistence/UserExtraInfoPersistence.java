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

package com.amf.registration.service.persistence;

import com.amf.registration.exception.NoSuchUserExtraInfoException;
import com.amf.registration.model.UserExtraInfo;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the user extra info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserExtraInfoUtil
 * @generated
 */
@ProviderType
public interface UserExtraInfoPersistence
	extends BasePersistence<UserExtraInfo> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserExtraInfoUtil} to access the user extra info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the user extra infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user extra infos
	 */
	public java.util.List<UserExtraInfo> findByUuid(String uuid);

	/**
	 * Returns a range of all the user extra infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserExtraInfoModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user extra infos
	 * @param end the upper bound of the range of user extra infos (not inclusive)
	 * @return the range of matching user extra infos
	 */
	public java.util.List<UserExtraInfo> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the user extra infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserExtraInfoModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user extra infos
	 * @param end the upper bound of the range of user extra infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user extra infos
	 */
	public java.util.List<UserExtraInfo> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserExtraInfo>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user extra infos where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserExtraInfoModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of user extra infos
	 * @param end the upper bound of the range of user extra infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching user extra infos
	 */
	public java.util.List<UserExtraInfo> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserExtraInfo>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user extra info
	 * @throws NoSuchUserExtraInfoException if a matching user extra info could not be found
	 */
	public UserExtraInfo findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<UserExtraInfo>
				orderByComparator)
		throws NoSuchUserExtraInfoException;

	/**
	 * Returns the first user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user extra info, or <code>null</code> if a matching user extra info could not be found
	 */
	public UserExtraInfo fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserExtraInfo>
			orderByComparator);

	/**
	 * Returns the last user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user extra info
	 * @throws NoSuchUserExtraInfoException if a matching user extra info could not be found
	 */
	public UserExtraInfo findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<UserExtraInfo>
				orderByComparator)
		throws NoSuchUserExtraInfoException;

	/**
	 * Returns the last user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user extra info, or <code>null</code> if a matching user extra info could not be found
	 */
	public UserExtraInfo fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<UserExtraInfo>
			orderByComparator);

	/**
	 * Returns the user extra infos before and after the current user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param extraInfoId the primary key of the current user extra info
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user extra info
	 * @throws NoSuchUserExtraInfoException if a user extra info with the primary key could not be found
	 */
	public UserExtraInfo[] findByUuid_PrevAndNext(
			long extraInfoId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<UserExtraInfo>
				orderByComparator)
		throws NoSuchUserExtraInfoException;

	/**
	 * Removes all the user extra infos where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of user extra infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user extra infos
	 */
	public int countByUuid(String uuid);

	/**
	 * Caches the user extra info in the entity cache if it is enabled.
	 *
	 * @param userExtraInfo the user extra info
	 */
	public void cacheResult(UserExtraInfo userExtraInfo);

	/**
	 * Caches the user extra infos in the entity cache if it is enabled.
	 *
	 * @param userExtraInfos the user extra infos
	 */
	public void cacheResult(java.util.List<UserExtraInfo> userExtraInfos);

	/**
	 * Creates a new user extra info with the primary key. Does not add the user extra info to the database.
	 *
	 * @param extraInfoId the primary key for the new user extra info
	 * @return the new user extra info
	 */
	public UserExtraInfo create(long extraInfoId);

	/**
	 * Removes the user extra info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info that was removed
	 * @throws NoSuchUserExtraInfoException if a user extra info with the primary key could not be found
	 */
	public UserExtraInfo remove(long extraInfoId)
		throws NoSuchUserExtraInfoException;

	public UserExtraInfo updateImpl(UserExtraInfo userExtraInfo);

	/**
	 * Returns the user extra info with the primary key or throws a <code>NoSuchUserExtraInfoException</code> if it could not be found.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info
	 * @throws NoSuchUserExtraInfoException if a user extra info with the primary key could not be found
	 */
	public UserExtraInfo findByPrimaryKey(long extraInfoId)
		throws NoSuchUserExtraInfoException;

	/**
	 * Returns the user extra info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info, or <code>null</code> if a user extra info with the primary key could not be found
	 */
	public UserExtraInfo fetchByPrimaryKey(long extraInfoId);

	/**
	 * Returns all the user extra infos.
	 *
	 * @return the user extra infos
	 */
	public java.util.List<UserExtraInfo> findAll();

	/**
	 * Returns a range of all the user extra infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserExtraInfoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user extra infos
	 * @param end the upper bound of the range of user extra infos (not inclusive)
	 * @return the range of user extra infos
	 */
	public java.util.List<UserExtraInfo> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the user extra infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserExtraInfoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user extra infos
	 * @param end the upper bound of the range of user extra infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user extra infos
	 */
	public java.util.List<UserExtraInfo> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserExtraInfo>
			orderByComparator);

	/**
	 * Returns an ordered range of all the user extra infos.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>UserExtraInfoModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user extra infos
	 * @param end the upper bound of the range of user extra infos (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of user extra infos
	 */
	public java.util.List<UserExtraInfo> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserExtraInfo>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the user extra infos from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of user extra infos.
	 *
	 * @return the number of user extra infos
	 */
	public int countAll();

}