package com.springcloud.dubbo_provider.service;
import com.alibaba.dubbo.config.annotation.Service;
import com.springcloud.dubbo_api.service.PushService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Service
@Api(value = "消息推送服务")
public class PushServiceImpl implements PushService {

	@ApiOperation(value = "推送", notes = "推送消息至指定帐号")
	@Override
	public String push(@ApiParam(value="帐号") String account) {
		return "中文字符串";
	}

}
