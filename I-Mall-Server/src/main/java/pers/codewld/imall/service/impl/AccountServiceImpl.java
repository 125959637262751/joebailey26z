package pers.codewld.imall.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pers.codewld.imall.model.entity.UmsAdmin;
import pers.codewld.imall.model.param.LoginParam;
import pers.codewld.imall.model.vo.UmsRoleMarkVO;
import pers.codewld.imall.security.JWTUtil;
import pers.codewld.imall.service.AccountService;
import pers.codewld.imall.service.UmsAdminService;
import pers.codewld.imall.util.TransformUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * <p>
 * 账户 服务实现类
 * </p>
 *
 * @author codewld
 * @since 2022-02-10
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    UmsAdminService umsAdminService;

    @Autowired
    PasswordEncoder MD5PasswordEncoder;

    @Autowired
    JWTUtil jwtUtil;

    @Override
    public String login(LoginParam loginParam) {
        // 查询基本信息
        UmsAdmin umsAdmin = umsAdminService.getByUsername(loginParam.getUsername());
        // 校验基本信息
        if (umsAdmin == null || !MD5PasswordEncoder.matches(loginParam.getPassword(), umsAdmin.getPassword())) {
            throw new BadCredentialsException("账号密码错误");
        }
        if (!umsAdmin.getStatus()) {
            throw new DisabledException("账号被禁用");
        }
        // 查询角色信息
        List<UmsRoleMarkVO> umsRoleMarkVOList = umsAdminService.listRoleMark(umsAdmin.getId());
        List<GrantedAuthority> authorityList = umsRoleMarkVOList
                .stream()
                .map(o -> new SimpleGrantedAuthority(o.getCode().toUpperCase()))
                .collect(Collectors.toList());
        umsAdmin.setAuthorities(authorityList);
        // 保存登录记录
        UpdateWrapper<UmsAdmin> updateWrapper = new UpdateWrapper<UmsAdmin>()
                .eq("id", umsAdmin.getId()).set("login_time", LocalDateTime.now());
        boolean res = umsAdminService.update(updateWrapper);
        if (!res) {
            throw new RuntimeException("登录记录保存失败");
        }
        return jwtUtil.sign(umsAdmin);
    }

}
