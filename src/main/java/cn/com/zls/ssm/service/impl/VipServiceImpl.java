package cn.com.zls.ssm.service.impl;

import cn.com.zls.ssm.entity.Vip;
import cn.com.zls.ssm.service.VipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class VipServiceImpl extends BaseServiceImpl<Vip> implements VipService {
}
