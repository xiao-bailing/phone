package com.qlu.phone.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlu.phone.Mapper.UpdaterMapper;
import com.qlu.phone.entity.Updater;
import com.qlu.phone.service.UpdateService;
import org.springframework.stereotype.Service;

@Service
public class UpdateServiceImpl extends ServiceImpl<UpdaterMapper,Updater> implements UpdateService {
}
