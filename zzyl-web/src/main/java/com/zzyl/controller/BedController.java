package com.zzyl.controller;

import com.zzyl.base.ResponseResult;
import com.zzyl.dto.BedDto;
import com.zzyl.service.BedService;
import com.zzyl.vo.BedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bed")
@Api(tags = "床位管理相关接口")
public class BedController extends BaseController {

    @Autowired
    private BedService bedService;

    @GetMapping("/read/room/{roomId}")
    @ApiOperation(value = "根据房间id查询床位", notes = "传入房间id")
    public ResponseResult<List<BedVo>> readBedByRoomId(
            @ApiParam(value = "房间ID", required = true) @PathVariable("roomId") Long roomId) {
        List<BedVo> beds = bedService.getBedsByRoomId(roomId);
        return success(beds);
    }

    @PostMapping("/create")
    @ApiOperation("创建床位")
    public ResponseResult createBed(@RequestBody BedDto bedDto) {
        bedService.createBed(bedDto);
        return ResponseResult.success();
    }

    @GetMapping("/read/{id}")
    @ApiOperation("根据ID查询床位")
    public ResponseResult getByBedId(@PathVariable Long id){
        BedDto bedDto =bedService.getByBedId(id);
        return ResponseResult.success(bedDto);
    }

    @PutMapping("/update")
    @ApiOperation("更新床位")
    public  ResponseResult upDateBed(@RequestBody BedDto bedDto){
        bedService.upDateBed(bedDto);
        return ResponseResult.success();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除床位")
    public ResponseResult deleteBed(@PathVariable Long id){
        bedService.deleteBed(id);
        return ResponseResult.success();
    }
}
