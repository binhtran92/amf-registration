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

package com.amf.registration.service.persistence.impl;

import com.amf.registration.exception.NoSuchUserExtraInfoException;
import com.amf.registration.model.UserExtraInfo;
import com.amf.registration.model.impl.UserExtraInfoImpl;
import com.amf.registration.model.impl.UserExtraInfoModelImpl;
import com.amf.registration.service.persistence.UserExtraInfoPersistence;
import com.amf.registration.service.persistence.impl.constants.AMFPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the user extra info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = UserExtraInfoPersistence.class)
public class UserExtraInfoPersistenceImpl
	extends BasePersistenceImpl<UserExtraInfo>
	implements UserExtraInfoPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>UserExtraInfoUtil</code> to access the user extra info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		UserExtraInfoImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the user extra infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching user extra infos
	 */
	@Override
	public List<UserExtraInfo> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<UserExtraInfo> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

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
	@Override
	public List<UserExtraInfo> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserExtraInfo> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

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
	@Override
	public List<UserExtraInfo> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<UserExtraInfo> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<UserExtraInfo> list = null;

		if (useFinderCache) {
			list = (List<UserExtraInfo>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (UserExtraInfo userExtraInfo : list) {
					if (!uuid.equals(userExtraInfo.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_USEREXTRAINFO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(UserExtraInfoModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<UserExtraInfo>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user extra info
	 * @throws NoSuchUserExtraInfoException if a matching user extra info could not be found
	 */
	@Override
	public UserExtraInfo findByUuid_First(
			String uuid, OrderByComparator<UserExtraInfo> orderByComparator)
		throws NoSuchUserExtraInfoException {

		UserExtraInfo userExtraInfo = fetchByUuid_First(
			uuid, orderByComparator);

		if (userExtraInfo != null) {
			return userExtraInfo;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchUserExtraInfoException(sb.toString());
	}

	/**
	 * Returns the first user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user extra info, or <code>null</code> if a matching user extra info could not be found
	 */
	@Override
	public UserExtraInfo fetchByUuid_First(
		String uuid, OrderByComparator<UserExtraInfo> orderByComparator) {

		List<UserExtraInfo> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user extra info
	 * @throws NoSuchUserExtraInfoException if a matching user extra info could not be found
	 */
	@Override
	public UserExtraInfo findByUuid_Last(
			String uuid, OrderByComparator<UserExtraInfo> orderByComparator)
		throws NoSuchUserExtraInfoException {

		UserExtraInfo userExtraInfo = fetchByUuid_Last(uuid, orderByComparator);

		if (userExtraInfo != null) {
			return userExtraInfo;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchUserExtraInfoException(sb.toString());
	}

	/**
	 * Returns the last user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user extra info, or <code>null</code> if a matching user extra info could not be found
	 */
	@Override
	public UserExtraInfo fetchByUuid_Last(
		String uuid, OrderByComparator<UserExtraInfo> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<UserExtraInfo> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user extra infos before and after the current user extra info in the ordered set where uuid = &#63;.
	 *
	 * @param extraInfoId the primary key of the current user extra info
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user extra info
	 * @throws NoSuchUserExtraInfoException if a user extra info with the primary key could not be found
	 */
	@Override
	public UserExtraInfo[] findByUuid_PrevAndNext(
			long extraInfoId, String uuid,
			OrderByComparator<UserExtraInfo> orderByComparator)
		throws NoSuchUserExtraInfoException {

		uuid = Objects.toString(uuid, "");

		UserExtraInfo userExtraInfo = findByPrimaryKey(extraInfoId);

		Session session = null;

		try {
			session = openSession();

			UserExtraInfo[] array = new UserExtraInfoImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, userExtraInfo, uuid, orderByComparator, true);

			array[1] = userExtraInfo;

			array[2] = getByUuid_PrevAndNext(
				session, userExtraInfo, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected UserExtraInfo getByUuid_PrevAndNext(
		Session session, UserExtraInfo userExtraInfo, String uuid,
		OrderByComparator<UserExtraInfo> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_USEREXTRAINFO_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(UserExtraInfoModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						userExtraInfo)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<UserExtraInfo> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the user extra infos where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (UserExtraInfo userExtraInfo :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(userExtraInfo);
		}
	}

	/**
	 * Returns the number of user extra infos where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching user extra infos
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_USEREXTRAINFO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"userExtraInfo.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(userExtraInfo.uuid IS NULL OR userExtraInfo.uuid = '')";

	public UserExtraInfoPersistenceImpl() {
		setModelClass(UserExtraInfo.class);

		setModelImplClass(UserExtraInfoImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("state", "state_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the user extra info in the entity cache if it is enabled.
	 *
	 * @param userExtraInfo the user extra info
	 */
	@Override
	public void cacheResult(UserExtraInfo userExtraInfo) {
		entityCache.putResult(
			entityCacheEnabled, UserExtraInfoImpl.class,
			userExtraInfo.getPrimaryKey(), userExtraInfo);

		userExtraInfo.resetOriginalValues();
	}

	/**
	 * Caches the user extra infos in the entity cache if it is enabled.
	 *
	 * @param userExtraInfos the user extra infos
	 */
	@Override
	public void cacheResult(List<UserExtraInfo> userExtraInfos) {
		for (UserExtraInfo userExtraInfo : userExtraInfos) {
			if (entityCache.getResult(
					entityCacheEnabled, UserExtraInfoImpl.class,
					userExtraInfo.getPrimaryKey()) == null) {

				cacheResult(userExtraInfo);
			}
			else {
				userExtraInfo.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user extra infos.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UserExtraInfoImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user extra info.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserExtraInfo userExtraInfo) {
		entityCache.removeResult(
			entityCacheEnabled, UserExtraInfoImpl.class,
			userExtraInfo.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<UserExtraInfo> userExtraInfos) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserExtraInfo userExtraInfo : userExtraInfos) {
			entityCache.removeResult(
				entityCacheEnabled, UserExtraInfoImpl.class,
				userExtraInfo.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, UserExtraInfoImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new user extra info with the primary key. Does not add the user extra info to the database.
	 *
	 * @param extraInfoId the primary key for the new user extra info
	 * @return the new user extra info
	 */
	@Override
	public UserExtraInfo create(long extraInfoId) {
		UserExtraInfo userExtraInfo = new UserExtraInfoImpl();

		userExtraInfo.setNew(true);
		userExtraInfo.setPrimaryKey(extraInfoId);

		String uuid = PortalUUIDUtil.generate();

		userExtraInfo.setUuid(uuid);

		return userExtraInfo;
	}

	/**
	 * Removes the user extra info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info that was removed
	 * @throws NoSuchUserExtraInfoException if a user extra info with the primary key could not be found
	 */
	@Override
	public UserExtraInfo remove(long extraInfoId)
		throws NoSuchUserExtraInfoException {

		return remove((Serializable)extraInfoId);
	}

	/**
	 * Removes the user extra info with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user extra info
	 * @return the user extra info that was removed
	 * @throws NoSuchUserExtraInfoException if a user extra info with the primary key could not be found
	 */
	@Override
	public UserExtraInfo remove(Serializable primaryKey)
		throws NoSuchUserExtraInfoException {

		Session session = null;

		try {
			session = openSession();

			UserExtraInfo userExtraInfo = (UserExtraInfo)session.get(
				UserExtraInfoImpl.class, primaryKey);

			if (userExtraInfo == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserExtraInfoException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(userExtraInfo);
		}
		catch (NoSuchUserExtraInfoException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected UserExtraInfo removeImpl(UserExtraInfo userExtraInfo) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(userExtraInfo)) {
				userExtraInfo = (UserExtraInfo)session.get(
					UserExtraInfoImpl.class, userExtraInfo.getPrimaryKeyObj());
			}

			if (userExtraInfo != null) {
				session.delete(userExtraInfo);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (userExtraInfo != null) {
			clearCache(userExtraInfo);
		}

		return userExtraInfo;
	}

	@Override
	public UserExtraInfo updateImpl(UserExtraInfo userExtraInfo) {
		boolean isNew = userExtraInfo.isNew();

		if (!(userExtraInfo instanceof UserExtraInfoModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(userExtraInfo.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					userExtraInfo);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in userExtraInfo proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom UserExtraInfo implementation " +
					userExtraInfo.getClass());
		}

		UserExtraInfoModelImpl userExtraInfoModelImpl =
			(UserExtraInfoModelImpl)userExtraInfo;

		if (Validator.isNull(userExtraInfo.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			userExtraInfo.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (userExtraInfo.isNew()) {
				session.save(userExtraInfo);

				userExtraInfo.setNew(false);
			}
			else {
				userExtraInfo = (UserExtraInfo)session.merge(userExtraInfo);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {userExtraInfoModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((userExtraInfoModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					userExtraInfoModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {userExtraInfoModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, UserExtraInfoImpl.class,
			userExtraInfo.getPrimaryKey(), userExtraInfo, false);

		userExtraInfo.resetOriginalValues();

		return userExtraInfo;
	}

	/**
	 * Returns the user extra info with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user extra info
	 * @return the user extra info
	 * @throws NoSuchUserExtraInfoException if a user extra info with the primary key could not be found
	 */
	@Override
	public UserExtraInfo findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserExtraInfoException {

		UserExtraInfo userExtraInfo = fetchByPrimaryKey(primaryKey);

		if (userExtraInfo == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserExtraInfoException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return userExtraInfo;
	}

	/**
	 * Returns the user extra info with the primary key or throws a <code>NoSuchUserExtraInfoException</code> if it could not be found.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info
	 * @throws NoSuchUserExtraInfoException if a user extra info with the primary key could not be found
	 */
	@Override
	public UserExtraInfo findByPrimaryKey(long extraInfoId)
		throws NoSuchUserExtraInfoException {

		return findByPrimaryKey((Serializable)extraInfoId);
	}

	/**
	 * Returns the user extra info with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param extraInfoId the primary key of the user extra info
	 * @return the user extra info, or <code>null</code> if a user extra info with the primary key could not be found
	 */
	@Override
	public UserExtraInfo fetchByPrimaryKey(long extraInfoId) {
		return fetchByPrimaryKey((Serializable)extraInfoId);
	}

	/**
	 * Returns all the user extra infos.
	 *
	 * @return the user extra infos
	 */
	@Override
	public List<UserExtraInfo> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<UserExtraInfo> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<UserExtraInfo> findAll(
		int start, int end,
		OrderByComparator<UserExtraInfo> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<UserExtraInfo> findAll(
		int start, int end, OrderByComparator<UserExtraInfo> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<UserExtraInfo> list = null;

		if (useFinderCache) {
			list = (List<UserExtraInfo>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_USEREXTRAINFO);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_USEREXTRAINFO;

				sql = sql.concat(UserExtraInfoModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<UserExtraInfo>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the user extra infos from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (UserExtraInfo userExtraInfo : findAll()) {
			remove(userExtraInfo);
		}
	}

	/**
	 * Returns the number of user extra infos.
	 *
	 * @return the number of user extra infos
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_USEREXTRAINFO);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "extraInfoId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_USEREXTRAINFO;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return UserExtraInfoModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the user extra info persistence.
	 */
	@Activate
	public void activate() {
		UserExtraInfoModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		UserExtraInfoModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, UserExtraInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, UserExtraInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, UserExtraInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, UserExtraInfoImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			UserExtraInfoModelImpl.UUID_COLUMN_BITMASK |
			UserExtraInfoModelImpl.USERID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(UserExtraInfoImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = AMFPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.amf.registration.model.UserExtraInfo"),
			true);
	}

	@Override
	@Reference(
		target = AMFPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = AMFPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_USEREXTRAINFO =
		"SELECT userExtraInfo FROM UserExtraInfo userExtraInfo";

	private static final String _SQL_SELECT_USEREXTRAINFO_WHERE =
		"SELECT userExtraInfo FROM UserExtraInfo userExtraInfo WHERE ";

	private static final String _SQL_COUNT_USEREXTRAINFO =
		"SELECT COUNT(userExtraInfo) FROM UserExtraInfo userExtraInfo";

	private static final String _SQL_COUNT_USEREXTRAINFO_WHERE =
		"SELECT COUNT(userExtraInfo) FROM UserExtraInfo userExtraInfo WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "userExtraInfo.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No UserExtraInfo exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No UserExtraInfo exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		UserExtraInfoPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "state"});

	static {
		try {
			Class.forName(AMFPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}