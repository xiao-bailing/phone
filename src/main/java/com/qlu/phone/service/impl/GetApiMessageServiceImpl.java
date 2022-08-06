package com.qlu.phone.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlu.phone.Mapper.GetApiMessageMapper;
import com.qlu.phone.entity.GetApiMessage;
import com.qlu.phone.service.GetApiMessageService;
import org.springframework.stereotype.Service;

@Service
public class GetApiMessageServiceImpl extends ServiceImpl<GetApiMessageMapper, GetApiMessage> implements GetApiMessageService {
}
