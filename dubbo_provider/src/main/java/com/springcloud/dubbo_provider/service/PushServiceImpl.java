package com.springcloud.dubbo_provider.service;
import com.alibaba.cloud.dubbo.registry.DubboCloudRegistry;
import com.alibaba.dubbo.config.annotation.Service;
import com.springcloud.dubbo_api.service.PushService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Service
@Api(value = "消息推送服务")
@Slf4j
public class PushServiceImpl implements PushService {

	@ApiOperation(value = "推送", notes = "推送消息至指定帐号")
	@Override
	public String push(@ApiParam(value="帐号") String account) {
		log.info("发财树:{}","哇哈哈哈哈9999"+account.concat("中文字符串"));
		return account.concat("中文字符串");

	}

}
