package com.liang.funny.controller;

import com.liang.funny.model.Access;
import com.liang.funny.service.AccessService;
import com.liang.funny.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/access")
public class AccessController {

    private final Logger logger = LoggerFactory.getLogger(com.liang.funny.controller.UserController.class);

    @Autowired
    private AccessService AccessService;

    /**
     * 新增权限
     * @param map
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult addAccess(@RequestBody Map<String, Object> map){
        AccessService.addAccess(map);
        return new JsonResult(200, true,"新增权限成功");
    }

    /**
     *
     * 获取某一ID权限信息，必需传入id号
     * @param AccessId
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET, produces = "application/json")
    public JsonResult getAccessBy(@PathVariable("id")int AccessId){
        Access Access = AccessService.getAccess(AccessId);
        return new JsonResult(200,true,"获取id="+AccessId+"的权限成功返回");

    }

    /**
     * 获得所用权限信息
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET, produces = "application/json")
    public List<Access> getAccesss(){
        return AccessService.getAccesss();
    }

    /**
     * 删除指定id的权限
     * @param AccessId
     * @return
     */
    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public JsonResult delAccessBy(@PathVariable("id")int AccessId){
        AccessService.delAccess(AccessId);
        return new JsonResult(200,true,"成功删除权限");

    }

    /**
     * 更新指定id的权限信息
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public boolean updateAccess(@PathVariable("id") Integer id, @RequestBody Map<String, Object> map){
        AccessService.updateAccess(id, map);
        return true;
    }
}
