package cn.com.zls.ssm.service.impl;

import cn.com.zls.ssm.entity.InRoomInfo;
import cn.com.zls.ssm.entity.Rooms;
import cn.com.zls.ssm.service.InRoomInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class InRoomInfoServiceImpl extends BaseServiceImpl<InRoomInfo> implements InRoomInfoService {
    //重写添加入住信息方法
    @Override
    public String save(InRoomInfo inRoomInfo) throws Exception {
        //1.执行入住信息添加
        Integer insINICount = baseMapper.insert(inRoomInfo);
        //2.完成客房状态修改
        //2.1.新建客房对象
        Rooms rooms = new Rooms();
        //2.2.设置客房对象的数据
        rooms.setId(inRoomInfo.getRoomId());
        rooms.setRoomStatus("1");
        //2.3.执行客房状态修改 由0（空闲）---->1（已入住）
        int updRoomsCount = roomsMapper.updateByPrimaryKeySelective(rooms);
        if(insINICount>0&&updRoomsCount>0){
            return "success";
        }else {
            return "fail";
        }
    }
}
