package com.zzyl.mapper;

import com.zzyl.entity.Bed;
import com.zzyl.vo.BedVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BedMapper {

    List<BedVo> getBedsByRoomId(Long roomId);

    void createBed(Bed bed);

    Bed getByBedId(Long id);

    void upDateBed(Bed bed);

    void deleteBed(Long id);
}

