package com.liang.funny.service;

import com.liang.funny.dao.AccessMapper;
import com.liang.funny.model.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AccessServiceImpl implements AccessService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccessMapper accessMapper;

    @Override
    public int addAccess(Map<String, Object> map) {
        String title = map.get("title").toString();
        String urls = map.get("urls").toString();
        Access access = new Access();
        access.setTitle(title);
        access.setUrls(urls);
        access.setStatus(true);
        access.setCreatedTime(new Date());
        access.setUpdatedTime(new Date());

        return accessMapper.insert(access);
    }

    @Override
    public int delAccess(Integer id) {
        return accessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateAccess(Integer id, Map<String, Object> map) {
        String title = map.get("title").toString();
        String urls = map.get("urls").toString();
        Boolean status = Boolean.parseBoolean(map.get("status").toString());

        Access access = accessMapper.selectByPrimaryKey(id);
        access.setTitle(title);
        access.setUrls(urls);
        access.setStatus(status);
        access.setUpdatedTime(new Date());
        return accessMapper.updateByPrimaryKeySelective(access);
    }

    @Override
    public Access getAccess(int id) {
        return accessMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Access> getAccesss() {
        return accessMapper.getAccesses();
    }
}
