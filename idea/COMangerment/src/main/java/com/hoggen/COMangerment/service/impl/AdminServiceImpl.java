package com.hoggen.COMangerment.service.impl;

import com.hoggen.COMangerment.dao.AdminDao;
import com.hoggen.COMangerment.dto.AdminExecution;
import com.hoggen.COMangerment.entity.Admin;
import com.hoggen.COMangerment.enums.UserStateEnum;
import com.hoggen.COMangerment.service.AdminService;
import com.hoggen.COMangerment.util.MD5Util;
import com.hoggen.COMangerment.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao rAdminDao;

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
//	@Autowired
//	private JedisUtil.Strings jedisStrings;

    @Override
    public Admin queryAdminByName(String name) {
        Admin user = null;
        user = rAdminDao.queryByUserName(name);
        logger.info("queryAdminByName");

        return user;
    }

    @Override
    public Admin queryAdminById(Long userId) {
        Admin user = null;

        user = rAdminDao.queryByUserId(userId);
        logger.info("queryAdminById");

        return user;
    }

    @Override
    @Transactional
    public AdminExecution modifyAdmin(Admin user, int type) {

        return null;
    }

}
