package pers.codewld.imall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.codewld.imall.model.entity.UmsRole;
import pers.codewld.imall.model.param.UmsRoleParam;
import pers.codewld.imall.model.vo.PageVO;
import pers.codewld.imall.model.vo.UmsRoleMarkVO;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @author codewld
 * @since 2022-02-04
 */
public interface UmsRoleService extends IService<UmsRole> {

    /**
     * 添加角色
     */
    boolean add(UmsRoleParam umsRoleParam);

    /**
     * 删除角色
     */
    boolean del(Long id);

    /**
     * 修改角色
     */
    boolean update(Long id, UmsRoleParam umsRoleParam);

    /**
     * 分页查询角色列表，可搜索
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @param name 名称
     */
    PageVO<UmsRole> page(Integer pageNum, Integer pageSize, String name);

    /**
     * 批量查询角色标记
     */
    List<UmsRoleMarkVO> listMark();

    /**
     * 修改角色的菜单
     * @param id 角色id
     * @param menuIdList 菜单id列表
     * @return 修改结果
     */
    boolean updateMenu(Long id,List<Long> menuIdList);

    /**
     * 批量查询角色的菜单ID
     * @param id 角色id
     * @return 菜单ID
     */
    List<Long>  listMenuId(Long id);
}
