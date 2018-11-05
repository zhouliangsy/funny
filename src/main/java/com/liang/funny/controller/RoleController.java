package com.liang.funny.controller;

import com.liang.funny.model.Role;
import com.liang.funny.service.RoleService;
import com.liang.funny.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/role")
public class RoleController {


        private final Logger logger = LoggerFactory.getLogger(com.liang.funny.controller.UserController.class);

        @Autowired
        private RoleService roleService;

        /**
         * 新增角色
         * @param map
         * @return
         */
        @RequestMapping(value = "/add", method = RequestMethod.POST)
        public JsonResult addRole(@RequestBody Map<String, Object> map){
            roleService.addRole(map);
            return new JsonResult(200, true,"新增角色成功");
        }

        /**
         *
         * 获取某一ID角色信息，必需传入id号
         * @param roleId
         * @return
         */
        @RequestMapping(value = "/get/{id}",method = RequestMethod.GET, produces = "application/json")
        public JsonResult getRoleBy(@PathVariable("id")int roleId){
            Role role = roleService.getRole(roleId);
            return new JsonResult(200,true,"获取id="+roleId+"的角色成功返回");

        }

        /**
         * 获得所用角色信息
         * @return
         */
        @RequestMapping(value = "/get",method = RequestMethod.GET, produces = "application/json")
        public List<Role> getRoles(){
            return roleService.getRoles();
        }

        /**
         * 删除指定id的角色
         * @param roleId
         * @return
         */
        @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
        public JsonResult delRoleBy(@PathVariable("id")int roleId){
            roleService.delRole(roleId);
            return new JsonResult(200,true,"成功删除角色");

        }

        /**
         * 更新指定id的角色信息
         * @param id
         * @param map
         * @return
         */
        @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
        public boolean updateRole(@PathVariable("id") Integer id, @RequestBody Map<String, Object> map){
            roleService.updateRole(id, map);
            return true;
        }
}
