package com.zzyl.service.impl;

import com.zzyl.constant.Constants;
import com.zzyl.dto.BedDto;
import com.zzyl.entity.Bed;
import com.zzyl.enums.BasicEnum;
import com.zzyl.exception.BaseException;
import com.zzyl.intercept.AutoFillInterceptor;
import com.zzyl.mapper.BedMapper;
import com.zzyl.service.BedService;
import com.zzyl.vo.BedVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BedServiceImpl implements BedService {

    @Autowired
    private BedMapper bedMapper;


    @Override
    public List<BedVo> getBedsByRoomId(Long roomId) {
        return bedMapper.getBedsByRoomId(roomId);
    }

    @Override
    public void createBed(BedDto bedDto) {
        Bed bed =new Bed();
        Long id = AutoFillInterceptor.loadUserId();
        BeanUtils.copyProperties(bedDto,bed);
        bed.setCreateTime(LocalDateTime.now());
        bed.setUpdateTime(LocalDateTime.now());
        bed.setCreateBy(id);
        bed.setUpdateBy(id);
        bed.setBedStatus(Integer.valueOf(Constants.SUCCESS));
        bed.setRemark(id+"创建了新床位");

        try {
            bedMapper.createBed(bed);
        } catch (Exception e) {
            throw new BaseException(BasicEnum.BED_INSERT_FAIL);
        }
    }

    @Override
    public BedDto getByBedId(Long id) {
      Bed bed = bedMapper.getByBedId(id);
      BedDto bedDto =new BedDto();
      BeanUtils.copyProperties(bed,bedDto);
      return bedDto;
    }

    @Override
    public void upDateBed(BedDto bedDto) {
        Long userId = AutoFillInterceptor.loadUserId();
        Long id = bedDto.getId();
        Long roomId = bedDto.getRoomId();
        Bed bed = bedMapper.getByBedId(id);
        BeanUtils.copyProperties(bedDto,bed);
        bed.setUpdateTime(LocalDateTime.now());
        bed.setUpdateBy(userId);
        bed.setRoomId(roomId);
        bedMapper.upDateBed(bed);
    }

    @Override
    public void deleteBed(Long id) {
        bedMapper.deleteBed(id);
    }
}

